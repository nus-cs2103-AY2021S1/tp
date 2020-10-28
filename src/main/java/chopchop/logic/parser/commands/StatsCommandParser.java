// StatsCommandParser.java
//@@author trav1sT

package chopchop.logic.parser.commands;

import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;
import static chopchop.logic.parser.commands.CommonParser.getFirstAugmentedComponent;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import chopchop.commons.util.Result;
import chopchop.commons.util.StringView;
import chopchop.commons.util.Strings;
import chopchop.logic.commands.Command;
import chopchop.logic.commands.StatsIngredientClearCommand;
import chopchop.logic.commands.StatsIngredientRecentCommand;
import chopchop.logic.commands.StatsIngredientUsedCommand;
import chopchop.logic.commands.StatsRecipeClearCommand;
import chopchop.logic.commands.StatsRecipeMadeCommand;
import chopchop.logic.commands.StatsRecipeRecentCommand;
import chopchop.logic.commands.StatsRecipeTopCommand;
import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;

public class StatsCommandParser {

    /**
     * Parses a 'stats' command. Syntax(es):
     */
    public static Result<? extends Command> parseStatsCommand(CommandArguments args) {
        assert args.getCommand().equals(Strings.COMMAND_STATS);

        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(
            Strings.ARG_BEFORE, Strings.ARG_AFTER))).isPresent()) {
            return Result.error("'stats' command doesn't support '%s'", foo.get());
        }

        return getCommandTarget(args, /* acceptsPlural: */ true)
            .then(target -> {
                var words = new StringView(target.snd()).words();

                switch (target.fst()) {
                case RECIPE:
                    if (target.snd().equals("top")) {
                        return Result.of(new StatsRecipeTopCommand());
                    }
                    if (target.snd().equals("recent")) {
                        return Result.of(new StatsRecipeRecentCommand());
                    }
                    if (target.snd().equals("clear")) {
                        return Result.of(new StatsRecipeClearCommand());
                    }

                    return parseDateRecipeCommand(target.snd().strip(), args);

                case INGREDIENT:
                    if (target.snd().equals("clear")) {
                        return Result.of(new StatsIngredientClearCommand());
                    }
                    if (target.snd().equals("recent")) {
                        return Result.of(new StatsIngredientRecentCommand());
                    }
                    return parseDateIngredientCommand(target.snd().strip(), args);

                default:
                    return Result.error("Can only find stats of recipes or ingredients ('%s' invalid)", target.fst());
                }
            });
    }

    private static Result<StatsRecipeMadeCommand> parseDateRecipeCommand(String name, CommandArguments args) {
        if (!name.equals("used")) {
            return Result.error("This is an invalid command. Try 'stats recipe used', 'stats recipe top', "
                + "stats recipe recent' or 'stats recipe clear'");
        }

        Optional<ArgName> foo;
        var supportedArgs = List.of(Strings.ARG_BEFORE, Strings.ARG_AFTER);
        if ((foo = getFirstUnknownArgument(args, supportedArgs)).isPresent()) {
            return Result.error("'stats recipe' command doesn't support '%s'", foo.get());
        } else if ((foo = getFirstAugmentedComponent(args)).isPresent()) {
            return Result.error("'stats' command doesn't support edit-arguments");
        }

        var after = args.getArgument(Strings.ARG_AFTER);
        var before = args.getArgument(Strings.ARG_BEFORE);

        if (before.size() + after.size() == 0) {
            return Result.error("At least 1 search criteria must be specified");
        }

        try {

            var arg1 = processDate(before).orElse(null);
            var arg2 = processDate(after).orElse(null);

            return Result.of(new StatsRecipeMadeCommand(arg1, arg2));

        } catch (Exception e) {

            return Result.error("Unable to parse date");
        }
    }

    private static Result<StatsIngredientUsedCommand> parseDateIngredientCommand(String name, CommandArguments args) {

        if (!name.equals("used")) {
            return Result.error("This is an invalid command. Try 'stats ingredient used',"
                + "stats ingredient recent' or 'stats ingredient clear'");
        }

        Optional<ArgName> foo;
        var supportedArgs = List.of(Strings.ARG_BEFORE, Strings.ARG_AFTER);
        if ((foo = getFirstUnknownArgument(args, supportedArgs)).isPresent()) {
            return Result.error("'stats ingredient' command doesn't support '%s'", foo.get());
        } else if ((foo = getFirstAugmentedComponent(args)).isPresent()) {
            return Result.error("'stats' command doesn't support edit-arguments");
        }

        var after = args.getArgument(Strings.ARG_AFTER);
        var before = args.getArgument(Strings.ARG_BEFORE);

        if (before.size() > 1 || after.size() > 1) {
            return Result.error("Multiple dates specified");
        }

        if (before.size() + after.size() == 0) {
            return Result.error("At least 1 search criteria must be specified");
        }

        try {
            var arg1 = processDate(before).orElse(null);
            var arg2 = processDate(after).orElse(null);

            return Result.of(new StatsIngredientUsedCommand(arg1, arg2));

        } catch (Exception e) {
            return Result.error("Unable to parse date");
        }
    }

    private static Optional<LocalDateTime> processDate(List<String> strings) throws DateTimeParseException {
        assert strings.size() < 2;

        if (strings.size() == 0) {
            return Optional.empty();
        }

        var timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String val = strings.get(0);
        if (val.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            return Optional.of(LocalDateTime.of(LocalDate.parse(val, formatter), LocalTime.of(0, 0)));
        } else {
            return Optional.of(LocalDateTime.parse(val, timeFormatter));
        }
    }
}
