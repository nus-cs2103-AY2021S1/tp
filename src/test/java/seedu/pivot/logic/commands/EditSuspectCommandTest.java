package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_DUPLICATE_SUSPECT;
import static seedu.pivot.logic.commands.suspectcommands.EditSuspectCommand.MESSAGE_EDIT_SUSPECT_SUCCESS;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_ADDRESS;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_EMAIL;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_NAME_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_PHONE;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_SEX_AMY;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.CasePersonBuilder.DEFAULT_EMAIL;
import static seedu.pivot.testutil.CasePersonBuilder.DEFAULT_NAME;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.pivot.testutil.TypicalIndexes.SECOND_INDEX;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.suspectcommands.EditSuspectCommand;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.testutil.CaseBuilder;
import seedu.pivot.testutil.CasePersonBuilder;
import seedu.pivot.testutil.EditPersonDescriptorBuilder;

public class EditSuspectCommandTest {

    private Suspect suspect = new CasePersonBuilder().buildSuspect();
    private EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
    private ModelStub modelStub;

    @BeforeEach
    void setUp() {
        StateManager.setState(FIRST_INDEX);
        modelStub = new ModelStubWithCaseList();
    }

    @AfterAll
    static void tearDown() {
        StateManager.resetState();
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditSuspectCommand(null, FIRST_INDEX, descriptor));
        assertThrows(NullPointerException.class, () -> new EditSuspectCommand(FIRST_INDEX, null, descriptor));
        assertThrows(NullPointerException.class, () -> new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, null));
    }

    @Test
    public void equals() {
        EditPersonDescriptor alternateDescriptor = new EditPersonDescriptorBuilder().withName(DEFAULT_NAME).build();
        EditSuspectCommand command = new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, descriptor);

        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        assertTrue(command.equals(new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, descriptor)));

        // different types -> returns false
        assertFalse(command.equals(1));

        // null -> returns false
        assertFalse(command.equals(null));

        // different caseIndex -> returns false
        assertFalse(command.equals(new EditSuspectCommand(SECOND_INDEX, FIRST_INDEX, descriptor)));

        // different personIndex -> returns false
        assertFalse(command.equals(new EditSuspectCommand(FIRST_INDEX, SECOND_INDEX, descriptor)));

        // different descriptor -> returns false
        assertFalse(command.equals(new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, alternateDescriptor)));
    }

    @Test
    public void execute_allFieldsSpecified_success() throws CommandException {
        Suspect editedSuspect = new CasePersonBuilder(suspect)
                .withName(VALID_CASEPERSON_NAME_AMY)
                .withSex(VALID_CASEPERSON_SEX_AMY)
                .withPhone(VALID_CASEPERSON_PHONE)
                .withEmail(VALID_CASEPERSON_EMAIL)
                .withAddress(VALID_CASEPERSON_ADDRESS).buildSuspect();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(editedSuspect).build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String actualMessage = editSuspectCommand.execute(modelStub).getFeedbackToUser();

        String expectedMessage = String.format(MESSAGE_EDIT_SUSPECT_SUCCESS, editedSuspect);
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void execute_someFieldsSpecified_success() throws CommandException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_CASEPERSON_NAME_AMY).withEmail(VALID_CASEPERSON_EMAIL).build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String actualMessage = editSuspectCommand.execute(modelStub).getFeedbackToUser();

        Suspect editedSuspect = new CasePersonBuilder(suspect)
                .withName(VALID_CASEPERSON_NAME_AMY).withEmail(VALID_CASEPERSON_EMAIL).buildSuspect();

        String expectedMessage = String.format(MESSAGE_EDIT_SUSPECT_SUCCESS, editedSuspect);
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void execute_duplicatePerson_failure() {
        Suspect editedSuspect = new CasePersonBuilder().buildSuspect();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(editedSuspect).build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String expectedMessage = MESSAGE_DUPLICATE_SUSPECT;
        assertThrows(CommandException.class, expectedMessage, () -> editSuspectCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, SECOND_INDEX, editPersonDescriptor);

        String expectedMessage = UserMessages.MESSAGE_INVALID_SUSPECT_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () -> editSuspectCommand.execute(modelStub));

    }

    private class ModelStubWithCaseList extends ModelStub {
        final List<Case> caseList;

        private ModelStubWithCaseList() {
            this.caseList = new ArrayList<>();
            Case caseWithSuspect = new CaseBuilder().withSuspects(suspect).build();
            caseList.add(caseWithSuspect);
        }

        @Override
        public ObservableList<Case> getFilteredCaseList() {
            return FXCollections.observableList(caseList);
        }

        @Override
        public void setCase(Case target, Case editedCase) {
            int targetIndex = caseList.indexOf(target);
            caseList.set(targetIndex, editedCase);
        }

        @Override
        public void commitPivot(String commandMessage, Undoable command) {}
    }
}
