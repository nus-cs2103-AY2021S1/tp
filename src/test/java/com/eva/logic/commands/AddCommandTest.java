package com.eva.logic.commands;

import static com.eva.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.PanelState;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.ReadOnlyUserPrefs;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.testutil.PersonBuilder;

import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public PanelState getPanelState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPanelState(PanelState panelState) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPersonDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonDatabaseFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getStaffDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStaffDatabaseFilePath(Path staffDatabaseFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getApplicantDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicantDatabaseFilePath(Path applicantDatabaseFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStaffLeave(Staff target, Leave leave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStaffLeave(Staff target, Leave leave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStaffLeave(Staff target, Leave leave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Leave> hasLeaveDate(Staff target, LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLeavePeriod(Staff target, Leave leave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEvaDatabase<Person> getPersonDatabase() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonDatabase(ReadOnlyEvaDatabase<Person> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStaff(Staff person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEvaDatabase<Staff> getStaffDatabase() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStaffDatabase(ReadOnlyEvaDatabase<Staff> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStaff(Staff person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStaff(Staff target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStaff(Staff target, Staff editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Staff> getFilteredStaffList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CurrentViewStaff getCurrentViewStaff() {
            return null;
        }

        @Override
        public void setCurrentViewStaff(CurrentViewStaff currentViewStaff) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CurrentViewApplicant getCurrentViewApplicant() {
            return null;
        }

        @Override
        public void setCurrentViewApplicant(CurrentViewApplicant currentViewStaff) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStaffList(Predicate<Staff> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplicant(Applicant person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplicantApplication(Applicant target, Application toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEvaDatabase<Applicant> getApplicantDatabase() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicantDatabase(ReadOnlyEvaDatabase<Applicant> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplicant(Applicant person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplicant(Applicant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicant(Applicant target, Applicant editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicationStatus(Applicant applicant, ApplicationStatus status) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplication(Applicant target, Application toSet) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Applicant> getFilteredApplicantList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyEvaDatabase<Person> getPersonDatabase() {
            return new EvaDatabase<>();
        }
    }

}
