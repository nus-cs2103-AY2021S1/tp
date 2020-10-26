package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.Reeve;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.SchoolType;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Detail;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;


/**
 * Contains utility methods for populating {@code Reeve} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        //Sample Exams
        Exam sampleExam1 = new Exam("CA2", "13/3/2020", new Score("34/50"));
        Exam sampleExam2 = new Exam("End of Year 2020", "5/11/2020", new Score("50/50"));
        Exam sampleExam3 = new Exam("Mid Year 2020", "26/7/2020", new Score("26/50"));

        return new Student[]{
            new Student(new Name("Alex Yeoh"), new Phone("87438807"),
                        new School("NUS High School"), new Year(SchoolType.SECONDARY, 4),
                        new Admin(new ClassVenue("Blk 30 Geylang Street 29, #06-40"),
                                new ClassTime("1 1400-1500"), new Fee("430"),
                                new PaymentDate("23/4/19"), getDetailList("clever")),
                        getQuestions("How do birds fly?"), getExams(sampleExam1, sampleExam2),
                        new Academic(getAttendance(new Attendance("14/04/1998", "attended",
                                new Feedback("attentive"))))),
            new Student(new Name("Bernice Yu"), new Phone("99272758"),
                        new School("Montford Secondary School"), new Year(SchoolType.SECONDARY, 4),
                        new Admin(new ClassVenue("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                                new ClassTime("1 1500-1600"), new Fee("50"),
                                new PaymentDate("30/6/20"), getDetailList()),
                        getSolvedQuestions("Read your textbook", "Explain heat flow."), getExams(),
                        new Academic(getAttendance(new Attendance("06/05/2010", "unattended",
                                new Feedback("was sick"))))),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new School("Raffles Girls School"), new Year(SchoolType.SECONDARY, 3),
                        new Admin(new ClassVenue("Blk 11 Ang Mo Kio Street 74, #11-04"),
                                new ClassTime("2 1900-1930"), new Fee("680"),
                                new PaymentDate("1/12/19"), getDetailList()),
                        getQuestions(), getExams(sampleExam3), new Academic(getAttendance(new Attendance(
                        "01/01/2020", "attended", new Feedback("prepared well"))))),
            new Student(new Name("David Li"), new Phone("91031282"),
                        new School("Anderson Primary School"), new Year(SchoolType.PRIMARY, 2),
                        new Admin(new ClassVenue("Blk 436 Serangoon Gardens Street 26, #16-43"),
                                new ClassTime("6 0800-0950"), new Fee("12"),
                                new PaymentDate("24/7/20"), getDetailList("friend")),
                        getQuestions("How do birds fly?", "Explain heat flow."), getExams(),
                        new Academic(getAttendance(new Attendance("26/10/2020", "unattended",
                                new Feedback("productive session"))))),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new School("National Junior College"), new Year(SchoolType.JC, 1),
                        new Admin(new ClassVenue("Blk 47 Tampines Street 20, #17-35"),
                                new ClassTime("3 1300-1400"), new Fee("0"),
                                new PaymentDate("7/4/20"), getDetailList("clever", "friend")),
                        getQuestions(), getExams(), new Academic(getAttendance(new Attendance("25/12/2020",
                        "attended", new Feedback("studied through christmas"))))),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new School("Catholic High School"), new Year(SchoolType.JC, 1),
                        new Admin(new ClassVenue("Blk 45 Aljunied Street 85, #11-31"),
                                new ClassTime("4 2000-2130"), new Fee("38"),
                                new PaymentDate("19/12/19"), getDetailList("rude")),
                        getQuestions(), getExams(sampleExam2, sampleExam3), new Academic(getAttendance(
                        new Attendance("20/04/98", "attended", new Feedback("unprepared")))))
        };
    }

    public static ReadOnlyReeve getSampleAddressBook() {
        Reeve sampleAb = new Reeve();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a {@code Detail} list containing the list of given strings.
     */
    public static List<Detail> getDetailList(String... strings) {
        return Arrays.stream(strings)
                .map(Detail::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a {@code Question} list containing the list of given strings.
     */
    public static List<Question> getQuestions(String... strings) {
        return Arrays.stream(strings)
                .map(UnsolvedQuestion::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of solved {@code Questions} containing the given strings.
     */
    public static List<Question> getSolvedQuestions(String solution, String... strings) {
        return Arrays.stream(strings)
                .map(string -> new SolvedQuestion(string, solution))
                .collect(Collectors.toList());
    }

    public static List<Exam> getExams(Exam... exams) {
        return Arrays.asList(exams);
    }

    /**
     * Returns a {@code Attendance} list containing the list of given attendences.
     */
    public static List<Attendance> getAttendance(Attendance... attendances) {
        return Arrays.asList(attendances);
    }
}
