package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_FACULTY,
                    PREFIX_STUDENT_ID, PREFIX_TAG);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_FACULTY, PREFIX_STUDENT_ID,
                PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.HELP.getFullMessage()));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Faculty faculty = ParserUtil.parseFaculty(argMultimap.getValue(PREFIX_FACULTY).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student student = new Student(name, phone, email, faculty, studentId, tagList);

        return new AddCommand(student);
    }
}
