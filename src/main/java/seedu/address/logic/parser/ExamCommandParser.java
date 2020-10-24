package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_EXAM_COMMAND;
import static seedu.address.logic.commands.ExamCommand.MESSAGE_USAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.commands.ExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExamCommand object.
 */
public class ExamCommandParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the ExamCommand
     * and returns an ExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ExamCommand parseExamCommand(String args)
            throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddExamCommand.COMMAND_WORD:
            return new AddExamCommandParser().parse(arguments);

        case DeleteExamCommand.COMMAND_WORD:
            return new DeleteExamCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_EXAM_COMMAND);
        }
    }
}
