package seedu.homerce.logic.commands.service;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;
import static seedu.homerce.testutil.service.TypicalServices.MANICURE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.service.Service;
import seedu.homerce.testutil.ModelStub;

public class AddServiceCommandTest {

    @Test
    public void constructor_nullService_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddServiceCommand(null));
    }

    @Test
    public void constructor_nullModel_throwsNullPointerException() {
        Service service = LASH_LIFT;
        AddServiceCommand addServiceCommand = new AddServiceCommand(service);
        assertThrows(NullPointerException.class, () -> addServiceCommand.execute(null, null));
    }

    @Test
    public void execute_serviceAcceptedByModel_addSuccessful() throws CommandException {
        AddServiceCommandTest.ModelStubAcceptingServiceAdded modelStub =
            new ModelStubAcceptingServiceAdded();
        Service validService = LASH_LIFT;

        CommandResult commandResult = new AddServiceCommand(validService).execute(modelStub,
            HistoryManager.getInstance());

        assertEquals(String.format(AddServiceCommand.MESSAGE_SUCCESS, validService), commandResult
            .getFeedbackToUser());
        assertEquals(Arrays.asList(validService), modelStub.servicesAdded);
    }

    @Test
    public void equals() {
        Service firstService = LASH_LIFT;

        Service secondService = MANICURE;

        AddServiceCommand addServiceCommandFirst = new AddServiceCommand(firstService);
        AddServiceCommand addServiceCommandSecond = new AddServiceCommand(secondService);

        // same object -> returns true
        assertTrue(addServiceCommandFirst.equals(addServiceCommandFirst));

        // different types -> return false
        assertFalse(addServiceCommandFirst.equals(1));

        // null -> returns false
        assertFalse(addServiceCommandFirst.equals(null));

        // different expense -> returns false
        assertFalse(addServiceCommandFirst.equals(addServiceCommandSecond));
    }

    /**
     * A Model stub that always accepts the service being added.
     */
    private static class ModelStubAcceptingServiceAdded extends ModelStub {
        final ArrayList<Service> servicesAdded = new ArrayList<>();

        @Override
        public void addService(Service service) {
            requireNonNull(service);
            servicesAdded.add(service);
        }

        @Override
        public ReadOnlyServiceManager getServiceManager() {
            return new ServiceManager();
        }

        @Override
        public ReadOnlyAppointmentManager getAppointmentManager() {
            return new AppointmentManager();
        }

        @Override
        public void updateFilteredServiceList(Predicate<Service> predicate) {

        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            return FXCollections.observableArrayList(new ArrayList<Appointment>());
        }

        @Override
        public ObservableList<Service> getFilteredServiceList() {
            return FXCollections.observableArrayList(servicesAdded);
        }
    }
}
