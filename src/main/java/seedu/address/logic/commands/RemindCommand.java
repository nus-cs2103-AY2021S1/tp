package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remind;
import seedu.address.model.tag.Tag;

/**
 * Sets reminders for an assignment identified using it's displayed index from the address book.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the assignment identified by the index number used in the displayed assignment list."
            + " Tagged assignments are permanently displayed.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMIND_ASSIGNMENT_SUCCESS = "Set reminder for Assignment: %1$s";
    public static final String MESSAGE_REMINDED_ASSIGNMENT = "This assignment already has reminders set.";

    private final Index targetIndex;

    /**
     * Constructs a RemindCommand to set reminders to the specified assignment.
     * @param targetIndex index of the person in the filtered person list to edit
     */
    public RemindCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person assignmentToRemind = lastShownList.get(targetIndex.getZeroBased());

        if (assignmentToRemind.isReminded() && model.hasPerson(assignmentToRemind)) {
            throw new CommandException(MESSAGE_REMINDED_ASSIGNMENT);
        }

        assert(!assignmentToRemind.isReminded());
        Person remindedAssignment = createRemindedAssignment(assignmentToRemind);

        model.setPerson(assignmentToRemind, remindedAssignment);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMIND_ASSIGNMENT_SUCCESS, remindedAssignment));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code assignmentToRemind}.
     */
    private static Person createRemindedAssignment(Person assignmentToRemind) {
        assert assignmentToRemind != null;

        Name updatedName = assignmentToRemind.getName();
        Phone updatedPhone = assignmentToRemind.getPhone();
        Email updatedEmail = assignmentToRemind.getEmail();
        Address updatedAddress = assignmentToRemind.getAddress();
        Set<Tag> updatedTags = assignmentToRemind.getTags();
        Remind updatedRemind = assignmentToRemind.getRemind().setReminder();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedRemind);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && targetIndex.equals(((RemindCommand) other).targetIndex)); // state check
    }
}
