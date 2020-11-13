package com.eva.testutil.staff;

import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_DATE;
import static com.eva.logic.parser.CliSyntax.PREFIX_DESC;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static com.eva.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import com.eva.commons.util.DateUtil;
import com.eva.logic.commands.AddStaffCommand;
import com.eva.logic.commands.CommentCommand;
import com.eva.logic.commands.DeleteStaffCommand;
import com.eva.model.comment.Comment;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class StaffUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddStaffCommand(Staff staff) {
        return AddStaffCommand.COMMAND_WORD + " " + getStaffDetails(staff);
    }

    public static String getDeleteStaffCommand(int index) {
        return DeleteStaffCommand.COMMAND_WORD + " " + getDeleteStaffDetails(index);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getStaffDetails(Staff staff) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + staff.getName().fullName + " ");
        sb.append(PREFIX_PHONE + staff.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + staff.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + staff.getAddress().value + " ");
        staff.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        staff.getComments().stream().forEach(
            s -> sb.append(PREFIX_COMMENT + " "
                    + PREFIX_TITLE + " " + s.getTitle().getTitleDescription() + " "
                    + PREFIX_DATE + " " + DateUtil.dateToString(s.getDate()) + " "
                    + PREFIX_DESC + " " + s.getDescription())
        );
        staff.getLeaves().stream().forEach(
            s -> sb.append(PREFIX_LEAVE + s.getStartDate().toString() + s.getEndDate().toString() + " ")
        );
        return sb.toString();
    }

    public static String getDeleteStaffDetails(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(index);
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    //For now, use comment person descriptor for now until we change editpersondescriptor
    public static String getEditStaffDescriptorDetails(CommentCommand.CommentPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getComments() != null) {
            Set<Comment> comments = descriptor.getComments();
            if (comments.isEmpty()) {
                sb.append(PREFIX_COMMENT);
            } else {
                comments.forEach(s -> sb.append(PREFIX_COMMENT).append(s.getTitle().getTitleDescription()).append(" "));
            }
        }
        if (descriptor.getLeaves() != null) {
            Set<Leave> leaves = descriptor.getLeaves();
            if (leaves.isEmpty()) {
                sb.append(PREFIX_LEAVE);
            } else {
                //TODO
                leaves.forEach(s -> sb.append(PREFIX_LEAVE).append(s.getStartDate()).append(" "));
            }
        }
        return sb.toString();
    }
}

