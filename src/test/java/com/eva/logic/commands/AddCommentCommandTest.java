package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.APPLICANT_LIST;
import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.applicant.TypicalApplicants.getTypicalApplicantDatabase;
import static com.eva.testutil.staff.TypicalStaffs.getTypicalStaffDatabase;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.ReadOnlyUserPrefs;
import com.eva.model.UserPrefs;
import com.eva.model.comment.Comment;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.testutil.ApplicantBuilder;
import com.eva.testutil.CommentPersonDescriptorBuilder;
import com.eva.testutil.PersonBuilder;
import com.eva.testutil.staff.StaffBuilder;

import javafx.collections.ObservableList;

public class AddCommentCommandTest {

    public static final Index INDEX = Index.fromZeroBased(1);
    public static final Staff STAFF = new StaffBuilder().build();
    public static final CommentCommand.CommentPersonDescriptor STAFF_DESCRIPTOR =
            new CommentPersonDescriptorBuilder(STAFF).build();
    public static final Applicant APPLICANT = new ApplicantBuilder().build();
    public static final CommentCommand.CommentPersonDescriptor APPLICANT_DESCRIPTOR =
            new CommentPersonDescriptorBuilder(APPLICANT).build();
    public static final List<Comment> COMMENT_LIST = new ArrayList<>(STAFF.getComments());
    private Model model;

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommentCommand(null, STAFF_DESCRIPTOR));
    }

    @Test
    public void constructor_nullLeaveList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLeaveCommand(INDEX, null));
    }

    @Test
    public void execute_commentAcceptedByModelOnStaffList_addSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());

        Person staffToAddComment = expectedModel.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person staff = AddCommentCommand.createAddEditedPerson(staffToAddComment, STAFF_DESCRIPTOR);
        AddCommentCommand addCommentCommand = new AddCommentCommand(INDEX_FIRST_PERSON, STAFF_DESCRIPTOR);

        expectedModel.setStaff((Staff) staffToAddComment, (Staff) staff);
        String expectedMessage = String.format(AddCommentCommand.MESSAGE_ADD_COMMENT_SUCCESS_STAFF,
                STAFF_DESCRIPTOR.getCommentTitle(),
                staff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        assertCommandSuccess(addCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_commentAcceptedByModelOnStaffProfile_addSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setPanelState(PanelState.STAFF_PROFILE);

        Person staffToAddComment = expectedModel.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person staff = AddCommentCommand.createAddEditedPerson(staffToAddComment, STAFF_DESCRIPTOR);
        AddCommentCommand addCommentCommand = new AddCommentCommand(INDEX_FIRST_PERSON, STAFF_DESCRIPTOR);

        expectedModel.setStaff((Staff) staffToAddComment, (Staff) staff);
        expectedModel.setCurrentViewStaff(new CurrentViewStaff((Staff) staff, INDEX_FIRST_PERSON));

        String expectedMessage = String.format(AddCommentCommand.MESSAGE_ADD_COMMENT_SUCCESS_STAFF,
                STAFF_DESCRIPTOR.getCommentTitle(),
                staff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.STAFF_PROFILE);
        assertCommandSuccess(addCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_commentAcceptedByModelOnApplicantList_addSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());

        Person applicantToAddComment = expectedModel.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.setPanelState(APPLICANT_LIST);
        Person applicant = AddCommentCommand.createAddEditedPerson(applicantToAddComment, APPLICANT_DESCRIPTOR);
        AddCommentCommand addCommentCommand = new AddCommentCommand(INDEX_FIRST_PERSON, APPLICANT_DESCRIPTOR);

        expectedModel.setApplicant((Applicant) applicantToAddComment, (Applicant) applicant);
        String expectedMessage = String.format(AddCommentCommand.MESSAGE_ADD_COMMENT_SUCCESS_APPLICANT,
                APPLICANT_DESCRIPTOR.getCommentTitle(),
                applicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(APPLICANT_LIST);
        assertCommandSuccess(addCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_commentAcceptedByModelOnApplicantProfile_addSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setPanelState(PanelState.APPLICANT_PROFILE);

        Person applicantToAddComment = expectedModel.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person applicant = AddCommentCommand.createAddEditedPerson(applicantToAddComment, APPLICANT_DESCRIPTOR);
        AddCommentCommand addCommentCommand = new AddCommentCommand(INDEX_FIRST_PERSON, APPLICANT_DESCRIPTOR);

        expectedModel.setApplicant((Applicant) applicantToAddComment, (Applicant) applicant);
        expectedModel.setCurrentViewApplicant(new CurrentViewApplicant((Applicant) applicant, INDEX_FIRST_PERSON));

        String expectedMessage = String.format(AddCommentCommand.MESSAGE_ADD_COMMENT_SUCCESS_APPLICANT,
                APPLICANT_DESCRIPTOR.getCommentTitle(),
                applicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_PROFILE);
        assertCommandSuccess(addCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredStaffList_throwsCommandException() {
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStaffList().size() + 1);
        AddCommentCommand addCommentCommand = new AddCommentCommand(outOfBoundIndex, STAFF_DESCRIPTOR);
        assertCommandFailure(addCommentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
        public PanelState getPanelState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPanelState(PanelState panelState) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentViewStaff(CurrentViewStaff currentViewStaff) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentViewApplicant(CurrentViewApplicant currentViewStaff) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPersonDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getStaffDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getApplicantDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonDatabaseFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStaffDatabaseFilePath(Path staffDatabaseFilePath) {
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
        public void setPersonDatabase(ReadOnlyEvaDatabase<Person> newData) {
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
        public void setStaffDatabase(ReadOnlyEvaDatabase<Staff> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEvaDatabase<Staff> getStaffDatabase() {
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
        public CurrentViewApplicant getCurrentViewApplicant() {
            return null;
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
        public void setApplicantDatabase(ReadOnlyEvaDatabase<Applicant> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEvaDatabase<Applicant> getApplicantDatabase() {
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
    private class ModelStubWithStaffWithPanelState extends AddCommentCommandTest.ModelStub {
        private final ArrayList<Staff> staffList;
        private PanelState panelState;

        ModelStubWithStaffWithPanelState(Staff staff, PanelState panelState) {
            requireNonNull(staff);
            this.staffList = new ArrayList<>();
            staffList.add(staff);
            this.panelState = panelState;
        }

        @Override
        public boolean hasStaff(Staff staff) {
            requireNonNull(staff);
            return this.staffList.stream().anyMatch(staff::equals);
        }

        @Override
        public void setPanelState(PanelState panelState) {
            this.panelState = panelState;
        }

        @Override
        public PanelState getPanelState() {
            return this.panelState;
        }
    }
}
