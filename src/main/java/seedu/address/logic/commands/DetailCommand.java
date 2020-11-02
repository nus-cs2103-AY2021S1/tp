package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.student.Student;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.Detail;

public abstract class DetailCommand extends Command {

    public static final String COMMAND_WORD = "detail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, edits or deletes a Detail "
            + "from a student in Reeve. \n" + "SUPPORTED COMMANDS: add, edit, delete";

    /**
     * Creates a new Student, with the provided additional detail.
     * @param studentToAddDetail student to add detail to.
     * @param details new list of additional details.
     * @return updated Student.
     */
    public Student updateStudentDetail(Student studentToAddDetail, List<Detail> details) {
        Admin adminToAddDetail = studentToAddDetail.getAdmin();
        Admin updatedAdmin = new Admin(adminToAddDetail.getClassVenue(), adminToAddDetail.getClassTime(),
                adminToAddDetail.getFee(), adminToAddDetail.getPaymentDate(), details);
        Student updatedStudent = new Student(studentToAddDetail.getName(), studentToAddDetail.getPhone(),
                studentToAddDetail.getSchool(), studentToAddDetail.getYear(), updatedAdmin,
                studentToAddDetail.getQuestions(), studentToAddDetail.getExams(), studentToAddDetail.getAcademic());

        return updatedStudent;
    }
}
