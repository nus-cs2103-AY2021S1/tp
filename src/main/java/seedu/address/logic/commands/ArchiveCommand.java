package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ACTIVE;

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
 * Archives a person identified using its displayed index from the client list.
 */
public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_PERSON_SUCCESS = "Archived Person: %1$s";
    public static final String MESSAGE_DISABLE_IN_ARCHIVE_MODE = "This command is disabled in archive mode";

    private final Index targetIndex;

    public ArchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getIsArchiveMode()) {
            throw new CommandException(MESSAGE_DISABLE_IN_ARCHIVE_MODE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToArchive = lastShownList.get(targetIndex.getZeroBased());
        Person archivedPerson = createArchivedPerson(personToArchive);

        model.setPerson(personToArchive, archivedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_ACTIVE);

        return new CommandResult(String.format(MESSAGE_ARCHIVE_PERSON_SUCCESS, personToArchive));
    }

    private static Person createArchivedPerson(Person personToArchive) {
        assert personToArchive != null;
        assert !personToArchive.getIsArchive() : "Person to archive should not already be in archive";

        Name archivedName = personToArchive.getName();
        Phone archivedPhone = personToArchive.getPhone();
        Email archivedEmail = personToArchive.getEmail();
        Address archivedAddress = personToArchive.getAddress();
        Set<ClientSource> archivedClientSources = personToArchive.getClientSources();
        Note archivedNote = personToArchive.getNote();
        Priority archivedPriority = personToArchive.getPriority();
        Policy archivedPolicy = personToArchive.getPolicy();

        boolean archivedIsArchive = true;

        return new Person(
                archivedName,
                archivedPhone,
                archivedEmail,
                archivedAddress,
                archivedClientSources,
                archivedNote,
                archivedIsArchive,
                archivedPriority,
                archivedPolicy);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveCommand) other).targetIndex)); // state check
    }
}
