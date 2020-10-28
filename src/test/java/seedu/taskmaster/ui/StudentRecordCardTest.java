package seedu.taskmaster.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;

import static org.junit.jupiter.api.Assertions.*;

class StudentRecordCardTest {
    public static class AsNonApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // noop
        }
    }

    @BeforeAll
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(AsNonApp.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }


    StudentRecord testRecord = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    StudentRecord testRecord2 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    StudentRecord testRecord3 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201167"));

    StudentRecordCard testCard1 = new StudentRecordCard(testRecord, 1);
    StudentRecordCard testCard2 = new StudentRecordCard(testRecord, 1);
    StudentRecordCard testCard3 = new StudentRecordCard(testRecord2, 1);
    StudentRecordCard testCard4 = new StudentRecordCard(testRecord3, 1);
    StudentRecordCard testCard5 = new StudentRecordCard(testRecord3, 2);



    @Test
    void testEquals() {
        assertTrue(testCard1.equals(testCard1));    // Same card
        assertTrue(testCard1.equals(testCard2));    // Same field
        assertTrue(testCard1.equals(testCard3));    // Equivalent StudentRecord
        assertFalse(testCard1.equals(testCard4));   // different StudentRecord
        assertFalse(testCard1.equals(testCard5));   // different id
        assertFalse(testCard5.equals(testCard4));   // different StudentRecord and id
    }
}