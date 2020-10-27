package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.COMPULSORY_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.student.admin.PaymentDate.TODAY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Detail;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.model.student.question.Question;


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
                ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        if (!arePrefixesPresent(argMultimap, COMPULSORY_PREFIXES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        School school = ParserUtil.parseSchool(argMultimap.getValue(PREFIX_SCHOOL).get());
        Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        ClassVenue classVenue = ParserUtil.parseClassVenue(argMultimap.getValue(PREFIX_VENUE).get());
        ClassTime classTime = ParserUtil.parseClassTime(argMultimap.getValue(PREFIX_TIME).get());
        Fee fee = ParserUtil.parseFee(argMultimap.getValue(PREFIX_FEE).get());
        PaymentDate paymentDate = ParserUtil.parsePaymentDate(argMultimap.getValue(PREFIX_PAYMENT).orElse(TODAY));
        List<Detail> detailList =
                ParserUtil.parseDetails(argMultimap.getAllValues(PREFIX_DETAILS));

        Admin admin = new Admin(classVenue, classTime, fee, paymentDate, detailList);
        List<Question> questions = new ArrayList<>();
        ArrayList<Exam> exams = new ArrayList<>();
        List<Attendance> attendanceList = new ArrayList<>();
        Academic academic = new Academic(attendanceList);
        Student student = new Student(name, phone, school, year, admin, questions, exams, academic);
        return new AddCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
