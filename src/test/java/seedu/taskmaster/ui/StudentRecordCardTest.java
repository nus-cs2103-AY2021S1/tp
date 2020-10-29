package seedu.taskmaster.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;





class StudentRecordCardTest {
    private StudentRecord testRecord = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    private StudentRecord testRecord2 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    private StudentRecord testRecord3 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201167"));
    private StudentRecordCard testCard1 = new StudentRecordCard(testRecord, 1);
    private StudentRecordCard testCard2 = new StudentRecordCard(testRecord, 1);
    private StudentRecordCard testCard3 = new StudentRecordCard(testRecord2, 1);
    private StudentRecordCard testCard4 = new StudentRecordCard(testRecord3, 1);
    private StudentRecordCard testCard5 = new StudentRecordCard(testRecord3, 2);

    public static class AsNonApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // noop
        }
    }

    @BeforeAll
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(AsNonApp.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Test
    void testEquals() {
        assertTrue(testCard1.equals(testCard1)); // Same card
        assertTrue(testCard1.equals(testCard2)); // Same field
        assertTrue(testCard1.equals(testCard3)); // Equivalent StudentRecord
        assertFalse(testCard1.equals(testCard4)); // different StudentRecord
        assertFalse(testCard1.equals(testCard5)); // different id
        assertFalse(testCard5.equals(testCard4)); // different StudentRecord and id
    }
}
