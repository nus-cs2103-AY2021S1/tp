package tutorspet.logic.parser.lesson;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutorspet.logic.commands.lesson.AddLessonCommand.MESSAGE_USAGE;
import static tutorspet.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_DAY;
import static tutorspet.logic.parser.CliSyntax.PREFIX_END_TIME;
import static tutorspet.logic.parser.CliSyntax.PREFIX_NUMBER_OF_OCCURRENCES;
import static tutorspet.logic.parser.CliSyntax.PREFIX_START_TIME;
import static tutorspet.logic.parser.CliSyntax.PREFIX_VENUE;
import static tutorspet.logic.parser.ParserUtil.parseIndex;
import static tutorspet.logic.parser.ParserUtil.parseNumberOfOccurrences;
import static tutorspet.model.lesson.Lesson.MESSAGE_CONSTRAINTS;
import static tutorspet.model.lesson.Lesson.isValidStartTimeEndTime;

import java.time.LocalTime;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.lesson.AddLessonCommand;
import tutorspet.logic.parser.ArgumentMultimap;
import tutorspet.logic.parser.ArgumentTokenizer;
import tutorspet.logic.parser.Parser;
import tutorspet.logic.parser.ParserUtil;
import tutorspet.logic.parser.exceptions.ParseException;
import tutorspet.model.lesson.Day;
import tutorspet.model.lesson.Lesson;
import tutorspet.model.lesson.NumberOfOccurrences;
import tutorspet.model.lesson.Venue;

/**
 * Parses input arguments and creates a new AddLessonCommand object.
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLessonCommand
     * and returns an AddLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLASS_INDEX, PREFIX_DAY,
                        PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_VENUE,
                        PREFIX_NUMBER_OF_OCCURRENCES);

        Index moduleClassIndex;

        // parse moduleClass index
        boolean isModuleClassIndexPresent = argMultimap.getValue(PREFIX_CLASS_INDEX).isPresent();
        if (!isModuleClassIndexPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        try {
            moduleClassIndex = parseIndex(argMultimap.getValue(PREFIX_CLASS_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        // parse lesson data values
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DAY,
                PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_VENUE, PREFIX_NUMBER_OF_OCCURRENCES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        LocalTime startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        LocalTime endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get());
        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
        NumberOfOccurrences numberOfOccurrences =
                parseNumberOfOccurrences(argMultimap.getValue(PREFIX_NUMBER_OF_OCCURRENCES).get());

        // check that start time and end time are valid
        if (!isValidStartTimeEndTime(startTime, endTime)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }

        Lesson lesson = new Lesson(startTime, endTime, day, numberOfOccurrences, venue);

        return new AddLessonCommand(moduleClassIndex, lesson);
    }
}
