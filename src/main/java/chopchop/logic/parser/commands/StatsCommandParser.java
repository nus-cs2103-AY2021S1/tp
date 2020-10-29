// StatsCommandParser.java
//@@author trav1sT

package chopchop.logic.parser.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import chopchop.commons.util.Result;
import chopchop.commons.util.StringView;
import chopchop.logic.commands.Command;
import chopchop.logic.commands.StatsIngredientClearCommand;
import chopchop.logic.commands.StatsIngredientRecentCommand;
import chopchop.logic.commands.StatsIngredientUsedCommand;
import chopchop.logic.commands.StatsRecipeClearCommand;
import chopchop.logic.commands.StatsRecipeMadeCommand;
import chopchop.logic.commands.StatsRecipeRecentCommand;
import chopchop.logic.commands.StatsRecipeTopCommand;
import chopchop.logic.parser.CommandArguments;

import static chopchop.commons.util.Strings.ARG_AFTER;
import static chopchop.commons.util.Strings.ARG_BEFORE;
import static chopchop.commons.util.Strings.COMMAND_STATS;
import static chopchop.logic.parser.commands.CommonParser.ensureCommandName;
import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;
import static chopchop.logic.parser.commands.CommonParser.checkArguments;

public class StatsCommandParser {

    /**
     * Parses a 'stats' command. Syntax(es):
     */
    public static Result<? extends Command> parseStatsCommand(CommandArguments args) {
        ensureCommandName(args, COMMAND_STATS);

        return getCommandTarget(args, /* acceptsPlural: */ true)
            .then(target -> {
                var words = new StringView(target.snd()).words();

                switch (target.fst()) {
                case RECIPE:
                    return parseRecipeStatsCommand(args, target.snd().strip());

                case INGREDIENT:
                    return parseIngredientStatsCommand(args, target.snd().strip());

                default:
                    return Result.error("Can only find stats of recipes or ingredients ('%s' invalid)", target.fst());
                }
            });
    }

    private static Result<? extends Command> parseRecipeStatsCommand(CommandArguments args, String kind) {
        var c = "stats recipe";
        switch (kind) {
        case "top":
            return ensureNoArgs(args, c + " top", new StatsRecipeTopCommand());

        case "clear":
            return ensureNoArgs(args, c + " clear", new StatsRecipeClearCommand());

        case "recent":
            return ensureNoArgs(args, c + " recent", new StatsRecipeRecentCommand());

        case "made":
            return parseDateRecipeCommand(kind, args);

        default:
            return Result.error("Expected one of 'top', 'made', 'recent', or 'clear'"
                + " after '%s' (found '%s')", c, kind);
        }
    }

    private static Result<? extends Command> parseIngredientStatsCommand(CommandArguments args, String kind) {
        var c = "stats ingredient";

        switch (kind) {
        case "clear":
            return ensureNoArgs(args, c + " clear", new StatsIngredientClearCommand());

        case "recent":
            return ensureNoArgs(args, c + " recent", new StatsIngredientRecentCommand());

        case "used":
            return parseDateIngredientCommand(kind, args);

        default:
            return Result.error("Expected one of 'used', 'recent', or 'clear'"
                + " after '%s' (found '%s')", c, kind);
        }
    }










    private static Result<StatsRecipeMadeCommand> parseDateRecipeCommand(String name, CommandArguments args) {
        Optional<String> err;
        var supportedArgs = List.of(ARG_BEFORE, ARG_AFTER);
        if ((err = checkArguments(args, "stats recipe", supportedArgs)).isPresent()) {
            return Result.error(err.get());
        }

        var after = args.getArgument(ARG_AFTER);
        var before = args.getArgument(ARG_BEFORE);

        if (before.size() > 1 || after.size() > 1) {
            return Result.error("Multiple dates specified");
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

        Optional<String> err;
        var supportedArgs = List.of(ARG_BEFORE, ARG_AFTER);
        if ((err = checkArguments(args, "stats ingredient", supportedArgs)).isPresent()) {
            return Result.error(err.get());
        }

        var after = args.getArgument(ARG_AFTER);
        var before = args.getArgument(ARG_BEFORE);

        if (before.size() > 1 || after.size() > 1) {
            return Result.error("Multiple dates specified");
        }

        try {
            var arg1 = processDate(before).orElse(null);
            var arg2 = processDate(after).orElse(null);

            return Result.of(new StatsIngredientUsedCommand(arg1, arg2));

        } catch (DateTimeParseException e) {
            return Result.error("Unable to parse date: %s", e.getMessage());
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

    private static Result<? extends Command> ensureNoArgs(CommandArguments args, String cmd, Command ret) {
        Optional<String> err;
        if ((err = checkArguments(args, cmd)).isPresent()) {
            return Result.error(err.get());
        }

        return Result.of(ret);
    }
}
