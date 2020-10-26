package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.student.Student;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.Admin;

public abstract class AdditionalDetailCommand extends Command {

    public static final String COMMAND_WORD = "detail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, edits or deletes an Additional Detail "
            + "from a student in Reeve. ";

    /**
     * Creates a new Student, with the provided additional detail.
     * @param studentToAddDetail student to add detail to.
     * @param details new list of additional details.
     * @return updated Student.
     */
    public Student updateStudentDetail(Student studentToAddDetail, List<AdditionalDetail> details) {
        Admin adminToAddDetail = studentToAddDetail.getAdmin();
        Admin updatedAdmin = new Admin(adminToAddDetail.getClassVenue(), adminToAddDetail.getClassTime(),
                adminToAddDetail.getFee(), adminToAddDetail.getPaymentDate(), details);
        Student updatedStudent = new Student(studentToAddDetail.getName(), studentToAddDetail.getPhone(),
                studentToAddDetail.getSchool(), studentToAddDetail.getYear(), updatedAdmin,
                studentToAddDetail.getQuestions(), studentToAddDetail.getExams());

        return updatedStudent;
    }
}
