package seedu.taskmaster.testutil;

import static seedu.taskmaster.testutil.TypicalStudents.ALICE;
import static seedu.taskmaster.testutil.TypicalStudents.BENSON;

import seedu.taskmaster.model.record.StudentRecord;

public class TypicalStudentRecords {
    public static final StudentRecord ALICE_STUDENT_RECORD =
            new StudentRecord(ALICE.getName(), ALICE.getNusnetId());

    public static final StudentRecord BENSON_STUDENT_RECORD =
            new StudentRecord(BENSON.getName(), BENSON.getNusnetId());
}
