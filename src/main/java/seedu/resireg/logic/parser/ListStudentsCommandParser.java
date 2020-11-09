package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.resireg.logic.commands.ListStudentsCommand;
import seedu.resireg.logic.commands.ListStudentsCommand.StudentFilter;
import seedu.resireg.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListStudentsCommand object
 */
public class ListStudentsCommandParser implements Parser<ListStudentsCommand> {
    private static final String MESSAGE_INVALID_COMMAND = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ListStudentsCommand.HELP.getFullMessage());

    /**
     * Parses the given {@code String} of arguments in the context of the ListStudentsCommand
     * and returns a ListStudentsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListStudentsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_FACULTY, PREFIX_STUDENT_ID);
        StudentFilter filter = new StudentFilter();

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            filter.addValidNames(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_NAME),
                    ParserUtil::parseName));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            filter.addValidPhones(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_PHONE),
                    ParserUtil::parsePhone));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            filter.addValidEmails(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_EMAIL),
                    ParserUtil::parseEmail));
        }
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            filter.addValidFaculties(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_FACULTY),
                    ParserUtil::parseFaculty));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            filter.addValidStudentIds(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_STUDENT_ID),
                    ParserUtil::parseStudentId));
        }

        return new ListStudentsCommand(filter);
    }

}
