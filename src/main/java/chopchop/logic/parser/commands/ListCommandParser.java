// ListCommandParser.java

package chopchop.logic.parser.commands;

import java.util.Optional;
import java.util.ArrayList;

import chopchop.util.Result;
import chopchop.util.Strings;
import chopchop.util.StringView;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.ListRecipeCommand;
import chopchop.logic.commands.ListIngredientCommand;

import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

public class ListCommandParser {

    private static final String commandName = Strings.COMMAND_LIST;

    /**
     * Parses a 'list' command. Syntax(es):
     * {@code list recipe}
     * {@code list ingredient}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a ListCommand, if the input was valid.
     */
    public static Result<? extends Command> parseListCommand(CommandArguments args) {

        if (!args.getCommand().equals(commandName)) {
            return Result.error("invalid command '%s' (expected '%s')", args.getCommand(), commandName);
        }

        // java type inference sucks, so lambdas are out of the question.
        if (args.getTarget().isEmpty()) {
            return Result.error("'%s' command requires a target (either 'recipe' or 'ingredient')",
                commandName);
        }

        // hold on to this first; validate it later.
        var target = args.getTarget().get();

        // just do this to make an error message.
        if (!args.getRemaining().isEmpty()) {
            return Result.error("'%s' command takes no arguments (found '%s...')", commandName,
                new StringView(args.getRemaining().get()).take(7));
        }

        // we expect no named arguments
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, new ArrayList<>())).isPresent()) {
            return Result.error("unknown argument '%s'", foo.get());
        }

        if (target.equals(Strings.TARGET_INGREDIENT)) {

            return Result.of(new ListIngredientCommand());

        } else if (target.equals(Strings.TARGET_RECIPE)) {

            return Result.of(new ListRecipeCommand());

        } else {
            return Result.error("can only list recipes or ingredients ('%s' invalid)", target);
        }
    }
}
