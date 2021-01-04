package seedu.homerce.model.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_230PM_LASH_LIFT_BENSON;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_26_2020_11AM_MANICURE_CARL;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_27_2020_12PM_LASH_LIFT_BENSON;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;
import static seedu.homerce.testutil.client.TypicalClients.BENSON;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.predicate.AppointmentWeekPredicate;
import seedu.homerce.model.appointment.uniquelist.UniqueAppointmentList;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Name;
import seedu.homerce.model.util.uniquelist.exceptions.DuplicateItemException;
import seedu.homerce.testutil.appointment.AppointmentBuilder;
import seedu.homerce.testutil.client.ClientBuilder;

public class AppointmentManagerTest {
    private final AppointmentManager appointmentManager = new AppointmentManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), appointmentManager.getAppointmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAppointmentManager_replacesData() {
        AppointmentManager newData = getTypicalAppointmentManager();
        appointmentManager.resetData(newData);
        assertEquals(newData, appointmentManager);
    }

    @Test
    public void resetData_withDuplicateAppointments_throwsDuplicateItemException() {
        // Two appointments with the same identity fields
        Appointment editedAppointment = new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE)
            .withClient(BENSON)
            .build();
        List<Appointment> newAppointments =
            Arrays.asList(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE, editedAppointment);
        AppointmentManagerStub newData = new AppointmentManagerStub(newAppointments);
        assertThrows(DuplicateItemException.class, () -> appointmentManager.resetData(newData));
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentManager.hasAppointment(null));
    }

    @Test
    public void hasAppointment_appointmentNotInAppointmentManager_returnsFalse() {
        assertFalse(appointmentManager.hasAppointment(OCT_26_2020_11AM_MANICURE_CARL));
    }

    @Test
    public void hasAppointment_appointmentInAppointmentManager_returnsTrue() {
        appointmentManager.addAppointment(OCT_25_2020_230PM_LASH_LIFT_BENSON);
        assertTrue(appointmentManager.hasAppointment(OCT_25_2020_230PM_LASH_LIFT_BENSON));
    }

    @Test
    public void hasAppointment_appointmentWithSameIdentityFieldsInAppointmentManager_returnsTrue() {
        appointmentManager.addAppointment(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        Appointment editedAppointment = new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE)
            .withService(LASH_LIFT)
            .build();
        assertTrue(appointmentManager.hasAppointment(editedAppointment));
    }

    @Test
    public void getAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, () ->
                appointmentManager.getAppointmentListCopy().remove(0)
        );
    }

    @Test
    public void testToString() {
        appointmentManager.addAppointment(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        assertEquals(
            appointmentManager.toString(),
            "Appointment Manager:\n"
                + "25-10-2020 1400 Client: Alice Pauline Phone: 94351253 Email: alice@example.com Tags: [friends] "
                + "Service: Hair Treatment Duration: 2.0 Amount: 55.50 Service Code: SC001 Done? No\n"
                + " Total number of appointments: 1");
    }

    @Test
    public void testReplaceClientsInAppointments() {
        appointmentManager.addAppointment(OCT_25_2020_230PM_LASH_LIFT_BENSON);
        appointmentManager.addAppointment(OCT_26_2020_11AM_MANICURE_CARL);
        appointmentManager.addAppointment(OCT_27_2020_12PM_LASH_LIFT_BENSON);

        Client editedBenson = new ClientBuilder(BENSON).withName("Benny").build();
        appointmentManager.replaceClientsInAppointments(BENSON, editedBenson);

        assertTrue(appointmentManager.getAppointmentList().get(0).getClient().getName().equals(new Name("Benny")));
        assertTrue(appointmentManager.getAppointmentList().get(2).getClient().getName().equals(new Name("Benny")));

    }

    /**
     * A stub ReadOnlyAppointmentManager whose appointments list can violate interface constraints.
     */
    private static class AppointmentManagerStub implements ReadOnlyAppointmentManager {
        private final UniqueAppointmentList appointments;

        AppointmentManagerStub(Collection<Appointment> appointmentCollection) {
            this.appointments = new UniqueAppointmentList();
            this.appointments.asModifiableList().setAll(appointmentCollection);
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments.asModifiableList();
        }

        @Override
        public ObservableList<Appointment> getAppointmentListCopy() {
            UniqueAppointmentList appointmentListCopy = new UniqueAppointmentList();
            List<Appointment> appointmentsItemsCopy = appointments.deepCopy();
            appointmentListCopy.setItems(appointmentsItemsCopy);
            return appointmentListCopy.asUnmodifiableObservableList();
        }

        @Override
        public AppointmentWeekPredicate getCurrentWeek() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Calendar getCalendar() {
            throw new AssertionError("This method should not be called");
        }
    }
}
