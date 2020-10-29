// DeleteCommandParser.java

package chopchop.logic.parser.commands;

import java.util.List;
import java.util.Optional;

import chopchop.model.attributes.Quantity;
import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.ItemReference;
import chopchop.logic.parser.CommandArguments;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.DeleteRecipeCommand;
import chopchop.logic.commands.DeleteIngredientCommand;

import static chopchop.logic.parser.commands.CommonParser.ensureCommandName;
import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;
import static chopchop.logic.parser.commands.CommonParser.getFirstAugmentedComponent;

public class DeleteCommandParser {
    /**
     * Parses a 'delete' command. Syntax(es):
     * {@code delete recipe REF}
     * {@code delete ingredient REF [/qty QUANTITY]}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a DeleteCommand, if the input was valid.
     */
    public static Result<? extends Command> parseDeleteCommand(CommandArguments args) {
        ensureCommandName(args, Strings.COMMAND_DELETE);

        return getCommandTarget(args)
            .then(target -> {
                if (target.snd().isEmpty()) {
                    return Result.error("Recipe or ingredient name cannot be empty");
                }

                switch (target.fst()) {
                case RECIPE:
                    return parseDeleteRecipeCommand(target.snd().strip(), args);

                case INGREDIENT:
                    return parseDeleteIngredientCommand(target.snd().strip(), args);

                default:
                    return Result.error("Can only delete recipes or ingredients ('%s' invalid)", target.fst());
                }
            });
    }

    /**
     * Parses a 'delete ingredient' command. Syntax:
     * {@code delete ingredient REF [/qty QUANTITY]}
     */
    private static Result<DeleteIngredientCommand> parseDeleteIngredientCommand(String name, CommandArguments args) {

        Optional<ArgName> foo;
        if ((foo = getFirstAugmentedComponent(args)).isPresent()) {
            return Result.error("'delete ingredient' command doesn't support edit-arguments (found '%s')",
                foo.get());
        } else if ((foo = getFirstUnknownArgument(args, List.of(Strings.ARG_QUANTITY))).isPresent()) {
            return Result.error("'delete ingredient' command doesn't support '%s'", foo.get());
        }

        var qtys = args.getArgument(Strings.ARG_QUANTITY);
        if (qtys.size() > 1) {
            return Result.error("Multiple quantities specified");
        } else if (qtys.size() == 1 && qtys.get(0).isEmpty()) {
            return Result.error("Specified quantity cannot be emtpy");
        }

        return ItemReference.parse(name)
            .then(ref -> Result.transpose(qtys
                .stream()
                .findFirst()
                .map(Quantity::parse))
                .map(qty -> new DeleteIngredientCommand(ref, qty)));
    }

    /**
     * Parses a 'delete recipe' command. Syntax:
     * {@code delete recipe REF}
     */
    private static Result<DeleteRecipeCommand> parseDeleteRecipeCommand(String name, CommandArguments args) {

        Optional<ArgName> foo;
        if ((foo = getFirstAugmentedComponent(args)).isPresent()) {
            return Result.error("'delete recipe' command doesn't support edit-arguments (found '%s')",
                foo.get());
        } else if ((foo = getFirstUnknownArgument(args, List.of())).isPresent()) {
            return Result.error("'delete recipe' command doesn't support '%s'", foo.get());
        }

        return ItemReference.parse(name).map(DeleteRecipeCommand::new);
    }
}
