package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.logic.commands.CommandTestUtil.showConsultationAtIndex;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_ONE;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.delete.DeleteConsultationCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.testutil.TypicalManagers;


public class DeleteConsultationCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
            TypicalManagers.getUserLogin());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.addConsultation(TEST_CONSULTATION_ONE);
        Consultation consultationToDelete = model.getFilteredConsultationsList().get(INDEX_FIRST.getZeroBased());
        DeleteConsultationCommand deleteCommand = new DeleteConsultationCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(DeleteConsultationCommand.MESSAGE_SUCCESS, consultationToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.deleteConsultation(consultationToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredConsultationsList().size() + 1);
        DeleteConsultationCommand deleteCommand = new DeleteConsultationCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.addConsultation(TEST_CONSULTATION_ONE);

        showConsultationAtIndex(model, INDEX_FIRST);

        Consultation consultationToDelete = model.getFilteredConsultationsList().get(INDEX_FIRST.getZeroBased());
        DeleteConsultationCommand deleteCommand = new DeleteConsultationCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(DeleteConsultationCommand.MESSAGE_SUCCESS, consultationToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.deleteConsultation(consultationToDelete);
        showNoConsultations(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        model.addConsultation(TEST_CONSULTATION_ONE);
        showConsultationAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        DeleteConsultationCommand deleteCommand = new DeleteConsultationCommand(outOfBoundIndex);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexConsultationsList_commandTargetFeatureAccurate() throws CommandException {
        model.addConsultation(TEST_CONSULTATION_ONE);
        DeleteConsultationCommand deleteCommand = new DeleteConsultationCommand(INDEX_FIRST);
        CommandResult commandResult = deleteCommand.execute(model);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Consultations, commandTargetFeature);
    }

    /**
     * Updates {@code model}'s filtered list to show no consultations.
     */
    private void showNoConsultations(Model model) {
        model.updateFilteredConsultationList(p -> false);
        assertTrue(model.getFilteredConsultationsList().isEmpty());
    }
}
