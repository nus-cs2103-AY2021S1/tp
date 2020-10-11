package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.student.Student;


/**
 * A utility class for Person.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_SCHOOL + student.getSchool().school + " ");
        sb.append(PREFIX_YEAR + String.valueOf(student.getYear().year) + " ");
        sb.append(PREFIX_VENUE + student.getAdmin().getClassVenue().venue + " ");
        //There needs to be a way to convert back and forth for the admin details
        sb.append(PREFIX_TIME + student.getAdmin().getClassTime().dayOfWeek.toString()
                + " " + student.getAdmin().getClassTime().startTime.toString().replace(":", "")
                + "-" + student.getAdmin().getClassTime().endTime.toString().replace(":", "") + " ");
        sb.append(PREFIX_FEE + String.valueOf(student.getAdmin().getFee().amount) + " ");
        sb.append(PREFIX_PAYMENT + "01/01/2001" + " ");
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getSchool().ifPresent(school -> sb.append(PREFIX_SCHOOL).append(school.school).append(" "));
        descriptor.getYear().ifPresent(year -> sb.append(PREFIX_YEAR).append(year.year).append(" "));

        return sb.toString();
    }
}
