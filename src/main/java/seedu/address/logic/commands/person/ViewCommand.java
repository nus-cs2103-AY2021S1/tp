package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ModelPerson;
import seedu.address.model.person.person.Person;

/**
 * View a contact by displaying its details in the side panel
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Displayed contact: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Displays the details of the contact identified by the index number used in the displayed contact list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Construct command given index of contact to view.
     * @param targetIndex specified index of contact to view.
     */
    public ViewCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(ModelPerson modelPerson) throws CommandException {
        requireNonNull(modelPerson);
        List<Person> lastShownList = modelPerson.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());
        modelPerson.setPersonInView(personToView);
        return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, personToView));
    }
}
