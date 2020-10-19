package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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

public class ClearLabelCommand extends Command {
    public static final String COMMAND_WORD = "label clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all labels of the person specified.\n"
            + "Parameters: NAME (must be name of person existing in ModDuke) "
            + "Example: " + COMMAND_WORD + " "
            + "Roy";

    private final Name targetName;

    /**
     * @param targetName the specified person
     */
    public ClearLabelCommand(Name targetName) {
        requireNonNull(targetName);
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isValidContact = model.hasPersonName(targetName);
        if (!isValidContact) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
        }

        List<Person> filteredList = model.getFilteredPersonList().stream()
                .filter(person -> person.isSameName(targetName)).collect(Collectors.toList());
        Person personToClear = filteredList.get(0);
        Person clearedPerson = createClearedPerson(personToClear);
        model.setPerson(personToClear, clearedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // update meeting book
        List<Meeting> filteredMeetingList = model.getFilteredMeetingList().stream()
                .filter(meeting -> meeting.getMembers().contains(personToClear)).map(meeting -> {
                    Set<Person> updatedMembers = new HashSet<>(meeting.getMembers());
                    updatedMembers.remove(personToClear);
                    updatedMembers.add(clearedPerson);
                    Meeting updatedMeeting = new Meeting(meeting.getMeetingName(), meeting.getDate(),
                            meeting.getTime(), updatedMembers);
                    model.setMeeting(meeting, updatedMeeting);
                    return updatedMeeting;
                }).collect(Collectors.toList());

        // todo update module book

        return new CommandResult(String.format("All labels of person '%s' have been cleared!", targetName.toString()));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToLabel}
     * edited with {@code labelPersonDescriptor}.
     */
    private static Person createClearedPerson(Person personToClear) {
        assert personToClear != null;

        return new Person(personToClear.getName(), personToClear.getPhone(), personToClear.getEmail(), new HashSet<>());
    }
}
