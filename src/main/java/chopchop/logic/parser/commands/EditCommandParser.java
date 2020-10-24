// EditCommandParser.java

import java.util.Optional;
import java.util.ArrayList;

import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Tag;
import chopchop.commons.util.Pair;
import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;

import chopchop.logic.edit.EditDescriptor;
import chopchop.logic.edit.EditOperationType;
import chopchop.logic.edit.IngredientEditDescriptor;
import chopchop.logic.edit.RecipeEditDescriptor;
import chopchop.logic.edit.StepEditDescriptor;
import chopchop.logic.edit.TagEditDescriptor;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.ItemReference;
import chopchop.logic.parser.CommandArguments;
import chopchop.logic.parser.commands.CommandTarget;

import chopchop.logic.commands.Command;

import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;

import chopchop.model.Model;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;

public class EditCommandParser {

    /**
     * Parses an 'edit' command. Syntax:
     * {@code edit recipe <#REF> (...)}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     an AddCommand, if the input was valid.
     */
    public static Result<? extends Command> parseEditCommand(CommandArguments args) {
        assert args.getCommand().equals(Strings.COMMAND_EDIT);

        return getCommandTarget(args)
            .then(target -> {
                if (target.snd().isEmpty()) {
                    return Result.error("recipe name cannot be empty");
                } else if (target.fst() != CommandTarget.RECIPE) {
                    return Result.error("only recipes can be edited");
                }

                return ItemReference.parse(target.snd());
            })
            .then(item -> {

                var edits = new ArrayList<Result<EditDescriptor>>();

                for (int i = 0; i < args.getAllArguments().size(); i++) {

                    var arg = args.getAllArguments().get(i);
                    var argName = arg.fst();
                    var argValue = arg.snd();

                    if (argName.getComponents().isEmpty()) {
                        return Result.error("'%s' needs an edit-argument in an edit command", argName);
                    }

                    if (argName.name().equals(Strings.ARG_TAG.name())) {

                        edits.add(parseTagEdit(argName, argValue));

                    } else if (argName.name().equals(Strings.ARG_STEP.name())) {

                        edits.add(parseStepEdit(argName, argValue));

                    } else if (argName.name().equals(Strings.ARG_INGREDIENT.name())) {

                        Optional<Quantity> qty = Optional.empty();
                        if (i + 1 < args.getAllArguments().size()) {
                            var nextArg = args.getAllArguments().get(i + 1);
                            if (nextArg.fst().equals(Strings.ARG_QUANTITY)) {

                                var q = Quantity.parse(nextArg.snd());
                                if (q.isError()) {
                                    return Result.error(q.getError());
                                } else {
                                    qty = q.toOptional();
                                }

                                // skip it on the next turn.
                                i += 1;
                            }
                        }

                        edits.add(parseIngredientEdit(argName, argValue, qty));

                    } else {
                        return Result.error("'edit' command doesn't support '%s'", argName);
                    }
                }

                return Result.of(Pair.of(item, edits));
            })
            .map(pair -> pair.mapSnd(x -> Result.sequence(x)))
            .then(pair -> {

                var ingrEdits = new ArrayList<IngredientEditDescriptor>();
                var stepEdits = new ArrayList<StepEditDescriptor>();
                var tagEdits = new ArrayList<TagEditDescriptor>();

                if (pair.snd().isError()) {
                    return Result.error(pair.snd().getError());
                }

                var list = pair.snd().getValue();
                for (var item : list) {
                    if (item instanceof TagEditDescriptor) {
                        tagEdits.add((TagEditDescriptor) item);
                    } else if (item instanceof StepEditDescriptor) {
                        stepEdits.add((StepEditDescriptor) item);
                    } else if (item instanceof IngredientEditDescriptor) {
                        ingrEdits.add((IngredientEditDescriptor) item);
                    } else {
                        // unreachable
                        assert false : "invalid edit descriptor";
                    }
                }

                return Result.of(new EditCommandStub(pair.fst(),
                    new RecipeEditDescriptor(ingrEdits, stepEdits, tagEdits)));
            });
    }




    private static Result<EditDescriptor> parseIngredientEdit(ArgName argName, String ingredientName,
        Optional<Quantity> qty) {

        var components = argName.getComponents();
        var op = components.get(0);

        if (components.size() != 1
            || (!op.equals("add") && !op.equals("edit") && !op.equals("delete"))) {

            return Result.error("expected either (and only) 'add', 'edit', or 'delete' after '/ingredient:'");
        }

        if (ingredientName.isEmpty()) {
            return Result.error("expected ingredient name after /ingredient:%s", op);
        }

        return ensureNoArgsForDeleteAndGetOperationType("ingredient", op, qty.isEmpty())
            .map(kind -> new IngredientEditDescriptor(kind, ingredientName, qty));
    }









    private static Result<EditDescriptor> parseTagEdit(ArgName argName, String argValue) {

        var components = argName.getComponents();

        var op = components.get(0);
        if (components.size() > 1 || (!op.equals("add") && !op.equals("delete"))) {
            return Result.error("expected either /tag:add or /tag:delete");
        }

        if (argValue.isEmpty()) {
            return Result.error("expected tag name after /tag:add or /tag:delete");
        } else if (!Tag.isValidTagName(argValue)) {
            return Result.error(Tag.MESSAGE_CONSTRAINTS);
        }

        return Result.of(new TagEditDescriptor(
            op.equals("add") ? EditOperationType.ADD : EditOperationType.DELETE,
            argValue
        ));
    }

    private static Result<EditDescriptor> parseStepEdit(ArgName argName, String argValue) {

        var components = argName.getComponents();

        var op = components.get(0);


        // first get the step number.
        Optional<Integer> optStep = Optional.empty();
        if (components.size() > 1) {

            int stepnum = 0;

            try {
                stepnum = Integer.parseInt(components.get(1));
            } catch (NumberFormatException e) {
                return Result.error("'%s' was not a valid step number (expected /step:%s:<number>)",
                    components.get(1), op);
            }

            if (stepnum <= 0) {
                return Result.error("step number should be greater than 0");
            }

            optStep = Optional.of(stepnum);
        }


        final var stepNumber = optStep;
        if (op.equals("add")) {

            // for add, the index is optional.
            if (components.size() > 2) {
                return Result.error("expected either /step:add or /step:add:<number>");
            }

            if (argValue.isEmpty()) {
                return Result.error("expected non-empty step after /step:add");
            }

            return Result.of(new StepEditDescriptor(EditOperationType.ADD, stepNumber, argValue));

        } else if (op.equals("edit") || op.equals("delete")) {

            // what a mess of a function.

            if (components.size() != 2 || stepNumber.isEmpty()) {
                return Result.error("expected number after /step:%s (eg. /step:%s:3)", op, op);
            }

            return ensureNoArgsForDeleteAndGetOperationType("step", op, argValue.isEmpty())
                .map(kind -> new StepEditDescriptor(kind, stepNumber, argValue));

        } else {
            return Result.error("expected either /step:add, /step:edit, or /step:delete");
        }
    }




    private static Result<EditOperationType> ensureNoArgsForDeleteAndGetOperationType(
        String editor, String op, boolean argIsEmpty) {

        if (op.equals("add")) {
            if (argIsEmpty) {
                return Result.error("expected non-empty step after /%s:%s", editor, op);
            }

            return Result.of(EditOperationType.ADD);

        } else if (op.equals("edit")) {
            if (argIsEmpty) {
                return Result.error("expected non-empty step after /%s:%s", editor, op);
            }

            return Result.of(EditOperationType.EDIT);

        } else if (op.equals("delete")) {
            if (!argIsEmpty) {
                return Result.error("unexpected %s after /%s:%s", editor, editor, op);
            }

            return Result.of(EditOperationType.DELETE);

        } else {
            return Result.error("expected either /%s:add, /%s:edit, or /%s:delete",
                editor, editor, editor);
        }
    }


    static class EditCommandStub extends Command {

        private final ItemReference recipe;
        private final RecipeEditDescriptor edit;

        public EditCommandStub(ItemReference recipe, RecipeEditDescriptor edit) {
            this.recipe = recipe;
            this.edit = edit;
        }

        @Override
        public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
            return new CommandResult("");
        }
    }
}
