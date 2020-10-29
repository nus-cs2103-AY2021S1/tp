package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_START_TIME;

import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class AddTutorialGroupCommandParser implements Parser<AddTutorialGroupCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isValidTime(String toVerify) {
        if (!toVerify.contains(":")) {
            return false;
        } else {
            String[] splitTime = toVerify.split(":");
            int hour = Integer.valueOf(splitTime[0]);
            int minute = Integer.valueOf(splitTime[1]);
            return (hour >= 0 && hour <= 23) && (minute >= 0 && minute <= 59);
        }
    }

    private static boolean isValidStartEndTimePair(String startTimeString, String endTimeString) {
        int startTimeInteger = Integer.valueOf(startTimeString.replace(":", ""));
        int endTimeInteger = Integer.valueOf(endTimeString.replace(":", ""));
        return isValidTime(startTimeString) && isValidTime(endTimeString) && (endTimeInteger - startTimeInteger > 0);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddTutorialGroupCommand
     * and returns a AddTutorialGroupCommand object for execution.
     */
    public AddTutorialGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TUTORIAL_GRP, PREFIX_TUTORIAL_GRP_DAY,
                PREFIX_TUTORIAL_GRP_START_TIME, PREFIX_TUTORIAL_GRP_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIAL_GRP, PREFIX_TUTORIAL_GRP_DAY,
            PREFIX_TUTORIAL_GRP_START_TIME, PREFIX_TUTORIAL_GRP_END_TIME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTutorialGroupCommand.MESSAGE_USAGE));
        }

        String id = argMultimap.getValue(PREFIX_TUTORIAL_GRP).orElse("");
        String day = argMultimap.getValue(PREFIX_TUTORIAL_GRP_DAY).orElse("");
        String startTimeString = argMultimap.getValue(PREFIX_TUTORIAL_GRP_START_TIME).orElse("");
        String endTimeString = argMultimap.getValue(PREFIX_TUTORIAL_GRP_END_TIME).orElse("");

        if (!isValidStartEndTimePair(startTimeString, endTimeString)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTutorialGroupCommand.MESSAGE_USAGE));
        }

        TutorialGroupId tutorialGroupId = ParserUtil.parseTutorialGroupId(id);
        LocalTime startTime = LocalTime.parse(startTimeString + ":00");
        LocalTime endTime = LocalTime.parse(endTimeString + ":00");

        // todo: change the LocalTime
        return new AddTutorialGroupCommand(new TutorialGroup(new TutorialGroupId(id),
                startTime, endTime, day));
    }

}
