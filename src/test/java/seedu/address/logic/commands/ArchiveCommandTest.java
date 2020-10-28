package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ACTIVE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalClientListWithArchive;
import static seedu.address.testutil.TypicalPolicies.getTypicalPolicyList;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
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
 * Contains integration tests (interaction with the Model) and unit tests for {@code ArchiveCommand}.
 * Note that integration test with FindCommand, UndoCommand, RedoCommand are not done here.
 */
public class ArchiveCommandTest {

    private Model model = new ModelManager(getTypicalClientListWithArchive(), new UserPrefs(), getTypicalPolicyList());

    @Test
    public void execute_validIndex_success() {
        Person personToArchive = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_PERSON_SUCCESS, personToArchive);

        ModelManager expectedModel = new ModelManager(model.getClientList(), new UserPrefs(), model.getPolicyList());
        Person archivedPerson = createArchivedPerson(personToArchive);
        expectedModel.setPerson(personToArchive, archivedPerson);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_ACTIVE);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_inArchiveMode_throwsCommandException() {
        model.setIsArchiveMode(true);
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(archiveCommand, model, ArchiveCommand.MESSAGE_DISABLE_IN_ARCHIVE_MODE);
    }

    @Test
    public void equals() {
        ArchiveCommand archiveFirstCommand = new ArchiveCommand(INDEX_FIRST_PERSON);
        ArchiveCommand archiveSecondCommand = new ArchiveCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(archiveFirstCommand.equals(archiveFirstCommand));

        // same values -> returns true
        ArchiveCommand archiveFirstCommandCopy = new ArchiveCommand(INDEX_FIRST_PERSON);
        assertTrue(archiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(archiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(archiveFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(archiveFirstCommand.equals(archiveSecondCommand));
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

}
