package seedu.homerce.logic.parser.schedule;

import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.homerce.logic.commands.schedule.AbstractWeekCommand;
import seedu.homerce.logic.commands.schedule.CurrentWeekCommand;
import seedu.homerce.logic.commands.schedule.CustomWeekCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.Prefix;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.util.attributes.Date;

public class ScheduleCommandParser implements Parser<AbstractWeekCommand> {
    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid parameters for schedule command.\n%1$s";

    @Override
    public AbstractWeekCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            return new CurrentWeekCommand();
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_DATE);
        if (!isPrefixPresent(argMultimap, PREFIX_DATE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    CustomWeekCommand.MESSAGE_USAGE
                )
            );
        }
        Date dateToNavigate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        return new CustomWeekCommand(dateToNavigate);
    }

    /**
     * Returns true if the prefix is present in the user's command.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
