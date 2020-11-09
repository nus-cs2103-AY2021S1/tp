package tutorspet.logic.parser.lesson;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutorspet.logic.commands.lesson.DeleteLessonCommand.MESSAGE_USAGE;
import static tutorspet.logic.parser.ArgumentTokenizer.tokenize;
import static tutorspet.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static tutorspet.logic.parser.ParserUtil.parseIndex;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.lesson.DeleteLessonCommand;
import tutorspet.logic.parser.ArgumentMultimap;
import tutorspet.logic.parser.Parser;
import tutorspet.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteLessonCommand object.
 */
public class DeleteLessonCommandParser implements Parser<DeleteLessonCommand> {

    /**
     * Parses the given {@code String} of argument in the context of the DeleteLessonCommand
     * and returns a DeleteLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = tokenize(args, PREFIX_CLASS_INDEX, PREFIX_LESSON_INDEX);

        Index moduleClassIndex;
        Index lessonIndex;

        boolean isModuleClassIndexPresent = argMultimap.getValue(PREFIX_CLASS_INDEX).isPresent();
        boolean isLessonIndexPresent = argMultimap.getValue(PREFIX_LESSON_INDEX).isPresent();
        if (!isModuleClassIndexPresent || !isLessonIndexPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        try {
            moduleClassIndex = parseIndex(argMultimap.getValue(PREFIX_CLASS_INDEX).get());
            lessonIndex = parseIndex(argMultimap.getValue(PREFIX_LESSON_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        return new DeleteLessonCommand(moduleClassIndex, lessonIndex);
    }
}
