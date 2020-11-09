package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.medibook.commons.core.GuiSettings;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.Model;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.ReadOnlyUserPrefs;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPatientException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPatientAdded modelStub = new ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddCommand(validPatient).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPatient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientsAdded);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddCommand addCommand = new AddCommand(validPatient);
        ModelStub modelStub = new ModelStubWithPatient(validPatient);

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

        // different patient -> returns false
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
        public Path getMediBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMediBookFilePath(Path mediBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMediBook(ReadOnlyMediBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMediBook getMediBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
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

        @Override
        public void accessPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Patient> getPatientToAccess() {
            return Optional.empty();
        }

        @Override
        public void resetAccessedPatient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeletedPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Patient> getDeletedPatient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetDeletedPatient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEditedPatient(Patient patient, Ic prevIc) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Patient> getEditedPatient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Ic> getEditedPatientPrevIc() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetEditedPatient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean getShouldLoadMedicalNotes() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShouldLoadMedicalNotes(boolean b) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean getShouldDeleteAllMedicalNotes() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShouldDeleteAllMedicalNotes(boolean b) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setActiveUser(Optional<Doctor> doctor) {

        }

        @Override
        public Optional<Doctor> getActiveUser() {
            return Optional.empty();
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithPatient extends ModelStub {
        private final Patient patient;

        ModelStubWithPatient(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSamePatient(patient);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {
        final ArrayList<Patient> patientsAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSamePatient);
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient);
        }

        @Override
        public ReadOnlyMediBook getMediBook() {
            return new MediBook();
        }
    }

}
