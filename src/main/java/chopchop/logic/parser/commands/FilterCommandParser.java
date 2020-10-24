// FilterCommandParser.java

package chopchop.logic.parser.commands;

import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;
import chopchop.logic.commands.Command;
import chopchop.logic.commands.FilterCommand;
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
        if (args.getAllArguments().isEmpty()) {
            return Result.error("filtering criteria cannot be empty!");
        }

        // we expect no named arguments
        Optional<ArgName> foo;
        List<ArgName> s = new ArrayList<>(
            List.of(Strings.ARG_EXPIRY, Strings.ARG_INGREDIENT, Strings.ARG_TAG));

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

    private static Result<? extends FilterCommand> parseFilterIngredientCommand(CommandArguments args) {
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(Strings.ARG_TAG,
            Strings.ARG_EXPIRY))).isPresent()) {
            return Result.error("'filter ingredient' command doesn't support '%s'\n%s",
                foo.get(), FilterIngredientCommand.MESSAGE_USAGE);
        }

        var exps = args.getArgument(Strings.ARG_EXPIRY);
        Result<FilterCommand> rst_fst = checkImproperFieldInput(exps);
        if (rst_fst != null) {
            return rst_fst;
        }
        var tags = args.getArgument(Strings.ARG_TAG);
        Result<FilterCommand> rst_snd = checkImproperFieldInput(tags);
        if (rst_snd != null) {
            return rst_snd;
        }

        Result<ExpiryDate> nearestExpDate = null;
        if (exps.size() > 0) {
            // get the earliest expiry date
            nearestExpDate = parseExpiryDates(exps);
        }

        return Result.of(new FilterIngredientCommand(
                nearestExpDate == null ? null : new ExpiryDateMatchesKeywordsPredicate(nearestExpDate.getValue()),
                tags.size() == 0 ? null : new TagContainsKeywordsPredicate(tags)
        ));
    }

    private static Result<ExpiryDate> parseExpiryDates(List<String> args) {

        var exdOrigList = new ArrayList<Result<ExpiryDate>>();
        for (String s : args) {
            exdOrigList.add(ExpiryDate.of(s));
        }

        List<ExpiryDate> exdList = exdOrigList.stream()
            .map(rst -> rst.getValue())
            .collect(Collectors.toList());

        Collections.sort(exdList);

        return Result.of(exdList.get(0));
    }

    private static Result<? extends FilterCommand> parseFilterRecipeCommand(CommandArguments args) {

        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(
                Strings.ARG_INGREDIENT, Strings.ARG_TAG))).isPresent()) {
            return Result.error("'filter recipe' command doesn't support '%s'\n%s",
                    foo.get(), FilterRecipeCommand.MESSAGE_USAGE);
        }
        var ingredients = args.getArgument(Strings.ARG_INGREDIENT);
        Result<FilterCommand> rst_fst = checkImproperFieldInput(ingredients);
        if (rst_fst != null) {
            return rst_fst;
        }
        var tags = args.getArgument(Strings.ARG_TAG);
        Result<FilterCommand> rst_snd = checkImproperFieldInput(tags);
        if (rst_snd != null) {
            return rst_snd;
        }

        return Result.of(new FilterRecipeCommand(
                tags.isEmpty() ? null : new TagContainsKeywordsPredicate(tags),
                ingredients.isEmpty() ? null : new IngredientsContainsKeywordsPredicate(ingredients)
        ));
    }

    private static Result<FilterCommand> checkImproperFieldInput(List<String> lst) {
        int siz = lst.size();
        if (siz == 0) {
            return null;
        }
        int i = 0;
        while (i < siz) {
            String s = lst.get(i);
            if (s.isEmpty()) {
                return Result.error("field input cannot be empty");
            } else if (s.split("\\s+").length > 1) {
                return Result.error("field inputs should be single words");
            }
            i++;
        }
        return null;
    }
}
