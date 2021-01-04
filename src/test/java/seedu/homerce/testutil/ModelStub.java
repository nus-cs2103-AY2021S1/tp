package seedu.homerce.testutil;

import java.nio.file.Path;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.homerce.commons.core.GuiSettings;
import seedu.homerce.model.Model;
import seedu.homerce.model.ReadOnlyUserPrefs;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;
import seedu.homerce.model.manager.ReadOnlyExpenseTracker;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;

public class ModelStub implements Model {
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
    public Path getClientManagerFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setClientManagerFilePath(Path clientManagerFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addClient(Client client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addExpense(Expense expense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setClientManager(ReadOnlyClientManager newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyClientManager getClientManager() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasClient(Client client) {
        throw new AssertionError("This method should not be called.");
    }

    public boolean checkClientWithPhone(Phone phone) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteClient(Client target) {
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

    // ============================== Expense Tracker ====================================

    @Override
    public void deleteExpense(Expense target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpenses(List<Expense> expenses) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyExpenseTracker getExpenseTracker() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpenseTracker(ReadOnlyExpenseTracker expenseTracker) {
        throw new AssertionError("This method should not be called.");
    }


    // ============================== Service Management ====================================

    @Override
    public void addService(Service toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyServiceManager getServiceManager() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setServiceManager(ReadOnlyServiceManager serviceManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Service> getFilteredServiceList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredServiceList(Predicate<Service> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    // ============================== Revenue Tracker ====================================

    @Override
    public void addRevenue(Revenue toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteRevenue(Revenue target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRevenues(List<Revenue> revenues) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRevenueList(Predicate<Revenue> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Revenue> getFilteredRevenueList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyRevenueTracker getRevenueTracker() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRevenueTracker(ReadOnlyRevenueTracker revenueTracker) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteService(Service target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setService(Service target, Service editedService) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Client getClientByPhone(Phone phone) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasService(ServiceCode code) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Service getServiceByServiceCode(ServiceCode serviceCode) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAppointment(Appointment toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteAppointment(Appointment target) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAppointmentManager getAppointmentManager() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointment(List<Appointment> appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointmentManager(ReadOnlyAppointmentManager appointmentManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Appointment> getFilteredSchedule() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void nextSchedulePage() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void previousSchedulePage() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void refreshSchedule() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredSchedule(Predicate<Appointment> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointmentManagerCalendar(Calendar calendar) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void replaceModel(Model previousModel) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Model deepCopy() {
        throw new AssertionError("This method should not be called.");
    }
}
