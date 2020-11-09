package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJobCommand.EditJobDescriptor;
import seedu.address.model.JobAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PersonAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.Job;
import seedu.address.testutil.EditJobDescriptorBuilder;
import seedu.address.testutil.JobBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for EditJobCommand.
 */
public class EditJobCommandTest {

    private Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Job editedJob = new JobBuilder().build();
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder(editedJob).build();
        EditJobCommand editJobCommand = new EditJobCommand(INDEX_FIRST_JOB, descriptor);

        String expectedMessage = String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(model.getPersonAddressBook(),
                new JobAddressBook(model.getJobAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastJob = Index.fromOneBased(model.getFilteredJobList().size());
        Job lastJob = model.getFilteredJobList().get(indexLastJob.getZeroBased());

        JobBuilder jobInList = new JobBuilder(lastJob);
        Job editedJob = jobInList.withJobTitle(VALID_JOB_TITLE_MAYBANK).withPhone(VALID_PHONE_MAYBANK)
                .withAddress(VALID_ADDRESS_MAYBANK).withPriority(VALID_PRIORITY_MAYBANK)
                .withTags(VALID_TAG_MAYBANK).build();

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_MAYBANK)
                .withPhone(VALID_PHONE_MAYBANK).withAddress(VALID_ADDRESS_MAYBANK)
                .withPriority(VALID_PRIORITY_MAYBANK).withTags(VALID_TAG_MAYBANK).build();
        EditJobCommand editJobCommand = new EditJobCommand(indexLastJob, descriptor);

        String expectedMessage = String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(model.getPersonAddressBook(),
                new JobAddressBook(model.getJobAddressBook()), new UserPrefs());
        expectedModel.setJob(lastJob, editedJob);

        assertCommandSuccess(editJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditJobCommand editJobCommand = new EditJobCommand(INDEX_FIRST_JOB, new EditJobDescriptor());
        Job editedJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());

        String expectedMessage = String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new PersonAddressBook(model.getPersonAddressBook()),
                new JobAddressBook(model.getJobAddressBook()), new UserPrefs());

        assertCommandSuccess(editJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job jobInFilteredList = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Job editedJob = new JobBuilder(jobInFilteredList).withJobTitle(VALID_JOB_TITLE_IRAS).build();
        EditJobCommand editJobCommand = new EditJobCommand(INDEX_FIRST_JOB,
                new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_IRAS).build());

        String expectedMessage = String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new PersonAddressBook(model.getPersonAddressBook()),
                new JobAddressBook(model.getJobAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateJobUnfilteredList_failure() {
        Job firstJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder(firstJob).build();
        EditJobCommand editJobCommand = new EditJobCommand(INDEX_SECOND_JOB, descriptor);

        assertCommandFailure(editJobCommand, model, EditJobCommand.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void execute_duplicateJobFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        // edit job in filtered list into a duplicate in address book
        Job jobInList = model.getJobAddressBook().getJobList().get(INDEX_SECOND_JOB.getZeroBased());
        EditJobCommand editJobCommand = new EditJobCommand(INDEX_FIRST_JOB,
                new EditJobDescriptorBuilder(jobInList).build());

        assertCommandFailure(editJobCommand, model, EditJobCommand.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void execute_invalidJobIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_MAYBANK).build();
        EditJobCommand editJobCommand = new EditJobCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidJobIndexFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_JOB);
        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJobAddressBook().getJobList().size());

        EditJobCommand editJobCommand = new EditJobCommand(outOfBoundIndex,
                new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_MAYBANK).build());

        assertCommandFailure(editJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditJobCommand standardCommand = new EditJobCommand(INDEX_FIRST_JOB, DESC_IRAS);

        // same values -> returns true
        EditJobDescriptor copyDescriptor = new EditJobDescriptor(DESC_IRAS);
        EditJobCommand commandWithSameValues = new EditJobCommand(INDEX_FIRST_JOB, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearJobCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditJobCommand(INDEX_SECOND_JOB, DESC_IRAS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditJobCommand(INDEX_FIRST_JOB, DESC_MAYBANK)));
    }

}
