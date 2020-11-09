package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARCHIVE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalClientListWithArchive;
import static seedu.address.testutil.TypicalPolicies.getTypicalPolicyList;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
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
 * Contains integration tests (interaction with the Model) and unit tests for {@code UnarchiveCommand}.
 * Note that integration test with FindCommand, UndoCommand, RedoCommand are not done here.
 */
public class UnarchiveCommandTest {

    private Model model = new ModelManager(getTypicalClientListWithArchive(), new UserPrefs(), getTypicalPolicyList());

    @BeforeEach
    public void setModelToArchiveMode() {
        model.setIsArchiveMode(true);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);
    }

    @Test
    public void execute_validIndex_success() {
        Person personToUnarchive = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_PERSON_SUCCESS, personToUnarchive);

        ModelManager expectedModel = new ModelManager(model.getClientList(), new UserPrefs(), model.getPolicyList());
        expectedModel.setIsArchiveMode(true);
        Person unarchivedPerson = createUnarchivedPerson(personToUnarchive);
        expectedModel.setPerson(personToUnarchive, unarchivedPerson);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE);

        assertCommandSuccess(unarchiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_inActiveMode_throwsCommandException() {
        model.setIsArchiveMode(false);
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(unarchiveCommand, model, UnarchiveCommand.MESSAGE_DISABLE_IN_ACTIVE_MODE);
    }

    @Test
    public void equals() {
        UnarchiveCommand unarchiveFirstCommand = new UnarchiveCommand(INDEX_FIRST_PERSON);
        UnarchiveCommand unarchiveSecondCommand = new UnarchiveCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommand));

        // same values -> returns true
        UnarchiveCommand unarchiveFirstCommandCopy = new UnarchiveCommand(INDEX_FIRST_PERSON);
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(unarchiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unarchiveFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unarchiveFirstCommand.equals(unarchiveSecondCommand));
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

}
