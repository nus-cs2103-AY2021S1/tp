package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * An utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an AddStudentCommand string for adding the {@code student}.
     */
    public static String getAddStudentCommand(Student student) {
        return AddStudentCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().toString() + " ");
        sb.append(PREFIX_PHONE + student.getPhone().toString() + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().toString() + " ");
        sb.append(PREFIX_STUDENT_ID + student.getStudentId().toString() + " ");
        student.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();

        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME + name.toString() + " "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE + phone.toString() + " "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL + email.toString() + " "));
        descriptor.getStudentId().ifPresent(id -> sb.append(PREFIX_STUDENT_ID + id.toString() + " "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(tag -> sb.append(PREFIX_TAG + tag.tagName + " "));
            }
        }
        return sb.toString();
    }
}
