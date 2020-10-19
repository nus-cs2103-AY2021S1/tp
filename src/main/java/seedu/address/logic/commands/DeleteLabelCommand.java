package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class DeleteLabelCommand extends Command {
    public static final String COMMAND_WORD = "label delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the given tag(s) from the person identified by the name used in the displayed person list.\n"
            + "Parameters: NAME (must be name of person existing in ModDuke) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Roy "
            + PREFIX_TAG + "classmate";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Labelled Person: %1$s";
    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "The person does not have all the tags provided.";

    private final Name targetName;
    private final Set<Tag> tags;

    public DeleteLabelCommand(Name targetName, Set<Tag> tags) {
        this.targetName = targetName;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isValidContact = model.hasPersonName(targetName);

        if (!isValidContact) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
        }

        // Update address book
        List<Person> filteredList = model.getFilteredPersonList().stream()
                .filter(person -> person.isSameName(targetName)).collect(Collectors.toList());
        Person personToEdit = filteredList.get(0);
        Person editedPerson = createEditedPerson(personToEdit, tags);

        model.setPerson(personToEdit, editedPerson);

        // Update meeting book
        List<Meeting> filteredMeetingList = model.getFilteredMeetingList().stream()
                .filter(meeting -> meeting.getMembers().contains(personToEdit)).map(meeting -> {
                    Set<Person> updatedMembers = new HashSet<>(meeting.getMembers());
                    updatedMembers.remove(personToEdit);
                    updatedMembers.add(editedPerson);
                    Meeting updatedMeeting = new Meeting(meeting.getMeetingName(), meeting.getDate(),
                            meeting.getTime(), updatedMembers);
                    model.setMeeting(meeting, updatedMeeting);
                    return updatedMeeting;
                }).collect(Collectors.toList());

        // todo update module book

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code tags}.
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> tags) throws CommandException {
        assert personToEdit != null;

        if (tags.stream().allMatch(tag -> personToEdit.getTags().contains(tag))) {
            Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
            updatedTags.removeAll(tags);
            return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), updatedTags);
        } else {
            throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLabelCommand // instanceof handles nulls
                && targetName.equals(((DeleteLabelCommand) other).targetName)); // state check
    }
}
