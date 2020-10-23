package seedu.address.logic.parser;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.commands.ExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_EXAM_COMMAND;
import static seedu.address.logic.commands.ExamCommand.MESSAGE_USAGE;

public class ExamCommandParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public ExamCommand parseExamCommand(String userInput)
            throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
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
