package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.SchoolType;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Detail;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String school} into a {@code School}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static School parseSchool(String school) throws ParseException {
        requireNonNull(school);
        String trimmedSchool = school.trim();
        if (!School.isValidSchool(trimmedSchool)) {
            throw new ParseException(School.MESSAGE_CONSTRAINTS);
        }
        return new School(trimmedSchool);
    }

    /**
     * Parses a {@code String year} into a {@code Year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!Year.isValidYear(trimmedYear)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        final Matcher matcher = Year.YEAR_FORMAT.matcher(year.trim());
        boolean isMatched = matcher.matches();
        assert isMatched;
        String schoolTypeString = matcher.group("school").trim().toLowerCase();
        String levelString = matcher.group("level").trim().toLowerCase();

        SchoolType schoolType = parseSchoolType(schoolTypeString);
        Integer level = Integer.parseInt(levelString);

        return new Year(schoolType, level);
    }

    /**
     * Parses a {@code String schoolType} into a {@code SchoolType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code schoolType} is invalid.
     */
    public static SchoolType parseSchoolType(String schoolType) throws ParseException {
        requireNonNull(schoolType);
        String trimmed = schoolType.trim().toLowerCase();
        if (!SchoolType.isValidSchoolType(trimmed)) {
            throw new ParseException(SchoolType.SCHOOL_TYPE_CONSTRANTS);
        }
        checkArgument(SchoolType.isValidSchoolType(trimmed), SchoolType.SCHOOL_TYPE_CONSTRANTS);
        return SchoolType.LOOKUP_TABLE.get(trimmed);
    }

    /**
     * Parses a {@code String question} into an {@code UnsolvedQuestion}.
     */
    public static UnsolvedQuestion parseQuestion(String question) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!Question.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(Question.MESSAGE_CONSTRAINTS);
        }
        return new UnsolvedQuestion(trimmedQuestion);
    }

    /**
     * Parses a {@code String solution} a trimmed {@code solution}.
     */
    public static String parseSolution(String solution) throws ParseException {
        requireNonNull(solution);
        String trimmedSolution = solution.trim();
        if (!SolvedQuestion.isValidSolution(trimmedSolution)) {
            throw new ParseException(SolvedQuestion.MESSAGE_SOLUTION_CONSTRAINTS);
        }
        return trimmedSolution;
    }

    /**
     * Parses a {@code String venue} into a {@code ClassVenue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static ClassVenue parseClassVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        if (!ClassVenue.isValidClassVenue(trimmedVenue)) {
            throw new ParseException(ClassVenue.MESSAGE_CONSTRAINTS);
        }
        return new ClassVenue(trimmedVenue);
    }

    /**
     * Parses a {@code String time} into a {@code ClassTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static ClassTime parseClassTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!ClassTime.isValidClassTime(trimmedTime)) {
            throw new ParseException(ClassTime.MESSAGE_CONSTRAINTS);
        } else if (!ClassTime.isValidStartAndEndTime(trimmedTime)) {
            throw new ParseException(ClassTime.TIME_CONSTRAINTS);
        }
        return new ClassTime(trimmedTime);
    }

    /**
     * Parses a {@code String fee} into a {@code Fee}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code fee} is invalid.
     */
    public static Fee parseFee(String fee) throws ParseException {
        requireNonNull(fee);
        String trimmedFee = fee.trim();
        if (!Fee.isValidFee(trimmedFee)) {
            throw new ParseException(Fee.MESSAGE_CONSTRAINTS);
        }
        return new Fee(trimmedFee);
    }

    /**
     * Parses a {@code String paymentDate} into a {@code PaymentDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code paymentDate} is invalid.
     */
    public static PaymentDate parsePaymentDate(String paymentDate) throws ParseException {
        requireNonNull(paymentDate);
        String trimmedPaymentDate = paymentDate.trim();
        if (!PaymentDate.isValidDate(trimmedPaymentDate)) {
            throw new ParseException(PaymentDate.MESSAGE_CONSTRAINTS);
        }
        return new PaymentDate(trimmedPaymentDate);
    }

    /**
     * Parses a {@code String detail} into a {@code Detail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code detail} is invalid.
     */
    public static Detail parseDetail(String detail) throws ParseException {
        requireNonNull(detail);
        String trimmedDetail = detail.trim();
        if (!Detail.isValidAdditionalDetail(trimmedDetail)) {
            throw new ParseException(Detail.MESSAGE_CONSTRAINTS);
        }
        return new Detail(trimmedDetail);
    }

    /**
     * Parses {@code Collection<String> details} into a {@code List<Detail>}.
     */
    public static List<Detail> parseDetails(Collection<String> additionalDetails)
            throws ParseException {
        requireNonNull(additionalDetails);
        final List<Detail> detailSet = new ArrayList<>();
        for (String detail : additionalDetails) {
            detailSet.add(parseDetail(detail));
        }
        return detailSet;
    }

    /**
     * Parses a {@code String examDate} into a {@code Exam} formatted {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code examDate} is invalid.
     */
    public static String parseExamDate(String examDate) throws ParseException {
        requireNonNull(examDate);
        String trimmedExamDate = examDate.trim();
        if (!Exam.isValidDate(trimmedExamDate)) {
            throw new ParseException(AddExamCommand.MESSAGE_EXAM_INVALID_DATE);
        }
        return trimmedExamDate;
    }

    /**
     * Parses a {@code String examName} into a {@code Exam} format {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code examName} is invalid.
     */
    public static String parseExamName(String examName) throws ParseException {
        requireNonNull(examName);
        String trimmedExamName = examName.trim();
        if (trimmedExamName.isEmpty()) {
            throw new ParseException(AddExamCommand.MESSAGE_EXAM_INVALID_NAME);
        }
        return trimmedExamName;
    }

    /**
     * Parses a {@code String score} into a {@code Score}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code score} is invalid.
     */
    public static Score parseScore(String score) throws ParseException {
        requireNonNull(score);
        String trimmedScore = score.trim();
        if (!Score.isValidExamScore(trimmedScore)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }
        return new Score(trimmedScore);
    }

    /**
     * Parses a {@code String lessonDate} into a {@code Attendance} formatted {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lessonDate} is invalid.
     */
    public static String parseAttendanceDate(String lessonDate) throws ParseException {
        requireNonNull(lessonDate);
        String trimmedLessonDate = lessonDate.trim();
        if (!Attendance.isValidDate(trimmedLessonDate)) {
            throw new ParseException(Attendance.DATE_CONSTRAINTS);
        }
        return trimmedLessonDate;
    }

    /**
     * Parses a {@code String attendanceStatus} into a {@code Attendance} formatted {@code String}.
     * @throws ParseException if the given {@code attendanceStatus} is invalid.
     */
    public static String parseAttendanceStatus(String attendanceStatus) throws ParseException {
        requireNonNull(attendanceStatus);
        String formattedStatus = attendanceStatus.trim().toLowerCase();
        if (!Attendance.isValidAttendanceStatus(formattedStatus)) {
            throw new ParseException(Attendance.STATUS_CONSTRAINTS);
        }
        return formattedStatus;
    }

    /**
     * Parses a {@code String score} into a {@code Feedback}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code feedback} is invalid.
     */
    public static Feedback parseFeedback(String feedback) throws ParseException {
        requireNonNull(feedback);
        String trimmedFeedback = feedback.trim();
        if (!Feedback.isValidFeedback(trimmedFeedback)) {
            throw new ParseException(Feedback.MESSAGE_CONSTRAINTS);
        }
        return new Feedback(trimmedFeedback);
    }

}
