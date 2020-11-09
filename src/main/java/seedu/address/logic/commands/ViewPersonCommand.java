package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.information.Person;

/**
 * Displays a person on the right panel;
 */
public class ViewPersonCommand extends Command {

    public static final String COMMAND_WORD = "view can";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX(must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DISPLAY_PERSON_SUCCESS = "Displaying Candidate: %1$s";

    private final Index targetIndex;

    public ViewPersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> displayablePersons = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= displayablePersons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDisplay = displayablePersons.get(targetIndex.getZeroBased());
        model.setDisplayedPerson(personToDisplay);
        return new CommandResult(String.format(MESSAGE_DISPLAY_PERSON_SUCCESS, personToDisplay), false,
                false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPersonCommand // instanceof handles nulls
                && targetIndex.equals(((ViewPersonCommand) other).targetIndex)); // state check
    }
}
