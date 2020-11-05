package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.logic.parser.SampleResume;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.application.Application;
import com.eva.testutil.staff.TypicalStaffs;

class AddApplicationCommandTest {

    private static final Index INDEX = Index.fromZeroBased(1);
    private static final Application sampleApplication = new SampleResume().generateSampleApplication();
    private Model model;

    @Test
    public void constructor_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicationCommand(INDEX, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicationCommand(null, sampleApplication));
    }

    @Test
    public void execute_validIndexValidApplicationUnfilteredList_success() throws CommandException {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(),
                TypicalStaffs.getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        Applicant target = expectedModel.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddApplicationCommand addApplicationCommand = new AddApplicationCommand(INDEX_FIRST_PERSON,
                sampleApplication);

        String expectedMessage = String.format(AddApplicationCommand.MESSAGE_SUCCESS, target.getName());
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), TypicalStaffs.getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        CommandResult result = addApplicationCommand.execute(model);
        assertCommandSuccess(addApplicationCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexValidApplicationUnfilteredList_throwsCommandException() {
        model = new ModelManager(getTypicalPersonDatabase(), TypicalStaffs.getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        AddApplicationCommand addApplicationCommand = new AddApplicationCommand(outOfBoundIndex, sampleApplication);

        assertCommandFailure(addApplicationCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Application application = new SampleResume().generateSampleApplication();
        AddApplicationCommand addSampleApplicationCommand = new AddApplicationCommand(INDEX, application);
        Application otherSample = new SampleResume().generateSampleApplication();
        otherSample.setApplicantName("other");
        AddApplicationCommand otherSampleApplicationCommand = new AddApplicationCommand(INDEX, otherSample);

        // same object -> returns true
        assertEquals(addSampleApplicationCommand, addSampleApplicationCommand);

        // same values -> returns true
        AddApplicationCommand addSampleApplicationCommandCopy = new AddApplicationCommand(INDEX, application);
        assertEquals(addSampleApplicationCommandCopy, addSampleApplicationCommand);

        // different types -> returns false
        assertNotEquals(addSampleApplicationCommand, 1);

        // null -> returns false
        assertNotEquals(addSampleApplicationCommand, null);

        // different application -> returns false
        assertNotEquals(otherSampleApplicationCommand, addSampleApplicationCommand);
    }
}
