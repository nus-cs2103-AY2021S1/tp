// FindCommandParser.java

package chopchop.logic.parser.commands;

import java.util.Optional;
import java.util.ArrayList;

import chopchop.util.Result;
import chopchop.util.Strings;
import chopchop.util.StringView;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.FindRecipeCommand;
import chopchop.logic.commands.FindIngredientCommand;

import chopchop.model.attributes.NameContainsKeywordsPredicate;

import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

public class FindCommandParser {

    private static final String commandName = Strings.COMMAND_FIND;

    /**
     * Parses a 'find' command. Syntax(es):
     * {@code delete recipe (keywords)+}
     * {@code delete ingredient (keywords)+}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a FindCommand, if the input was valid.
     */
    public static Result<? extends Command> parseFindCommand(CommandArguments args) {

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

        var words = args.getRemaining()
            .map(x -> new StringView(x).words())
            .orElse(new ArrayList<>());

        if (words.isEmpty()) {
            return Result.error("'%s' command requires at least one search term", commandName);
        }

        // we expect no named arguments
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, new ArrayList<>())).isPresent()) {
            return Result.error("unknown argument '%s'", foo.get());
        }

        if (target.equals(Strings.TARGET_INGREDIENT)) {

            return Result.of(new FindIngredientCommand(new NameContainsKeywordsPredicate(words)));

        } else if (target.equals(Strings.TARGET_RECIPE)) {

            return Result.of(new FindRecipeCommand(new NameContainsKeywordsPredicate(words)));

        } else {
            return Result.error("can only find recipes or ingredients ('%s' invalid)", target);
        }
    }
}
