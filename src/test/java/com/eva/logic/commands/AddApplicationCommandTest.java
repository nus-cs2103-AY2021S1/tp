package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.PanelState;
import com.eva.logic.parser.SampleResume;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.application.Application;

class AddApplicationCommandTest {

    private Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
            getTypicalApplicantDatabase(), new UserPrefs());

//    @Test
//    void execute_validIndexUnfilteredList_success() {
//        Applicant target = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
//        SampleResume sampleResume = new SampleResume();
//        AddApplicationCommand addAppCommand = new AddApplicationCommand(INDEX_FIRST_PERSON,
//                sampleResume.generateSampleApplication());
//        model.setPanelState(PanelState.APPLICANT_LIST);
//
//        String expectedMessage = String.format(AddApplicationCommand.MESSAGE_SUCCESS, target);
//
//        ModelManager expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
//                model.getApplicantDatabase(), new UserPrefs());
//
//        expectedModel.addApplicantApplication(target, sampleResume.generateSampleApplication());
//
//        assertCommandSuccess(addAppCommand, model, expectedMessage, expectedModel);
//    }
}
