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
import chopchop.logic.commands.StatsIngredientDateCommand;
import chopchop.logic.commands.StatsRecipeDateCommand;
import chopchop.logic.commands.StatsRecipeMostCookedCommand;
import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;

public class StatsCommandParser {

    /**
     * Parses a 'stats' command. Syntax(es):
     */
    public static Result<? extends Command> parseStatsCommand(CommandArguments args) {
        assert args.getCommand().equals(Strings.COMMAND_STATS);

        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(Strings.ARG_BEFORE, Strings.ARG_AFTER))).isPresent()) {
            return Result.error("'stats' command doesn't support '%s'\n%s",
                foo.get(), StatsRecipeMostCookedCommand.MESSAGE_USAGE);
        }

        return getCommandTarget(args)
            .then(target -> {
                var words = new StringView(target.snd()).words();

                switch (target.fst()) {
                case RECIPE:
                    if (target.snd().equals("most cooked")) {
                        return Result.of(new StatsRecipeMostCookedCommand());
                    }
                    return parseDateRecipeCommand(target.snd().strip(), args);

                case INGREDIENT:
                    return parseDateIngredientCommand(target.snd().strip(), args);

                default:
                    return Result.error("Stats of recipes or ingredients not found ('%s' invalid)", target.fst());
                }
            });
    }

    private static Result<StatsRecipeDateCommand> parseDateRecipeCommand(String name, CommandArguments args) {
        if (!name.isBlank()) {
            return Result.error("Just 'stats recipe' will do. Do not specify name.");
        }
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(Strings.ARG_BEFORE, Strings.ARG_AFTER))).isPresent()) {

            return Result.error("'stats recipe' command doesn't support '%s'\n%s",
                foo.get(), StatsRecipeDateCommand.MESSAGE_USAGE);
        }

        if ((foo = getFirstAugmentedComponent(args)).isPresent()) {
            return Result.error("'stats' command doesn't support edit-arguments\n%s",
                StatsRecipeDateCommand.MESSAGE_USAGE);
        }

        var before = args.getArgument(Strings.ARG_BEFORE);
        if (before.size() > 1) {
            return Result.error("multiple dates specified\n%s", StatsRecipeDateCommand.MESSAGE_USAGE);
        }

        var after = args.getArgument(Strings.ARG_AFTER);
        if (after.size() > 1) {
            return Result.error("multiple dates specified\n%s", StatsRecipeDateCommand.MESSAGE_USAGE);
        }

        try {
            LocalDateTime arg1 = processDate(before).orElse(null);
            LocalDateTime arg2 = processDate(after).orElse(null);
            return Result.of(new StatsRecipeDateCommand(arg1, arg2));
        } catch (Exception e) {
            return Result.error("Unable to parse date\n%s",
                StatsRecipeDateCommand.MESSAGE_USAGE);
        }
    }

    private static Result<StatsIngredientDateCommand> parseDateIngredientCommand(String name, CommandArguments args) {
        if (!name.isBlank()) {
            return Result.error("Just 'stats ingredient' will do. Do not specify name.");
        }
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of(Strings.ARG_BEFORE, Strings.ARG_AFTER))).isPresent()) {

            return Result.error("'stats ingredient' command doesn't support '%s'\n%s",
                foo.get(), StatsRecipeDateCommand.MESSAGE_USAGE);
        }

        if ((foo = getFirstAugmentedComponent(args)).isPresent()) {
            return Result.error("'stats' command doesn't support edit-arguments\n%s",
                StatsRecipeDateCommand.MESSAGE_USAGE);
        }

        var before = args.getArgument(Strings.ARG_BEFORE);
        if (before.size() > 1) {
            return Result.error("multiple dates specified\n%s", StatsIngredientDateCommand.MESSAGE_USAGE);
        }

        var after = args.getArgument(Strings.ARG_AFTER);
        if (after.size() > 1) {
            return Result.error("multiple dates specified\n%s", StatsIngredientDateCommand.MESSAGE_USAGE);
        }

        try {
            LocalDateTime arg1 = processDate(before).orElse(null);
            LocalDateTime arg2 = processDate(after).orElse(null);
            return Result.of(new StatsIngredientDateCommand(arg1, arg2));
        } catch (Exception e) {
            return Result.error("Unable to parse date\n%s",
                StatsRecipeDateCommand.MESSAGE_USAGE);
        }
    }

    private static Optional<LocalDateTime> processDate(List<String> strings) throws DateTimeParseException {
        assert strings.size() < 2;

        if (strings.size() == 0) {
            return Optional.empty();
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String val = strings.get(0);
        if (val.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            return Optional.of(LocalDateTime.of(LocalDate.parse(val, formatter), LocalTime.of(0, 0)));
        } else {
            return Optional.of(LocalDateTime.parse(val, timeFormatter));
        }
    }
}
