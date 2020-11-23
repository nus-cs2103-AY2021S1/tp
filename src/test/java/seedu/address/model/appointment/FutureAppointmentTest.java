package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentDates.PAST_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentTimes.VALID_APPOINTMENT_TIME;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotInFutureException;

class FutureAppointmentTest {
    @Test
    void futureAppointment() {
        Assertions.assertDoesNotThrow(() -> new FutureAppointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME
        ));

        assertThrows(AppointmentNotInFutureException.class, () -> new FutureAppointment(
                ALICE,
                BENEDICT,
                PAST_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME
            )
        );
    }

}
