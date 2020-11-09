package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.medibook.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.medibook.commons.core.Messages;
import seedu.medibook.commons.core.index.Index;
import seedu.medibook.commons.util.CollectionUtil;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;

/**
 * Edits the details of an existing patient in the medi book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_IC + "IC] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATE + "DATE OF BIRTH] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_BLOOD_TYPE + "BLOOD TYPE]"
            + "[" + PREFIX_ALLERGY + "ALLERGY]... "
            + "[" + PREFIX_CONDITION + "CONDITION]... "
            + "[" + PREFIX_TREATMENT + "TREATMENT]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the medi book.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index of the patient in the filtered patient list to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(index);
        requireNonNull(editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getPatientToAccess().isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_IN_PATIENT_PROFILE);
        }
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);
        MedicalNoteList medicalNoteList = patientToEdit.getMedicalNoteList();
        editedPatient.setMedicalNoteList(medicalNoteList.makeCopy());

        if (!patientToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        boolean wasIcEdited = !patientToEdit.getIc().equals(editedPatient.getIc());
        if (wasIcEdited) {
            model.setEditedPatient(editedPatient, patientToEdit.getIc());
        }

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Ic updatedIc = editPatientDescriptor.getIc().orElse(patientToEdit.getIc());
        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        DateOfBirth updatedDateOfBirth = editPatientDescriptor.getDateOfBirth().orElse(patientToEdit.getDateOfBirth());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Optional<Email> updatedEmail = editPatientDescriptor.getEmail().or(patientToEdit::getEmail);
        Optional<Address> updatedAddress = editPatientDescriptor.getAddress().or(patientToEdit::getAddress);
        Optional<Height> updatedHeight = editPatientDescriptor.getHeight().or(patientToEdit::getHeight);
        Optional<Weight> updatedWeight = editPatientDescriptor.getWeight().or(patientToEdit::getWeight);
        Optional<BloodType> updatedBloodType = editPatientDescriptor.getBloodType()
                .or(patientToEdit::getBloodType);
        Set<Allergy> updatedAllergies = editPatientDescriptor.getAllergies().orElse(patientToEdit.getAllergies());
        Set<Condition> updatedConditions = editPatientDescriptor.getConditions().orElse(patientToEdit.getConditions());
        Set<Treatment> updatedTreatments = editPatientDescriptor.getTreatments().orElse(patientToEdit.getTreatments());


        Patient editedPatient = new Patient(updatedIc, updatedName, updatedDateOfBirth, updatedPhone, updatedEmail,
                updatedAddress, updatedHeight, updatedWeight, updatedBloodType, updatedAllergies, updatedConditions,
            updatedTreatments);

        editedPatient.setRecord(patientToEdit.getRecord());

        boolean wasHeightUpdated = editPatientDescriptor.getHeight().isPresent();
        boolean wasWeightUpdated = editPatientDescriptor.getWeight().isPresent();
        if (wasHeightUpdated || wasWeightUpdated) {
            updateRecords(editedPatient, editPatientDescriptor);
        }

        return editedPatient;
    }

    private static void updateRecords(Patient editedPatient, EditPatientDescriptor editPatientDescriptor) {
        editPatientDescriptor.getHeight()
                .or(editedPatient::getHeight)
                .ifPresent(h -> editedPatient.getRecord().addHeightRecord(h));
        editPatientDescriptor.getWeight()
                .or(editedPatient::getWeight)
                .ifPresent(w -> editedPatient.getRecord().addWeightRecord(w));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPatientDescriptor.equals(e.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {
        private Ic ic;
        private Name name;
        private DateOfBirth dateOfBirth;
        private Phone phone;
        private Email email;
        private Address address;
        private Height height;
        private Weight weight;
        private BloodType bloodType;
        private Set<Allergy> allergies;
        private Set<Condition> conditions;
        private Set<Treatment> treatments;

        public EditPatientDescriptor() {}

        /**
         * Copy constructor.
         * Defensive copies of medical tags are used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setIc(toCopy.ic);
            setName(toCopy.name);
            setDateOfBirth(toCopy.dateOfBirth);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setHeight(toCopy.height);
            setWeight(toCopy.weight);
            setBloodType(toCopy.bloodType);
            setAllergies(toCopy.allergies);
            setConditions(toCopy.conditions);
            setTreatments(toCopy.treatments);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(ic, name, dateOfBirth, phone, email, address, height, weight,
                                               bloodType, allergies, conditions, treatments);
        }

        public void setIc(Ic ic) {
            this.ic = ic;
        }

        public Optional<Ic> getIc() {
            return Optional.ofNullable(ic);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDateOfBirth(DateOfBirth dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Optional<DateOfBirth> getDateOfBirth() {
            return Optional.ofNullable(dateOfBirth);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }

        public void setBloodType(BloodType bloodType) {
            this.bloodType = bloodType;
        }

        public Optional<BloodType> getBloodType() {
            return Optional.ofNullable(bloodType);
        }

        /**
         * Sets {@code allergies} to this object's {@code allergies}.
         * A defensive copy of {@code allergies} is used internally.
         */
        public void setAllergies(Set<Allergy> allergies) {
            this.allergies = (allergies != null) ? new HashSet<>(allergies) : null;
        }

        /**
         * Returns an unmodifiable allergy set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code allergies} is null.
         */
        public Optional<Set<Allergy>> getAllergies() {
            return (allergies != null) ? Optional.of(Collections.unmodifiableSet(allergies)) : Optional.empty();
        }

        /**
         * Sets {@code conditions} to this object's {@code conditions}.
         * A defensive copy of {@code conditions} is used internally.
         */
        public void setConditions(Set<Condition> conditions) {
            this.conditions = (conditions != null) ? new HashSet<>(conditions) : null;
        }

        /**
         * Returns an unmodifiable condition set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code conditions} is null.
         */
        public Optional<Set<Condition>> getConditions() {
            return (conditions != null) ? Optional.of(Collections.unmodifiableSet(conditions)) : Optional.empty();
        }

        /**
         * Sets {@code treatments} to this object's {@code treatments}.
         * A defensive copy of {@code treatments} is used internally.
         */
        public void setTreatments(Set<Treatment> treatments) {
            this.treatments = (treatments != null) ? new HashSet<>(treatments) : null;
        }

        /**
         * Returns an unmodifiable treatment set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code treatments} is null.
         */
        public Optional<Set<Treatment>> getTreatments() {
            return (treatments != null) ? Optional.of(Collections.unmodifiableSet(treatments)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            // state check
            EditPatientDescriptor e = (EditPatientDescriptor) other;

            return getIc().equals(e.getIc())
                    && getName().equals(e.getName())
                    && getDateOfBirth().equals(e.getDateOfBirth())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getHeight().equals(e.getHeight())
                    && getWeight().equals(e.getWeight())
                    && getBloodType().equals(e.getBloodType())
                    && getAllergies().equals(e.getAllergies())
                    && getConditions().equals(e.getConditions())
                    && getTreatments().equals(e.getTreatments());
        }
    }
}
