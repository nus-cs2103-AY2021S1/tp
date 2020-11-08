package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;


public class ShowMrCommandTest {

    @Test
    public void execute_showMr_failure() {
        Model model = new ModelManager();
        Patient validPatient = new PatientBuilder().build();
        ArrayList<String> list = new ArrayList<>();
        list.add(validPatient.getNric().toString());
        NricPredicate nricPredicate = new NricPredicate(list);
        assertCommandFailure(new ShowMrCommand(nricPredicate), model, "No patient with the NRIC found!");
    }
}
