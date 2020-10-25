package seedu.taskmaster.logic.parser;

import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmaster.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.taskmaster.logic.commands.AddCommand;
import seedu.taskmaster.logic.commands.NewSessionCommand;
import seedu.taskmaster.logic.parser.exceptions.ParseException;
import seedu.taskmaster.model.record.StudentRecordList;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionDateTime;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.student.Email;
import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.student.Telegram;
import seedu.taskmaster.model.tag.Tag;

/**
 * Parses input arguments and creates a new NewSessionCommand object
 */
public class NewSessionCommandParser implements Parser<NewSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * NewSessionCommand and returns an NewSessionCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public NewSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_SESSION_NAME, PREFIX_SESSION_DATE_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSION_NAME, PREFIX_SESSION_DATE_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewSessionCommand.MESSAGE_USAGE));
        }

        SessionName sessionName = ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSION_NAME).get());
        SessionDateTime sessionDateTime = ParserUtil.parseSessionDateTime(argMultimap.getValue(PREFIX_SESSION_DATE_TIME).get());
        return new NewSessionCommand(sessionName, sessionDateTime);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

