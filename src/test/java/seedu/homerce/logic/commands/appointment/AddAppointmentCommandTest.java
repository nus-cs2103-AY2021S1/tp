package seedu.homerce.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.AppointmentTemp;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.testutil.ModelStub;
import seedu.homerce.testutil.appointment.AppointmentBuilder;
import seedu.homerce.testutil.appointment.AppointmentTempBuilder;
import seedu.homerce.testutil.client.TypicalClients;
import seedu.homerce.testutil.service.TypicalServices;

public class AddAppointmentCommandTest {
    @Test
    public void constructor_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void constructor_nullModel_throwsNullPointerException() {
        AppointmentTemp appointmentTemp = new AppointmentTempBuilder()
            .withDate("25-10-2020")
            .withTimeOfDay("1400")
            .withPhone("83222666")
            .withServiceCode("SC001")
            .build();
        AddAppointmentCommand addConditionerCommand = new AddAppointmentCommand(appointmentTemp);
        assertThrows(NullPointerException.class, () -> addConditionerCommand.execute(null, null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        AppointmentTemp validAppointmentTemp = new AppointmentTempBuilder()
            .withDate("25-10-2020")
            .withTimeOfDay("1400")
            .withPhone("94351253")
            .withServiceCode("SC001")
            .build();
        CommandResult commandResult = null;
        try {
            commandResult = new AddAppointmentCommand(validAppointmentTemp).execute(modelStub,
                HistoryManager.getInstance());
        } catch (CommandException e) {
            e.printStackTrace();
        }
        Appointment expectedAppointment = new AppointmentBuilder()
            .withDate("25-10-2020")
            .withTimeOfDay("1400")
            .withClient(TypicalClients.ALICE)
            .withService(TypicalServices.HAIR_TREATMENT)
            .build();
        assertEquals(Arrays.asList(expectedAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void equals() {
        AppointmentTemp appointmentOne = new AppointmentTempBuilder().build();
        AppointmentTemp appointmentTwo = new AppointmentTempBuilder().withDate("1-1-2021").build();
        AddAppointmentCommand addAppointmentCommandOne = new AddAppointmentCommand(appointmentOne);
        AddAppointmentCommand addAppointmentCommandTwo = new AddAppointmentCommand(appointmentTwo);

        // same object -> returns true
        assertTrue(addAppointmentCommandTwo.equals(addAppointmentCommandTwo));

        // different types -> return false
        assertFalse(addAppointmentCommandOne.equals(1));

        // null -> returns false
        assertFalse(addAppointmentCommandOne.equals(null));

        // different expense -> returns false
        assertFalse(addAppointmentCommandOne.equals(addAppointmentCommandTwo));
    }

    /**
     * A Model stub that always accepts the expense being added.
     */
    private static class ModelStubAcceptingAppointmentAdded extends ModelStub {

        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyAppointmentManager getAppointmentManager() {
            return new AppointmentManager();
        }

        @Override
        public boolean checkClientWithPhone(Phone phone) {
            return phone.value.equals("94351253");
        }

        @Override
        public Client getClientByPhone(Phone phone) {
            return TypicalClients.ALICE;
        }

        @Override
        public boolean hasService(ServiceCode code) {
            return code.getID() == 1;
        }

        @Override
        public Service getServiceByServiceCode(ServiceCode serviceCode) {
            return TypicalServices.HAIR_TREATMENT;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            return appointmentsAdded.contains(appointment);
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        }

        @Override
        public void refreshSchedule() {

        }
    }
}
