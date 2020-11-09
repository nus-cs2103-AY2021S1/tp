package seedu.address.logic.commands.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;

//@@author LeeMingDe
/**
 * Edits a patient in the app with the selected name.
 * Input of name is case insensitive.
 */
public class EditPatientCommand extends Command {

    public static final String COMMAND_WORD = "editpatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the patient's name used in the displayed patient list. \n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: NAME (must match exactly with the name of the patient to be edited in the patient list) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TEMP + "TEMPERATURE] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_PERIOD_OF_STAY + "PERIOD OF STAY] "
            + "[" + PREFIX_COMMENTS + "COMMENT] "
            + "\nExample: " + COMMAND_WORD + " john "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_TEMP + "36.5";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_PATIENT_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in Covigent.";

    private final Name patientToBeEdited;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Constructs an EditCommand to edit the patient with the name {@code String}.
     *
     * @param patientToBeEdited name in the filtered patient list to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditPatientCommand(Name patientToBeEdited, EditPatientDescriptor editPatientDescriptor) {
        requireAllNonNull(patientToBeEdited, editPatientDescriptor);

        this.patientToBeEdited = patientToBeEdited;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Patient> optionalPatient = model.getPatientWithName(patientToBeEdited);
        if (optionalPatient.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_NAME);
        }

        Patient patientToEdit = optionalPatient.get();
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        if (!patientToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        model.updateRoomListWhenPatientsChanges(patientToEdit, editedPatient);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     *
     * @param patientToEdit Patient that is to be edited.
     * @param editPatientDescriptor Details to edit the patient with.
     * @return Patient that has been edited.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;
        assert editPatientDescriptor != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Temperature updatedTemperature = editPatientDescriptor.getTemperature().orElse(patientToEdit.getTemperature());
        Age updatedAge = editPatientDescriptor.getAge().orElse(patientToEdit.getAge());
        PeriodOfStay updatedPeriodOfStay = editPatientDescriptor
                .getPeriodOfStay()
                .orElse(patientToEdit.getPeriodOfStay());
        Comment updatedComment = editPatientDescriptor.getComment().orElse(patientToEdit.getComment());
        return new Patient(updatedName, updatedTemperature, updatedPeriodOfStay,
                updatedPhone, updatedAge, updatedComment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { //short circuit if same object
            return true;
        }

        if (!(other instanceof EditPatientCommand)) { // instanceof handles nulls
            return false;
        }

        EditPatientCommand e = (EditPatientCommand) other; // state check
        return patientToBeEdited.equals(e.patientToBeEdited)
                && editPatientDescriptor.equals(e.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private Phone phone;
        private Temperature temperature;
        private Age age;
        private PeriodOfStay periodOfStay;
        private Comment comment;

        public EditPatientDescriptor() {}


        /**
         * Constructs a EditPatientDescriptor object with the following fields.
         *
         * @param toCopy EditPatientDescriptor to copy the fields from.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setTemperature(toCopy.temperature);
            setAge(toCopy.age);
            setPeriodOfStay(toCopy.periodOfStay);
            setComment(toCopy.comment);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, temperature, age, periodOfStay, comment);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setTemperature(Temperature temperature) {
            this.temperature = temperature;
        }

        public Optional<Temperature> getTemperature() {
            return Optional.ofNullable(temperature);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setPeriodOfStay(PeriodOfStay periodOfStay) {
            this.periodOfStay = periodOfStay;
        }

        public Optional<PeriodOfStay> getPeriodOfStay() {
            return Optional.ofNullable(periodOfStay);
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public Optional<Comment> getComment() {
            return Optional.ofNullable(comment);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) { // short circuit if same object
                return true;
            }

            if (!(other instanceof EditPatientDescriptor)) { // instanceof handles nulls
                return false;
            }

            EditPatientDescriptor e = (EditPatientDescriptor) other; // state check

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getTemperature().equals(e.getTemperature())
                    && getAge().equals(e.getAge())
                    && getPeriodOfStay().equals(e.getPeriodOfStay())
                    && getComment().equals(e.getComment());
        }
    }
    //@@author LeeMingDe
}
