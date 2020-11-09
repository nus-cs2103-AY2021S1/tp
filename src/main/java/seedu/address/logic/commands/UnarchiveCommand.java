package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARCHIVE;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.policy.Policy;

/**
 * Unarchives a person identified using its displayed index from the client list.
 */
public class UnarchiveCommand extends Command {

    public static final String COMMAND_WORD = "unarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNARCHIVE_PERSON_SUCCESS = "Unarchived Person: %1$s";
    public static final String MESSAGE_DISABLE_IN_ACTIVE_MODE = "This command is disabled in active mode";

    private final Index targetIndex;

    public UnarchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getIsArchiveMode()) {
            throw new CommandException(MESSAGE_DISABLE_IN_ACTIVE_MODE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnarchive = lastShownList.get(targetIndex.getZeroBased());
        Person unarchivedPerson = createUnarchivedPerson(personToUnarchive);

        model.setPerson(personToUnarchive, unarchivedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);

        return new CommandResult(String.format(MESSAGE_UNARCHIVE_PERSON_SUCCESS, personToUnarchive));
    }

    private static Person createUnarchivedPerson(Person personToUnarchive) {
        assert personToUnarchive != null;
        assert personToUnarchive.getIsArchive() : "Person to unarchive should not already be active";

        Name unarchivedName = personToUnarchive.getName();
        Phone unarchivedPhone = personToUnarchive.getPhone();
        Email unarchivedEmail = personToUnarchive.getEmail();
        Address unarchivedAddress = personToUnarchive.getAddress();
        Set<ClientSource> unarchivedClientSources = personToUnarchive.getClientSources();
        Note unarchivedNote = personToUnarchive.getNote();
        Priority unarchivedPriority = personToUnarchive.getPriority();
        Policy unarchivedPolicy = personToUnarchive.getPolicy();

        boolean unarchivedIsArchive = false;

        return new Person(
                unarchivedName,
                unarchivedPhone,
                unarchivedEmail,
                unarchivedAddress,
                unarchivedClientSources,
                unarchivedNote,
                unarchivedIsArchive,
                unarchivedPriority,
                unarchivedPolicy);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnarchiveCommand // instanceof handles nulls
                && targetIndex.equals(((UnarchiveCommand) other).targetIndex)); // state check
    }
}
