package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "contact delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the name used in the displayed person list.\n"
            + "Parameters: NAME (must be a valid name)\n"
            + "Example: " + COMMAND_WORD + " Roy";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Name targetName;

    public DeleteCommand(Name targetName) {
        this.targetName = targetName;
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
        assert filteredList.size() == 1;
        Person personToDelete = filteredList.get(0);
        model.deletePerson(personToDelete);

        // Update meeting book
        List<Meeting> filteredMeetingList = model.getFilteredMeetingList().stream()
                .filter(meeting -> meeting.getParticipants().contains(personToDelete)).map(meeting -> {
                    Set<Person> updatedMembers = new HashSet<>(meeting.getParticipants());
                    updatedMembers.remove(personToDelete);
                    Meeting updatedMeeting = new Meeting(meeting.getModule(), meeting.getMeetingName(),
                            meeting.getDate(), meeting.getTime(), updatedMembers);
                    model.setMeeting(meeting, updatedMeeting);
                    return updatedMeeting;
                }).collect(Collectors.toList());
        model.updatePersonInMeetingBook(personToDelete);
        model.updatePersonInModuleBook(personToDelete);

        // todo update module book
        //        List<Meeting> filteredModuleList = model.getFilteredModuleList().stream()
        //                .filter(module -> module.getClassmates().contains(personToDelete)).map(module -> {
        //                    Set<Person> updatedClassmates = new HashSet<>(module.getClassmates());
        //                    updatedClassmates.remove(personToDelete);
        //                    Module updatedModule = new Module(module.getModuleName(), updatedClassmates);
        //                    model.setModule(module, updatedModule);
        //                    return updatedModule;
        //                }).collect(Collectors.toList());

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetName.equals(((DeleteCommand) other).targetName)); // state check
    }
}
