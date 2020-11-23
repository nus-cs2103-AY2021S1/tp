package seedu.address.model.appointment;

import static seedu.address.testutil.TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentTimes.VALID_APPOINTMENT_TIME;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;

class UniqueAppointmentListTest {

    @Test
    void setAppointment() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        uniqueAppointmentList.add(appt);
        uniqueAppointmentList.setAppointment(appt, appt.deleteClient());
    }

    @Test
    void setAppointments() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        uniqueAppointmentList.add(appt);
        uniqueAppointmentList.setAppointments(new UniqueAppointmentList());
        uniqueAppointmentList.setAppointments(new ArrayList<Appointment>());
    }


    @Test
    void updateClient() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        uniqueAppointmentList.add(appt);
        uniqueAppointmentList.updateClient(ALICE.getId(), ALICE.setTombstone());
    }

    @Test
    void updateHairdresser() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        uniqueAppointmentList.add(appt);
        uniqueAppointmentList.updateHairdresser(BENEDICT.getId(), BENEDICT.setTombstone());
    }

    @Test
    void setClientToNull() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        uniqueAppointmentList.add(appt);
        uniqueAppointmentList.deleteClient(ALICE.getId());
    }

    @Test
    void setHairdresserToNull() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        uniqueAppointmentList.add(appt);
        uniqueAppointmentList.deleteHairdresser(BENEDICT.getId());
    }

    @Test
    void findAppointmentById() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

        // input should be not null
        Assertions.assertThrows(NullPointerException.class, () -> uniqueAppointmentList
            .findAppointmentById(null));

        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        AppointmentId apptId = appt.getId();

        // match not found, returns null
        Assertions.assertEquals(null, uniqueAppointmentList.findAppointmentById(apptId));

        // appointment is present, returns it
        uniqueAppointmentList.add(appt);
        Assertions.assertEquals(appt, uniqueAppointmentList.findAppointmentById(apptId));
    }

    @Test
    void duplicateException() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        uniqueAppointmentList.add(appt);
        Assertions.assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(appt));
    }

    @Test
    void notFoundException() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertThrows(AppointmentNotFoundException.class, () ->
            uniqueAppointmentList.setAppointment(appt, appt.deleteClient()));
    }
}
