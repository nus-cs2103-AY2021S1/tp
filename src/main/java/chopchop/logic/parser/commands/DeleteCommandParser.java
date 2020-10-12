// DeleteCommandParser.java

package chopchop.logic.parser.commands;

import java.util.Optional;
import java.util.ArrayList;

import chopchop.util.Result;
import chopchop.util.Strings;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.ItemReference;
import chopchop.logic.parser.CommandArguments;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.DeleteRecipeCommand;
import chopchop.logic.commands.DeleteIngredientCommand;

import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

public class DeleteCommandParser {

    private static final String commandName = Strings.COMMAND_DELETE;

    /**
     * Parses a 'delete' command. Syntax(es):
     * {@code delete recipe REF}
     * {@code delete ingredient REF}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a DeleteCommand, if the input was valid.
     */
    public static Result<? extends Command> parseDeleteCommand(CommandArguments args) {

        if (!args.getCommand().equals(commandName)) {
            return Result.error("invalid command '%s' (expected '%s')", args.getCommand(), commandName);
        }

        // java type inference sucks, so lambdas are out of the question.
        if (args.getTarget().isEmpty()) {
            return Result.error("'%s' command requires a target (either 'recipe' or 'ingredient')",
                commandName);
        }

        var target = args.getTarget().get();

        if (target.equals(Strings.TARGET_INGREDIENT)) {
            return parseDeleteIngredientCommand(args);
        } else if (target.equals(Strings.TARGET_RECIPE)) {
            return parseDeleteRecipeCommand(args);
        } else {
            return Result.error("can only delete recipes or ingredients ('%s' invalid)", target);
        }
    }

    /**
     * Parses a 'delete ingredient' command. Syntax:
     * {@code delete ingredient REF}
     */
    private static Result<DeleteIngredientCommand> parseDeleteIngredientCommand(CommandArguments args) {
        assert args.getCommand().equals(commandName)
            && args.getTarget().equals(Optional.of(Strings.TARGET_INGREDIENT));

        // the non-named argument is the name of the ingredient.
        if (args.getRemaining().isEmpty()) {
            return Result.error("ingredient name cannot be empty");
        }

        // we expect no named arguments
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, new ArrayList<>())).isPresent()) {
            return Result.error("unknown argument '%s'", foo.get());
        }

        var ref = args.getRemaining().get();
        return ItemReference.parse(ref)
            .map(DeleteIngredientCommand::new);
    }

    /**
     * Parses a 'delete recipe' command. Syntax:
     * {@code delete recipe REF}
     */
    private static Result<DeleteRecipeCommand> parseDeleteRecipeCommand(CommandArguments args) {
        assert args.getCommand().equals(commandName)
            && args.getTarget().equals(Optional.of(Strings.TARGET_RECIPE));

        // the non-named argument is the name of the ingredient.
        if (args.getRemaining().isEmpty()) {
            return Result.error("recipe name cannot be empty");
        }

        // we expect no named arguments
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, new ArrayList<>())).isPresent()) {
            return Result.error("unknown argument '%s'", foo.get());
        }

        var ref = args.getRemaining().get();
        return ItemReference.parse(ref)
            .map(DeleteRecipeCommand::new);
    }
}
