package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.ReadOnlyUserPrefs;
import com.eva.model.UserPrefs;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.applicant.application.SampleResume;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.testutil.staff.TypicalStaffs;

import javafx.collections.ObservableList;

class AddApplicationCommandTest {

    private static final Index INDEX = Index.fromZeroBased(1);
    private static final Application sampleApplication = new SampleResume().generateSampleApplication();
    private Model model;

    @Test
    public void constructor_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicationCommand(INDEX, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicationCommand(null, sampleApplication));
    }

    @Test
    public void execute_validIndexValidApplicationUnfilteredList_success() throws CommandException {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(),
                TypicalStaffs.getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        Applicant target = expectedModel.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddApplicationCommand addApplicationCommand = new AddApplicationCommand(INDEX_FIRST_PERSON,
                sampleApplication);
        expectedModel.addApplicantApplication(target, sampleApplication);
        String expectedMessage = String.format(AddApplicationCommand.MESSAGE_SUCCESS, target.getName());
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), TypicalStaffs.getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandSuccess(addApplicationCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexValidApplicationUnfilteredList_throwsCommandException() {
        model = new ModelManager(getTypicalPersonDatabase(), TypicalStaffs.getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        AddApplicationCommand addApplicationCommand = new AddApplicationCommand(outOfBoundIndex, sampleApplication);

        assertCommandFailure(addApplicationCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Application application = new SampleResume().generateSampleApplication();
        AddApplicationCommand addSampleApplicationCommand = new AddApplicationCommand(INDEX, application);
        Application otherSample = new SampleResume().generateSampleApplication();
        otherSample.setApplicantName("other");
        AddApplicationCommand otherSampleApplicationCommand = new AddApplicationCommand(INDEX, otherSample);

        // same object -> returns true
        assertEquals(addSampleApplicationCommand, addSampleApplicationCommand);

        // same values -> returns true
        AddApplicationCommand addSampleApplicationCommandCopy = new AddApplicationCommand(INDEX, application);
        assertEquals(addSampleApplicationCommandCopy, addSampleApplicationCommand);

        // different types -> returns false
        assertNotEquals(addSampleApplicationCommand, 1);

        // null -> returns false
        assertNotEquals(addSampleApplicationCommand, null);

        // different application -> returns false
        assertNotEquals(otherSampleApplicationCommand, addSampleApplicationCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    public class ModelStub implements Model {
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
     * A Model stub that contains a single Applicant.
     */
    private class ModelStubWithApplicant extends AddApplicationCommandTest.ModelStub {
        private final Applicant applicant;

        ModelStubWithApplicant(Applicant applicant) {
            requireNonNull(applicant);
            this.applicant = applicant;
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return this.applicant.isSamePerson(applicant);
        }
    }

    /**
     * A Model stub that always accept the Applicant being added.
     */
    private class ModelStubAcceptingApplicantAdded extends AddApplicationCommandTest.ModelStub {
        final ArrayList<Applicant> applicantsAdded = new ArrayList<>();
        private PanelState panelState;

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return applicantsAdded.stream().anyMatch(applicant::isSamePerson);
        }

        @Override
        public void addApplicant(Applicant applicant) {
            requireNonNull(applicant);
            applicantsAdded.add(applicant);
        }

        @Override
        public void setPanelState(PanelState panelState) {
            this.panelState = panelState;
        }

        @Override
        public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
            return;
        }

        @Override
        public ReadOnlyEvaDatabase<Applicant> getApplicantDatabase() {
            return new EvaDatabase<>();
        }
    }
}
