
//package seedu.pivot.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.pivot.logic.commands.testutil.CommandTestUtil.EDIT_CASE_DESCRIPTOR_AMY;
//import static seedu.pivot.logic.commands.testutil.CommandTestUtil.EDIT_CASE_DESCRIPTOR_BOB;
//import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TITLE_BOB;
//import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
//import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
//import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
//import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
//import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.pivot.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.pivot.commons.core.UserMessages;
//import seedu.pivot.commons.core.index.Index;
//import seedu.pivot.logic.commands.EditCommand.EditCaseDescriptor;
//import seedu.pivot.model.Model;
//import seedu.pivot.model.ModelManager;
//import seedu.pivot.model.Pivot;
//import seedu.pivot.model.UserPrefs;
//import seedu.pivot.model.investigationcase.Case;
//import seedu.pivot.testutil.CaseBuilder;
//import seedu.pivot.testutil.CasePersonBuilder;
//import seedu.pivot.testutil.EditCaseDescriptorBuilder;
//
///**
// * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests
// for EditCommand.
// */
//public class EditCommandTest {
//
//    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Case editedCase = new CaseBuilder()
//                .withDocument("name", "test1.txt")
//                .withWitnesses(new CasePersonBuilder().withName("Janice").withGender("F").buildWitness())
//                .build();
//        EditCommand.EditCaseDescriptor descriptor = new EditCaseDescriptorBuilder(editedCase).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CASE_SUCCESS, editedCase);
//
//        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
//        expectedModel.setCase(model.getFilteredCaseList().get(0), editedCase);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastPerson = Index.fromOneBased(model.getFilteredCaseList().size());
//        Case lastCase = model.getFilteredCaseList().get(indexLastPerson.getZeroBased());
//
//        CaseBuilder personInList = new CaseBuilder(lastCase);
//        Case editedCase = personInList.withTitle(VALID_TITLE_BOB).withTags(VALID_TAG_HUSBAND).build();
//
//        EditCaseDescriptor descriptor = new EditCaseDescriptorBuilder().withTitle(VALID_TITLE_BOB)
//                .withTags(VALID_TAG_HUSBAND).build();
//
//        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CASE_SUCCESS, editedCase);
//
//        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
//        expectedModel.setCase(lastCase, editedCase);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditCommand.EditCaseDescriptor());
//        Case editedCase = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CASE_SUCCESS, editedCase);
//
//        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showCaseAtIndex(model, INDEX_FIRST_PERSON);
//
//        Case caseInFilteredList = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Case editedCase = new CaseBuilder(caseInFilteredList).withTitle(VALID_TITLE_BOB).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
//                new EditCaseDescriptorBuilder().withTitle(VALID_TITLE_BOB).build());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CASE_SUCCESS, editedCase);
//
//        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
//        expectedModel.setCase(model.getFilteredCaseList().get(0), editedCase);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePersonUnfilteredList_failure() {
//        Case firstCase = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
//        EditCaseDescriptor descriptor = new EditCaseDescriptorBuilder(firstCase).build();
//        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
//
//        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CASE);
//    }
//
//    @Test
//    public void execute_duplicatePersonFilteredList_failure() {
//        showCaseAtIndex(model, INDEX_FIRST_PERSON);
//
//        // edit person in filtered list into a duplicate in address book
//        Case caseInList = model.getPivot().getCaseList().get(INDEX_SECOND_PERSON.getZeroBased());
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
//                new EditCaseDescriptorBuilder(caseInList).build());
//
//        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CASE);
//    }
//
//    @Test
//    public void execute_invalidPersonIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCaseList().size() + 1);
//        EditCaseDescriptor descriptor = new EditCaseDescriptorBuilder().withTitle(VALID_TITLE_BOB).build();
//        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidPersonIndexFilteredList_failure() {
//        showCaseAtIndex(model, INDEX_FIRST_PERSON);
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getPivot().getCaseList().size());
//
//        EditCommand editCommand = new EditCommand(outOfBoundIndex,
//                new EditCaseDescriptorBuilder().withTitle(VALID_TITLE_BOB).build());
//
//        assertCommandFailure(editCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, EDIT_CASE_DESCRIPTOR_AMY);
//
//        // same values -> returns true
//        EditCommand.EditCaseDescriptor copyDescriptor = new EditCaseDescriptor(EDIT_CASE_DESCRIPTOR_AMY);
//        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, EDIT_CASE_DESCRIPTOR_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, EDIT_CASE_DESCRIPTOR_BOB)));
//    }
//
//}
