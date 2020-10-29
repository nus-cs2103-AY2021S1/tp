package seedu.taskmaster.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;

class StudentRecordTest {
    private StudentRecord testRecord = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    private StudentRecord testRecord2 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201166"));
    private StudentRecord testRecord3 = new StudentRecord(
            new Name("Chiau Siak"), new NusnetId("e0201167"));
    private StudentRecord testRecord4 = new StudentRecord(
            new Name("Coh Ghiau Siak"), new NusnetId("e0201166"));

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
        assertTrue(testRecord.equals(testRecord)); // same instance
        assertTrue(testRecord.equals(testRecord2)); // same fields
        assertFalse(testRecord.equals("some string")); // not InstanceOf
        assertFalse(testRecord.equals(testRecord3)); // different NusnetId
        assertFalse(testRecord.equals(testRecord4)); // different name
        assertFalse(testRecord3.equals(testRecord4)); // different in both fields
    }
}
