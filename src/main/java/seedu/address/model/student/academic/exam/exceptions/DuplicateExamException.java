package seedu.address.model.student.academic.exam.exceptions;

/**
 * Signals that the operation will result in duplicate exams for a specific student (Exams are considered duplicates if they have the same
 * name).
 */
public class DuplicateExamException extends RuntimeException{
    public DuplicateExamException() {
        super("Operation would result in duplicate exams");
    }
}
