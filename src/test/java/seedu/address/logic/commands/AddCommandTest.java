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
import seedu.address.model.HospifyBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHospifyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddCommand(validPatient).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPatient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddCommand addCommand = new AddCommand(validPatient);
        ModelStub modelStub = new ModelStubWithPerson(validPatient);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PATIENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
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
        public Path getHospifyFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHospifyFilePath(Path hospifyFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int count() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sort(Comparator<Patient> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHospifyBook(ReadOnlyHospifyBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHospifyBook getHospifyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatientWithMrUrl(MedicalRecord url) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Patient patient;

        ModelStubWithPerson(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSamePatient(patient);
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            requireNonNull(nric);
            return this.patient.getNric().equals(nric);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Patient> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return personsAdded.stream().anyMatch(patient::isSamePatient);
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            requireNonNull(nric);
            return personsAdded.stream().anyMatch(patient -> hasPatientWithNric(patient.getNric()));
        }

        @Override
        public boolean hasPatientWithMrUrl(MedicalRecord url) {
            requireNonNull(url);
            return personsAdded.stream().anyMatch(patient -> hasPatientWithMrUrl(patient.getMedicalRecord()));
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            personsAdded.add(patient);
        }

        @Override
        public ReadOnlyHospifyBook getHospifyBook() {
            return new HospifyBook();
        }
    }

}
