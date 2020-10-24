// FilterCommandParser.java

package chopchop.logic.parser.commands;

import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;
import chopchop.logic.commands.Command;
import chopchop.logic.commands.FilterIngredientCommand;
import chopchop.logic.commands.FilterRecipeCommand;
import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.ExpiryDateMatchesKeywordsPredicate;
import chopchop.model.attributes.IngredientsContainsKeywordsPredicate;
import chopchop.model.attributes.TagContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

public class FilterCommandParser {

    private static final String commandName = Strings.COMMAND_FILTER;

    /**
     * Parses a 'filter' command. Syntax(es):
     * {@code filter recipe (keywords)+}
     * {@code filter ingredient (keywords)+}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a FilterCommand, if the input was valid.
     */
    public static Result<? extends Command> parseFilterCommand(CommandArguments args) {

        if (!args.getCommand().equals(commandName)) {
            return Result.error("invalid command '%s' (expected '%s')", args.getCommand(), commandName);
        }
        if (args.getRemaining().isBlank()) {
            return Result.error("filtering target cannot be empty!");
        }

        // we expect no named arguments
        Optional<ArgName> foo;
        List<ArgName> s = new ArrayList<>();
        s.add(Strings.ARG_EXPIRY);
        s.add(Strings.ARG_INGREDIENT);
        s.add(Strings.ARG_TAG);
        if (args.getAllArguments().isEmpty()) {
            return Result.error("filtering criteria cannot be empty!");
        }

        if ((foo = getFirstUnknownArgument(args, s)).isPresent()) {
            return Result.error("'filter' command doesn't support '%s'\n%s",
                foo.get(), FilterRecipeCommand.MESSAGE_USAGE);
        }

        return getCommandTarget(args)
            .then(target -> {
                if (!target.snd().isEmpty()) {
                    return Result.error("recipe or ingredient name should be empty");
                }

                switch (target.fst()) {
                case RECIPE:
                    return parseFilterRecipeCommand(args);

                case INGREDIENT:
                    return parseFilterIngredientCommand(args);

                default:
                    return Result.error("can only filter recipes or ingredients ('%s' invalid)", target.fst());
                }
            });
    }

    private static Result<FilterIngredientCommand> parseFilterIngredientCommand(CommandArguments args) {

        assert args.getCommand().equals(Strings.COMMAND_FILTER);

        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(Strings.ARG_TAG,
            Strings.ARG_EXPIRY))).isPresent()) {

            return Result.error("'filter ingredient' command doesn't support 's%'\n%s",
                foo.get(), FilterIngredientCommand.MESSAGE_USAGE);
        } ///tag movie /tag family reunion /tag snacks

        var tags = args.getArgument(Strings.ARG_TAG);
        if (tags.size() == 1 && tags.get(0).isEmpty()) {
            return Result.error("tag content cannot be empty");
        }

        var exps = args.getArgument(Strings.ARG_EXPIRY);
        if (exps.size() == 1 && exps.get(0).isEmpty()) {
            return Result.error("expiry date cannot be empty");
        }

        Result<ExpiryDate> nearestExpDate;
        if (exps.size() > 0) {
            // get the expiry date nearest from now
            nearestExpDate = parseExpiryDates(exps);
        } else {
            nearestExpDate = null;
        }

        return Result.of(new FilterIngredientCommand(
                nearestExpDate == null ? null : new ExpiryDateMatchesKeywordsPredicate(nearestExpDate.getValue()),
                tags.size() == 0 ? null : new TagContainsKeywordsPredicate(tags)
        ));

    }

    /**
     * Parse the list of ingredients.
     */
    private static Result<ExpiryDate> parseExpiryDates(List<String> args) {

        var exdOrigList = new ArrayList<Result<ExpiryDate>>();

        args.stream().map(exd -> exdOrigList.add(ExpiryDate.of(exd)));

        List<ExpiryDate> exdList = exdOrigList.stream().map(rst -> rst.getValue()).collect(Collectors.toList());
        Collections.sort(exdList);

        return Result.of(exdList.get(0));
    }

    private static Result<FilterRecipeCommand> parseFilterRecipeCommand(CommandArguments args) {
        assert args.getCommand().equals(Strings.COMMAND_FILTER);

        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(
                Strings.ARG_INGREDIENT, Strings.ARG_TAG))).isPresent()) {

            return Result.error("'filter recipe' command doesn't support '%s'\n%s",
                    foo.get(), FilterRecipeCommand.MESSAGE_USAGE);
        }

        var tags = args.getArgument(Strings.ARG_TAG);
        if (tags.size() == 1 && tags.get(0).isEmpty()) {
            return Result.error("tag content cannot be empty");
        }

        var ingredients = args.getArgument(Strings.ARG_INGREDIENT);
        if (ingredients.size() == 1 && ingredients.get(0).isEmpty()) {
            return Result.error("name of ingredient cannot be empty");
        }

        return Result.of(new FilterRecipeCommand(
                tags.isEmpty() ? null : new TagContainsKeywordsPredicate(tags),
                ingredients.isEmpty() ? null : new IngredientsContainsKeywordsPredicate(ingredients)
                ));
    }
}
