package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_DUPLICATE_VICTIM;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_ADDRESS;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_EMAIL;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_NAME_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_PHONE;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_SEX_AMY;
import static seedu.pivot.logic.commands.victimcommands.EditVictimCommand.MESSAGE_EDIT_VICTIM_SUCCESS;
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
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.commands.victimcommands.EditVictimCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.testutil.CaseBuilder;
import seedu.pivot.testutil.CasePersonBuilder;
import seedu.pivot.testutil.EditPersonDescriptorBuilder;

public class EditVictimCommandTest {

    private Victim victim = new CasePersonBuilder().buildVictim();
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
        assertThrows(NullPointerException.class, () -> new EditVictimCommand(null, FIRST_INDEX, descriptor));
        assertThrows(NullPointerException.class, () -> new EditVictimCommand(FIRST_INDEX, null, descriptor));
        assertThrows(NullPointerException.class, () -> new EditVictimCommand(FIRST_INDEX, FIRST_INDEX, null));
    }

    @Test
    public void equals() {
        EditPersonDescriptor alternateDescriptor = new EditPersonDescriptorBuilder().withName(DEFAULT_NAME).build();
        EditVictimCommand command = new EditVictimCommand(FIRST_INDEX, FIRST_INDEX, descriptor);

        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        assertTrue(command.equals(new EditVictimCommand(FIRST_INDEX, FIRST_INDEX, descriptor)));

        // different types -> returns false
        assertFalse(command.equals(1));

        // null -> returns false
        assertFalse(command.equals(null));

        // different caseIndex -> returns false
        assertFalse(command.equals(new EditVictimCommand(SECOND_INDEX, FIRST_INDEX, descriptor)));

        // different personIndex -> returns false
        assertFalse(command.equals(new EditVictimCommand(FIRST_INDEX, SECOND_INDEX, descriptor)));

        // different descriptor -> returns false
        assertFalse(command.equals(new EditVictimCommand(FIRST_INDEX, FIRST_INDEX, alternateDescriptor)));
    }

    @Test
    public void execute_allFieldsSpecified_success() throws CommandException {
        Victim editedVictim = new CasePersonBuilder(victim)
                .withName(VALID_CASEPERSON_NAME_AMY)
                .withSex(VALID_CASEPERSON_SEX_AMY)
                .withPhone(VALID_CASEPERSON_PHONE)
                .withEmail(VALID_CASEPERSON_EMAIL)
                .withAddress(VALID_CASEPERSON_ADDRESS).buildVictim();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(editedVictim).build();
        EditVictimCommand editVictimCommand = new EditVictimCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String actualMessage = editVictimCommand.execute(modelStub).getFeedbackToUser();

        String expectedMessage = String.format(MESSAGE_EDIT_VICTIM_SUCCESS, editedVictim);
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void execute_someFieldsSpecified_success() throws CommandException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_CASEPERSON_NAME_AMY).withEmail(VALID_CASEPERSON_EMAIL).build();
        EditVictimCommand editVictimCommand = new EditVictimCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String actualMessage = editVictimCommand.execute(modelStub).getFeedbackToUser();

        Victim editedVictim = new CasePersonBuilder(victim)
                .withName(VALID_CASEPERSON_NAME_AMY).withEmail(VALID_CASEPERSON_EMAIL).buildVictim();

        String expectedMessage = String.format(MESSAGE_EDIT_VICTIM_SUCCESS, editedVictim);
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void execute_duplicatePerson_failure() {
        Victim editedVictim = new CasePersonBuilder().buildVictim();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(editedVictim).build();
        EditVictimCommand editVictimCommand = new EditVictimCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String expectedMessage = MESSAGE_DUPLICATE_VICTIM;
        assertThrows(CommandException.class, expectedMessage, () -> editVictimCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditVictimCommand editVictimCommand = new EditVictimCommand(FIRST_INDEX, SECOND_INDEX, editPersonDescriptor);

        String expectedMessage = UserMessages.MESSAGE_INVALID_VICTIM_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () -> editVictimCommand.execute(modelStub));

    }

    private class ModelStubWithCaseList extends ModelStub {
        final List<Case> caseList;

        private ModelStubWithCaseList() {
            this.caseList = new ArrayList<>();
            Case caseWithVictim = new CaseBuilder().withVictims(victim).build();
            caseList.add(caseWithVictim);
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
