package com.eva.logic.commands;

import static com.eva.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;

public class AddCommentCommand extends CommentCommand {
    public AddCommentCommand(Index index, CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
        super(index, commentPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //for now is staff because we only working with staff for now
        List<Staff> lastShownList = model.getFilteredStaffList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        //for now staff
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createAddEditedPerson(personToEdit, commentPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (editedPerson instanceof Staff) {
            model.setStaff((Staff) personToEdit, (Staff) editedPerson);
        } else {
            model.setPerson(personToEdit, editedPerson);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_COMMENT_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createAddEditedPerson(Person personToEdit,
                                                CommentCommand.CommentPersonDescriptor commentPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = commentPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = commentPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = commentPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = commentPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = commentPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Comment> updatedCommentsCommands = commentPersonDescriptor.getComments();
        Set<Comment> updatedComments = personToEdit.getComments();
        for (Comment comment: updatedCommentsCommands) {
            updatedComments.add(new Comment(comment.getDate(),
                    comment.getDescription(), comment.getTitle().getTitle()));
        }
        if (personToEdit instanceof Staff) {
            Set<Leave> updatedLeaves = commentPersonDescriptor.getLeaves();
            return new Staff(updatedName, updatedPhone, updatedEmail,
                    updatedAddress, updatedTags, updatedLeaves, updatedComments);
        }
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedComments);
    }


}
