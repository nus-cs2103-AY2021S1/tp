package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHairStyleX;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.Person;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;


/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {

    public static final ModelStub TYPICAL_MODEL_STUB = new ModelStub();

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getHairStyleXFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setHairStyleXFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addHairdresser(Hairdresser hairdresser) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setHairStyleX(ReadOnlyHairStyleX newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyHairStyleX getHairStyleX() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Client getClientById(ClientId clientId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Appointment getAppointmentById(AppointmentId appointmentId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasClient(Client client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteClient(Client client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addClient(Client client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasHairdresser(Hairdresser hairdresser) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteHairdresser(Hairdresser target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Hairdresser> getFilteredHairdresserList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredHairdresserList(Predicate<Hairdresser> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointment(Appointment target, Appointment changedAppointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public List<Appointment> getAppointmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Appointment getSelectedAppointment() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSelectedAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyProperty<Appointment> selectedAppointmentProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
