package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.JobAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyJobAddressBook;
import seedu.address.model.ReadOnlyPersonAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.information.Job;
import seedu.address.model.information.Person;
import seedu.address.testutil.JobBuilder;

public class AddJobCommandTest {

    @Test
    public void constructor_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddJobCommand(null));
    }

    @Test
    public void execute_jobAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingJobAdded modelStub = new ModelStubAcceptingJobAdded();
        Job validJob = new JobBuilder().build();

        CommandResult commandResult = new AddJobCommand(validJob).execute(modelStub);

        assertEquals(String.format(AddJobCommand.MESSAGE_SUCCESS, validJob), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validJob), modelStub.jobsAdded);
    }

    @Test
    public void execute_duplicateJob_throwsCommandException() {
        Job validJob = new JobBuilder().build();
        AddJobCommand addJobCommand = new AddJobCommand(validJob);
        ModelStub modelStub = new ModelStubWithJob(validJob);

        assertThrows(CommandException.class, AddJobCommand.MESSAGE_DUPLICATE_JOB, ()
            -> addJobCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Job iras = new JobBuilder().withJobTitle("IRAS").build();
        Job maybank = new JobBuilder().withJobTitle("MAYBANK").build();
        AddJobCommand addIrasCommand = new AddJobCommand(iras);
        AddJobCommand addMaybankCommand = new AddJobCommand(maybank);

        // same object -> returns true
        assertTrue(addIrasCommand.equals(addIrasCommand));

        // same values -> returns true
        AddJobCommand addIrasCommandCopy = new AddJobCommand(iras);
        assertTrue(addIrasCommand.equals(addIrasCommandCopy));

        // different types -> returns false
        assertFalse(addIrasCommand.equals(1));

        // null -> returns false
        assertFalse(addIrasCommand.equals(null));

        // different person -> returns false
        assertFalse(addIrasCommand.equals(addMaybankCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public Path getPersonAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonAddressBook(ReadOnlyPersonAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPersonAddressBook getPersonAddressBook() {
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
        public Path getJobAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJobAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJobAddressBook(ReadOnlyJobAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyJobAddressBook getJobAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasJob(Job job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteJob(Job target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addJob(Job job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJob(Job target, Job editedJob) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getSortedJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getSortedPersonList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredJobList(Predicate<Job> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedJobList(Comparator<Job> comparator) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Job getDisplayedJob() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDisplayedJob(Job job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Person getDisplayedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDisplayedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single job.
     */
    private class ModelStubWithJob extends ModelStub {
        private final Job job;

        ModelStubWithJob(Job job) {
            requireNonNull(job);
            this.job = job;
        }

        @Override
        public boolean hasJob(Job job) {
            requireNonNull(job);
            return this.job.isSameJob(job);
        }
    }

    /**
     * A Model stub that always accept the job being added.
     */
    private class ModelStubAcceptingJobAdded extends ModelStub {
        final ArrayList<Job> jobsAdded = new ArrayList<>();

        @Override
        public boolean hasJob(Job job) {
            requireNonNull(job);
            return jobsAdded.stream().anyMatch(job::isSameJob);
        }

        @Override
        public void addJob(Job job) {
            requireNonNull(job);
            jobsAdded.add(job);
        }

        @Override
        public ReadOnlyJobAddressBook getJobAddressBook() {
            return new JobAddressBook();
        }
    }

}
