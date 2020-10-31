package seedu.address.logic.commands.appointmentCommandTest;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommand.AddApptCommand;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Nric;

public class AddApptCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApptCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AddApptCommand(null, new Appointment()));
        assertThrows(NullPointerException.class, () -> new AddApptCommand(new Nric("S0000001A"), null));
    }
}
