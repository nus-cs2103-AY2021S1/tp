// EditCommandParser.java

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Tag;
import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;

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
import chopchop.logic.commands.DeleteRecipeCommand;
import chopchop.logic.commands.DeleteIngredientCommand;

import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

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

                var ingrEdits = new ArrayList<IngredientEditDescriptor>();
                var stepEdits = new ArrayList<StepEditDescriptor>();
                var tagEdits = new ArrayList<TagEditDescriptor>();

                for (int i = 0; i < args.getAllArguments().size(); i++) {

                    var arg = args.getAllArguments().get(i);
                    var argName = arg.fst();
                    var argValue = arg.snd();

                    if (argName.getComponents().isEmpty()) {
                        return Result.error("'%s' needs an edit-argument in an edit command", argName);
                    }

                    if (argName.name().equals(Strings.ARG_TAG.name())) {

                        var res = parseTagEdit(argName, argValue);
                        if (res.isError()) {
                            return Result.error(res.getError());
                        }

                        tagEdits.add(res.getValue());

                    } else if (argName.name().equals(Strings.ARG_STEP.name())) {

                        var res = parseStepEdit(argName, argValue);
                        if (res.isError()) {
                            return Result.error(res.getError());
                        }

                        stepEdits.add(res.getValue());

                    } else if (argName.name().equals(Strings.ARG_INGREDIENT.name())) {



                    } else {
                        return Result.error("'edit' command doesn't support '%s'", argName);
                    }
                }

                return Result.error("owo");
            });
    }

    private static Result<TagEditDescriptor> parseTagEdit(ArgName argName, String argValue) {

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

    private static Result<StepEditDescriptor> parseStepEdit(ArgName argName, String argValue) {

        var components = argName.getComponents();

        var op = components.get(0);
        if (op.equals("add")) {

            Optional<Integer> stepNumber = Optional.empty();

            // for add, the index is optional.
            if (components.size() > 1) {
                if (components.size() > 2) {
                    return Result.error("expected either /step:add or /step:add:<number>");
                }

                try {
                    stepNumber = Optional.of(Integer.parseInt(components.get(1)));
                } catch (NumberFormatException e) {
                    return Result.error("'%s' was not a valid step number (expected /step:add:<number>)",
                        components.get(1));
                }
            }

            if (argValue.isEmpty()) {
                return Result.error("expected non-empty step after /step:add");
            }

            return Result.of(new StepEditDescriptor(EditOperationType.ADD, stepNumber, argValue));

        } else if (op.equals("edit") || op.equals("delete")) {

            // what a mess of a function.

            if (components.size() != 2) {
                return Result.error("expected number after /step:%s (eg. /step:%s:3)", op, op);
            }

            int stepNumber = 0;
            try {
                stepNumber = Integer.parseInt(components.get(1));
            } catch (NumberFormatException e) {
                return Result.error("'%s' was not a valid step number (expected /step:%s:<number>)",
                    components.get(1), op);
            }

            EditOperationType type = null;

            if (op.equals("edit")) {

                if (argValue.isEmpty()) {
                    return Result.error("expected non-empty step after /step:edit");
                }

                type = EditOperationType.EDIT;

            } else {

                if (!argValue.isEmpty()) {
                    return Result.error("unexpected step after /step:delete");
                }

                type = EditOperationType.DELETE;
            }

            return Result.of(new StepEditDescriptor(type, Optional.of(stepNumber), argValue));

        } else {
            return Result.error("expected either /step:add, /step:edit, or /step:delete");
        }
    }







    class EditCommandStub extends Command {

        @Override
        public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
            return new CommandResult("");
        }
    }
}
