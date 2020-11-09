package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_MCR;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.core.Messages;
import seedu.medibook.commons.core.index.Index;
import seedu.medibook.commons.util.CollectionUtil;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;

/**
 * Edits the details of an existing medical note in the MediBook.
 */
public class EditNoteCommand extends Command {

    public static final String COMMAND_WORD = "editnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the medical note identified "
            + "by the index number used in the displayed medical note list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_NAME + "NAME OF DOCTOR] "
            + "[" + PREFIX_MCR + " MEDICAL REGISTRATION NO.] "
            + "[" + PREFIX_CONTENT + "CONTENT] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "18-06-2019 "
            + PREFIX_CONTENT + "Patient is having stomach cramps.";

    public static final String MESSAGE_EDIT_NOTE_SUCCESS = "Edited Medical Note: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_NOTE = "This medical note already exists in this patient profile.";
    public static final String MESSAGE_EDIT_NOTE_ON_LIST = "You can only edit medical note of a patient when you are "
            + "viewing his/her patient profile. Access the patient profile before editing medical note.";
    public static final String MESSAGE_USER_CANNOT_EDIT = "Current user cannot edit medical notes";
    public static final String MESSAGE_CANNOT_EDIT_OTHER_DOCTOR_NOTES = "Can't edit other doctor's medical notes";

    private final Logger logger = LogsCenter.getLogger(EditNoteCommand.class);
    private final Index index;
    private final EditNoteDescriptor editNoteDescriptor;

    /**
     * Constructs a command to edit medical notes at the index, using the descriptor.
     * @param index of the medical note in the displayed medical note list to edit.
     * @param editNoteDescriptor details to edit the medical note with.
     */
    public EditNoteCommand(Index index, EditNoteDescriptor editNoteDescriptor) {
        requireNonNull(index);
        requireNonNull(editNoteDescriptor);

        this.index = index;
        this.editNoteDescriptor = new EditNoteDescriptor(editNoteDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setShouldLoadMedicalNotes(false);
        Optional<Patient> patientOptional = model.getPatientToAccess();

        if (patientOptional.isEmpty()) {
            throw new CommandException(MESSAGE_EDIT_NOTE_ON_LIST);
        }

        Patient displayedPatient = patientOptional.get();

        assert model.hasPatient(displayedPatient) : "Patient in Context does not exist in model";

        int zeroBasedIndex = index.getZeroBased();

        if (zeroBasedIndex >= displayedPatient.getNumOfMedicalNotes()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        MedicalNote noteToEdit = displayedPatient.getMedicalNoteAtIndex(zeroBasedIndex);
        MedicalNote newMedicalNote = createEditedNote(noteToEdit, editNoteDescriptor);

        if (model.getActiveUser().isEmpty()) {
            throw new CommandException(MESSAGE_USER_CANNOT_EDIT);
        }

        Doctor activeUser = model.getActiveUser().get();

        if (!noteToEdit.isAuthoredBy(activeUser)) {
            throw new CommandException(MESSAGE_CANNOT_EDIT_OTHER_DOCTOR_NOTES);
        }

        try {
            displayedPatient.deleteMedicalNoteAtIndex(zeroBasedIndex);

            if (displayedPatient.alreadyHasMedicalNote(newMedicalNote)) {
                throw new CommandException(MESSAGE_DUPLICATE_NOTE);
            }

            displayedPatient.addMedicalNote(newMedicalNote);
        } catch (CommandException e) {
            // add back original note (undo the operation)
            displayedPatient.addMedicalNote(noteToEdit);

            // carry forward exception
            throw e;
        }

        logger.info("----------------[PATIENT AND ORDER OF MEDICAL NOTES:][" + displayedPatient + "\n"
                + displayedPatient.getMedicalNoteList() + "]");

        return new CommandResult(String.format(MESSAGE_EDIT_NOTE_SUCCESS, newMedicalNote));
    }

    /**
     * Creates and returns a {@code Medical Note} with the details of {@code noteToEdit}
     * edited with {@code editNoteDescriptor}.
     */
    private static MedicalNote createEditedNote(MedicalNote noteToEdit, EditNoteDescriptor editNoteDescriptor) {
        assert noteToEdit != null : "Note to edit in EditNoteCommand#createEditedNote() is null";

        Date updatedDate = editNoteDescriptor.getDate().orElse(noteToEdit.getDate());
        Content updatedContent = editNoteDescriptor.getContent().orElse(noteToEdit.getContent());

        return new MedicalNote(updatedDate, noteToEdit.doctor, updatedContent);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditNoteCommand)) {
            return false;
        }

        // state check
        EditNoteCommand e = (EditNoteCommand) other;
        return index.equals(e.index)
                && editNoteDescriptor.equals(e.editNoteDescriptor);
    }

    /**
     * Stores the details to edit the medical note with. Each non-empty field value will replace the
     * corresponding field value of the medical note.
     */
    public static class EditNoteDescriptor {
        private Date date;
        private Content content;

        public EditNoteDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditNoteDescriptor(EditNoteDescriptor toCopy) {
            setDate(toCopy.date);
            setContent(toCopy.content);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, content);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setContent(Content content) {
            this.content = content;
        }

        public Optional<Content> getContent() {
            return Optional.ofNullable(content);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditNoteDescriptor)) {
                return false;
            }

            // state check
            EditNoteDescriptor e = (EditNoteDescriptor) other;

            return getDate().equals(e.getDate())
                    && getContent().equals(e.getContent());
        }
    }
}
