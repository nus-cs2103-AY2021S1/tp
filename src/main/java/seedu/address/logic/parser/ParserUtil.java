package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ScheduleViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Title;
import seedu.address.model.schedule.ScheduleViewMode;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;
import seedu.address.model.student.academic.question.Question;
import seedu.address.model.student.academic.question.SolvedQuestion;
import seedu.address.model.student.academic.question.UnsolvedQuestion;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Detail;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;

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
        return new Year(trimmedYear);
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
        if (!Detail.isValidDetail(trimmedDetail)) {
            throw new ParseException(Detail.MESSAGE_CONSTRAINTS);
        }
        return new Detail(trimmedDetail);
    }

    /**
     * Parses {@code Collection<String> details} into a {@code List<Detail>}.
     */
    public static List<Detail> parseDetails(Collection<String> details)
            throws ParseException {
        requireNonNull(details);
        final List<Detail> detailSet = new ArrayList<>();
        for (String detail : details) {
            detailSet.add(parseDetail(detail));
        }
        return detailSet;
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses {@code String input} into a {@code ScheduleViewMode}.
     * Case of the input string is ignored.
     *
     * @throws ParseException if the given {@code string} is of a valid view mode.
     */
    public static ScheduleViewMode parseViewMode(String input) throws ParseException {

        if (input.equalsIgnoreCase(ScheduleViewMode.DAILY.name())) {
            return ScheduleViewMode.DAILY;
        }

        if (input.equalsIgnoreCase(ScheduleViewMode.WEEKLY.name())) {
            return ScheduleViewMode.WEEKLY;
        }
        throw new ParseException(ScheduleViewCommand.MESSAGE_INVALID_VIEW_MODE);
    }

    /**
     * Parses a {@code String dateToViewSchedule} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException when input string does not follow the format
     */
    public static LocalDate parseViewDate(String dateToViewSchedule) throws ParseException {
        requireNonNull(dateToViewSchedule);
        try {
            return parseDate(dateToViewSchedule);
        } catch (ParseException e) {
            throw new ParseException(ScheduleViewCommand.MESSAGE_CONSTRAINTS);
        }

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
        if (!Exam.isValidExamName(trimmedExamName)) {
            throw new ParseException(Exam.MESSAGE_CONSTRAINTS);
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
     * Parses a {@code String attendanceStatus} into a {@code boolean}.
     * Leading and trailing whitespaces will be trimmed, and case is ignored.
     *
     * @throws ParseException if the given {@code attendanceStatus} is invalid.
     */
    public static boolean parseAttendanceStatus(String attendanceStatus) throws ParseException {
        requireNonNull(attendanceStatus);
        String formattedStatus = attendanceStatus.trim().toLowerCase();
        if (!Attendance.isValidStatus(formattedStatus)) {
            throw new ParseException(Attendance.STATUS_CONSTRAINTS);
        }
        return formattedStatus.equals(Attendance.PRESENT_STATUS);
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

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is not a valid date.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!DateUtil.isValidDate(trimmedDate)) {
            throw new ParseException(DateUtil.DATE_CONSTRAINTS);
        }
        return DateUtil.parseToDate(trimmedDate);
    }

}
