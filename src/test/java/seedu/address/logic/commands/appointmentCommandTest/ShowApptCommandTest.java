package seedu.address.logic.commands.appointmentCommandTest;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommand.ShowApptCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

public class ShowApptCommandTest {

    @Test
    public void execute_showAppt_failure() {
        Model model = new ModelManager();
        Patient validPatient = new PatientBuilder().build();
        ArrayList<String> list = new ArrayList<>();
        list.add(validPatient.getNric().toString());
        NricPredicate nricPredicate = new NricPredicate(list);
        assertCommandFailure(new ShowApptCommand(nricPredicate), model, "No patient with the NRIC found!");
    }
}
