package seedu.address.logic.commands.appointmentCommandTest;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommand.EditApptCommand;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Nric;

public class EditApptCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditApptCommand(null, null, null));
        assertThrows(NullPointerException.class, () -> new EditApptCommand(null, new Appointment(), new Appointment()));
        assertThrows(NullPointerException.class, () -> new EditApptCommand(new Nric("S0000001A"), null, null));
    }
}
