package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ArchiveStatus;
import seedu.address.model.person.Person;


/**
 * Unarchives a person identified using it's displayed index from the address book.
 */
public class UnarchiveCommand extends Command {

    public static final String COMMAND_WORD = "c-unarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNARCHIVE_PERSON_SUCCESS = "Unarchived Person: %1$s";
    public static final String MESSAGE_PERSON_ALREADY_ACTIVE = "This person is already in the active list!";

    private final Index targetIndex;

    /**
     * Constructs an UnarchiveCommand.
     *
     * @param targetIndex the index number shown in the displayed person list.
     */
    public UnarchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnarchive = lastShownList.get(targetIndex.getZeroBased());
        ArchiveStatus currentStateOfPerson = personToUnarchive.getArchiveStatus();

        if (!currentStateOfPerson.archiveStatus) {
            throw new CommandException(String.format(MESSAGE_PERSON_ALREADY_ACTIVE));
        }

        Person unarchivedPerson = personToUnarchive.unarchive();
        model.setPerson(personToUnarchive, unarchivedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ARCHIVED_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNARCHIVE_PERSON_SUCCESS, personToUnarchive.getName()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnarchiveCommand // instanceof handles nulls
                && targetIndex.equals(((UnarchiveCommand) other).targetIndex)); // state check
    }
}
