package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.LessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.Type;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class LessonCommandParser implements Parser<LessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION,
                        PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_DURATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonCommand.MESSAGE_USAGE));
        }
        LocalDate startDate;
        LocalDate endDate;
        LocalTime startTime;
        LocalTime endTime;
        DayOfWeek dayOfWeek;
        
        Description description = Description.defaultDescription();
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());

        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            startDate = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE).get());
        }
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            endDate = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE).get());
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        //Todo: Parse "duration" into dayOfWeek and startTime + endTime
        Type type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        
        Lesson lesson = new Lesson(title, description, startDate, type, tagList);

        return new LessonCommand(lesson);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
