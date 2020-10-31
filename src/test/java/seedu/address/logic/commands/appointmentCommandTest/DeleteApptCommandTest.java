package seedu.address.logic.commands.appointmentCommandTest;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommand.DeleteApptCommand;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Nric;

public class DeleteApptCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteApptCommand(null, null));
        assertThrows(NullPointerException.class, () -> new DeleteApptCommand(null, new Appointment()));
        assertThrows(NullPointerException.class, () -> new DeleteApptCommand(new Nric("S0000001A"), null));
    }
}
