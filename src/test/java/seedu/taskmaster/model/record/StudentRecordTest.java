package seedu.taskmaster.model.record;

import org.junit.jupiter.api.Test;
import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;

import static org.junit.jupiter.api.Assertions.*;

class StudentRecordTest {

    StudentRecord testRecord = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    StudentRecord testRecord2 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    StudentRecord testRecord3 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201167"));
    StudentRecord testRecord4 = new StudentRecord(
            new Name("Coh Ghiau Siak"), new NusnetId("e0201167"));

    @Test
    void getName() {
        assertEquals(new Name("Chiau Siak"), testRecord.getName());
    }

    @Test
    void getNusnetId() {
        assertEquals(new NusnetId("e0201166"), testRecord.getNusnetId());
    }

    @Test
    void getAttendanceType() {
        assertEquals(AttendanceType.NO_RECORD, testRecord.getAttendanceType());
    }

    @Test
    void getClassParticipation() {
        assertEquals(new ClassParticipation(), testRecord.getClassParticipation());
    }

    @Test
    void testToString() {
        assertEquals(
                String.format("%s|%s|%s",
                        testRecord.getNusnetId(),
                        testRecord.getAttendanceType().name(),
                        testRecord.getClassParticipation()),
                testRecord.toString());
    }

    @Test
    void testEquals() {
        assertTrue(testRecord.equals(testRecord));      // same instance
        assertTrue(testRecord.equals(testRecord2));     // same fields
        assertFalse(testRecord.equals(testRecord3));    // different NusnetId
        assertFalse(testRecord.equals(testRecord4));    // different name
        assertFalse(testRecord3.equals(testRecord4));   // different in both fields
    }
}