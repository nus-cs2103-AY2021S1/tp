package seedu.taskmaster.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taskmaster.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.student.exceptions.DuplicateStudentException;
import seedu.taskmaster.model.student.exceptions.StudentNotFoundException;
import seedu.taskmaster.testutil.TypicalStudents;

public class StudentRecordListTest {
    private final Student studentInList = TypicalStudents.ALICE;
    private final Student studentNotInList = TypicalStudents.BENSON;
    private final StudentRecordList studentRecordList =
            StudentRecordListManager.of(Collections.singletonList(studentInList));

    @Test
    public void markStudentAttendance_idInList_success() {
        studentRecordList.markStudentAttendance(studentInList.getNusnetId(), AttendanceType.PRESENT);

        List<StudentRecord> expectedStudentRecords =
                Collections.singletonList(new StudentRecord(
                        studentInList.getName(),
                        studentInList.getNusnetId(),
                        AttendanceType.PRESENT,
                        new ClassParticipation()));
        StudentRecordList expectedList = new StudentRecordListManager();
        expectedList.setStudentRecords(expectedStudentRecords);

        assertEquals(studentRecordList, expectedList);
    }

    @Test
    public void markStudentAttendance_idNotInList_failure() {
        assertThrows(StudentNotFoundException.class, ()
            -> studentRecordList.markStudentAttendance(
                studentNotInList.getNusnetId(), AttendanceType.PRESENT));
    }

    @Test
    public void markAllStudents_idsInList_success() {
        studentRecordList.markAllStudents(
                Collections.singletonList(studentInList.getNusnetId()), AttendanceType.PRESENT);

        List<StudentRecord> expectedStudentRecords =
                Collections.singletonList(new StudentRecord(
                        studentInList.getName(),
                        studentInList.getNusnetId(),
                        AttendanceType.PRESENT,
                        new ClassParticipation()));
        StudentRecordList expectedList = new StudentRecordListManager();
        expectedList.setStudentRecords(expectedStudentRecords);

        assertEquals(studentRecordList, expectedList);
    }

    @Test
    public void markAllStudents_idsNotInList_failure() {
        assertThrows(StudentNotFoundException.class, ()
            -> studentRecordList.markAllStudents(
                    Collections.singletonList(studentNotInList.getNusnetId()), AttendanceType.PRESENT));
    }

    @Test
    public void setStudentRecords_nullStudentRecordListManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> studentRecordList.setStudentRecords((StudentRecordListManager) null));
    }

    @Test
    public void setStudentRecords_studentRecordList_replacesOwnListWithProvidedStudentRecordList() {
        List<StudentRecord> expectedStudentRecords =
                Collections.singletonList(new StudentRecord(
                        studentNotInList.getName(),
                        studentNotInList.getNusnetId(),
                        AttendanceType.PRESENT,
                        new ClassParticipation()));
        StudentRecordListManager expectedList = new StudentRecordListManager();
        expectedList.setStudentRecords(expectedStudentRecords);

        studentRecordList.setStudentRecords(expectedList);
        assertEquals(studentRecordList, expectedList);
    }

    @Test
    public void setStudentRecords_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentRecordList.setStudentRecords((List<StudentRecord>) null));
    }

    @Test
    public void setStudentRecords_list_replacesOwnListWithProvidedList() {
        List<StudentRecord> expectedStudentRecords =
                Collections.singletonList(new StudentRecord(
                        studentNotInList.getName(),
                        studentNotInList.getNusnetId(),
                        AttendanceType.PRESENT,
                        new ClassParticipation()));
        StudentRecordList expectedList = new StudentRecordListManager();
        expectedList.setStudentRecords(expectedStudentRecords);

        studentRecordList.setStudentRecords(expectedStudentRecords);
        assertEquals(studentRecordList, expectedList);
    }

    @Test
    public void setStudentRecords_listWithDuplicateStudentRecords_throwsDuplicateStudentException() {
        StudentRecord duplicateRecord = new StudentRecord(
                studentInList.getName(),
                studentInList.getNusnetId(),
                AttendanceType.PRESENT,
                new ClassParticipation());
        List<StudentRecord> listWithDuplicateStudentRecords =
                Arrays.asList(duplicateRecord, duplicateRecord);

        assertThrows(DuplicateStudentException.class, ()
            -> studentRecordList.setStudentRecords(listWithDuplicateStudentRecords));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> studentRecordList.asUnmodifiableObservableList().remove(0));
    }
}
