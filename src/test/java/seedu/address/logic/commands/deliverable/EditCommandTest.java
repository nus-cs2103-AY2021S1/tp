package seedu.address.logic.commands.deliverable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.DESC_DELIVERABLE_A;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.DESC_DELIVERABLE_B;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_MILESTONE_A;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_TITLE_A;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_TITLE_B;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.showDeliverableAtIndex;
import static seedu.address.logic.commands.deliverable.EditCommand.EditDeliverableDescriptor;
import static seedu.address.testutil.TypicalDeliverables.getTypicalDeliverableBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ModelDeliverableManager;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.testutil.DeliverableBuilder;
import seedu.address.testutil.EditDeliverableDescriptorBuilder;


class EditCommandTest {
    private ModelDeliverable modelDeliverable = new ModelDeliverableManager(
            getTypicalDeliverableBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Deliverable editedDeliverable = new DeliverableBuilder().build();
        EditDeliverableDescriptor descriptor =
                new EditDeliverableDescriptorBuilder(editedDeliverable).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_DELIVERABLE_SUCCESS, editedDeliverable);

        ModelDeliverable expectedModelDeliverable = new ModelDeliverableManager(
                new DeliverableBook(modelDeliverable.getDeliverableBook()), new UserPrefs());
        expectedModelDeliverable.setDeliverable(
                modelDeliverable.getFilteredDeliverableList().get(0), editedDeliverable);

        assertCommandSuccess(editCommand, modelDeliverable, expectedMessage, expectedModelDeliverable);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastDeliverable = Index.fromOneBased(modelDeliverable.getFilteredDeliverableList().size());
        Deliverable lastDeliverable = modelDeliverable.getFilteredDeliverableList().get(
                indexLastDeliverable.getZeroBased());

        DeliverableBuilder deliverableInList = new DeliverableBuilder(lastDeliverable);
        Deliverable editedDeliverable = deliverableInList.withTitle(VALID_TITLE_A)
                .withMilestone(VALID_MILESTONE_A).build();

        EditDeliverableDescriptor descriptor = new EditDeliverableDescriptorBuilder()
                .withTitle(VALID_TITLE_A).withMilestone(VALID_MILESTONE_A).build();

        EditCommand editCommand = new EditCommand(indexLastDeliverable, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_DELIVERABLE_SUCCESS, editedDeliverable);

        ModelDeliverable expectedModelDeliverable = new ModelDeliverableManager(
                new DeliverableBook(modelDeliverable.getDeliverableBook()), new UserPrefs());
        expectedModelDeliverable.setDeliverable(lastDeliverable, editedDeliverable);

        assertCommandSuccess(editCommand, modelDeliverable, expectedMessage, expectedModelDeliverable);
    }

    @Test
    public void execute_unchangedDeliverable_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST, new EditDeliverableDescriptor());
        Deliverable editedDeliverable = modelDeliverable.getFilteredDeliverableList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_UNCHANGED, editedDeliverable);

        ModelDeliverable expectedModelDeliverable = new ModelDeliverableManager(
                new DeliverableBook(modelDeliverable.getDeliverableBook()), new UserPrefs());

        assertCommandFailure(editCommand, modelDeliverable, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showDeliverableAtIndex(modelDeliverable, INDEX_FIRST);

        Deliverable deliverableInFilteredList = modelDeliverable.getFilteredDeliverableList().get(
                INDEX_FIRST.getZeroBased());
        Deliverable editedDeliverable = new DeliverableBuilder(deliverableInFilteredList)
                .withTitle(VALID_TITLE_B).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditDeliverableDescriptorBuilder().withTitle(VALID_TITLE_B).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_DELIVERABLE_SUCCESS, editedDeliverable);

        ModelDeliverable expectedModelDeliverable =
                new ModelDeliverableManager(new DeliverableBook(
                        modelDeliverable.getDeliverableBook()), new UserPrefs());
        expectedModelDeliverable.setDeliverable(
                modelDeliverable.getFilteredDeliverableList().get(0), editedDeliverable);

        assertCommandSuccess(editCommand, modelDeliverable, expectedMessage, expectedModelDeliverable);
    }

    @Test
    public void execute_duplicateDeliverableUnfilteredList_failure() {
        Deliverable firstDeliverable = modelDeliverable.getFilteredDeliverableList().get(INDEX_FIRST.getZeroBased());
        EditDeliverableDescriptor descriptor = new EditDeliverableDescriptorBuilder(
                firstDeliverable).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, modelDeliverable, EditCommand.MESSAGE_DUPLICATE_DELIVERABLE);
    }

    @Test
    public void execute_duplicateDeliverableFilteredList_failure() {
        showDeliverableAtIndex(modelDeliverable, INDEX_FIRST);

        // edit meeting in filtered list into a duplicate in address book
        Deliverable deliverableInList = modelDeliverable.getDeliverableBook().getDeliverableList()
                .get(INDEX_SECOND.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditDeliverableDescriptorBuilder(deliverableInList).build());

        assertCommandFailure(editCommand, modelDeliverable, EditCommand.MESSAGE_DUPLICATE_DELIVERABLE);
    }

    @Test
    public void execute_invalidDeliverableIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelDeliverable.getFilteredDeliverableList().size() + 1);
        EditDeliverableDescriptor descriptor = new EditDeliverableDescriptorBuilder().withTitle(
                VALID_TITLE_B).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, modelDeliverable, MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of deliverable book
     */
    @Test
    public void execute_invalidDeliverableIndexFilteredList_failure() {
        showDeliverableAtIndex(modelDeliverable, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelDeliverable.getDeliverableBook().getDeliverableList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditDeliverableDescriptorBuilder().withTitle(VALID_TITLE_B).build());

        assertCommandFailure(editCommand, modelDeliverable, MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST, DESC_DELIVERABLE_A);

        // same values -> returns true
        EditDeliverableDescriptor copyDescriptor = new EditDeliverableDescriptor(
                DESC_DELIVERABLE_A);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        //        // different types -> returns false
        //        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND, DESC_DELIVERABLE_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST, DESC_DELIVERABLE_B)));
    }

}
