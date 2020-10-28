package seedu.taskmaster.testutil;

import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NUSNETID_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_NUSNETID_BOB;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.taskmaster.model.Taskmaster;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionDateTime;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE =
            new StudentBuilder()
            .withName("Alice Pauline")
            .withNusnetId("e0123456")
            .withEmail("alice@example.com")
            .withTelegram("94351253")
            .withTags("friends").build();

    public static final Student BENSON =
            new StudentBuilder()
            .withName("Benson Meier")
            .withNusnetId("e0456789")
            .withEmail("johnd@example.com")
            .withTelegram("98765432")
            .withTags("owesMoney", "friends").build();

    public static final Student CARL =
            new StudentBuilder()
                    .withName("Carl Kurz")
                    .withTelegram("95352563")
                    .withEmail("heinz@example.com")
                    .withNusnetId("e0312654").build();

    public static final Student DANIEL =
            new StudentBuilder()
                    .withName("Daniel Meier")
                    .withTelegram("87652533")
                    .withEmail("cornelia@example.com")
                    .withNusnetId("e0987465")
                    .withTags("friends").build();

    public static final Student ELLE = new StudentBuilder()
            .withName("Elle Meyer")
            .withTelegram("9482224")
            .withEmail("werner@example.com")
            .withNusnetId("e0946875").build();

    public static final Student FIONA = new StudentBuilder()
            .withName("Fiona Kunz")
            .withTelegram("9482427")
            .withEmail("lydia@example.com")
            .withNusnetId("e0319843").build();

    public static final Student GEORGE = new StudentBuilder()
            .withName("George Best")
            .withTelegram("9482442")
            .withEmail("anna@example.com")
            .withNusnetId("e0731894").build();

    // Manually added
    public static final Student HOON = new StudentBuilder()
            .withName("Hoon Meier")
            .withTelegram("8482424")
            .withEmail("stefan@example.com")
            .withNusnetId("e0134679").build();

    public static final Student IDA = new StudentBuilder()
            .withName("Ida Mueller")
            .withTelegram("8482131")
            .withEmail("hans@example.com")
            .withNusnetId("e0235689").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder()
            .withName(VALID_NAME_AMY)
            .withTelegram(VALID_TELEGRAM_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withNusnetId(VALID_NUSNETID_AMY)
            .withTags(VALID_TAG_FRIEND).build();

    public static final Student BOB = new StudentBuilder()
            .withName(VALID_NAME_BOB)
            .withTelegram(VALID_TELEGRAM_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withNusnetId(VALID_NUSNETID_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code Taskmaster} with all the typical students.
     */
    public static Taskmaster getTypicalTaskmaster() {
        Taskmaster typicalTaskmaster = new Taskmaster();
        for (Student student : getTypicalStudents()) {
            typicalTaskmaster.addStudent(student);
        }
        Session typicalSession = new Session(
                new SessionName("Typical session"),
                new SessionDateTime(LocalDateTime.of(2020, 1, 1, 12, 0)),
                getTypicalStudents());
        typicalTaskmaster.addSession(typicalSession);
        return typicalTaskmaster;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
