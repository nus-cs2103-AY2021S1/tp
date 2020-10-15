package seedu.taskmaster.testutil;

import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_NUSNETID;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import seedu.taskmaster.logic.commands.AddCommand;
import seedu.taskmaster.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_TELEGRAM + student.getTelegram().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_NUSNETID + student.getNusnetId().value + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(PREFIX_TELEGRAM).append(telegram.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getNusnetId().ifPresent(address -> sb.append(PREFIX_NUSNETID).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
