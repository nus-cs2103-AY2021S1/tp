package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;

/**
 * Adds a medical note to a patient in MediBook.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a medical note to the current patient "
            + "in MediBook. "
            + "Parameters: "
            + "[" + PREFIX_DATE + "DATE]"
            + PREFIX_CONTENT + "MEDICAL NOTE CONTENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "04-11-1991 "
            + PREFIX_CONTENT + "Patient has high fever and cough.";

    public static final String MESSAGE_SUCCESS = "New medical note entry added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This medical note entry already exists in the system.";
    public static final String MESSAGE_ADD_NOTE_ON_LIST = "You can only add medical note to a patient when you are "
            + "viewing his/her patient profile. Access the patient profile before adding medical note.";
    public static final String MESSAGE_USER_CANNOT_ADD = "Current user cannot add medical notes";

    private final Logger logger = LogsCenter.getLogger(AddNoteCommand.class);

    private final Date medicalNoteDate;
    private final Content medicalNoteContent;

    /**
     * Creates an AddNoteCommand to add the specified {@code MedicalNote}
     */
    public AddNoteCommand(Date date, Content content) {
        requireAllNonNull(date, content);
        this.medicalNoteDate = date;
        this.medicalNoteContent = content;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setShouldLoadMedicalNotes(false);
        Optional<Patient> patientOptional = model.getPatientToAccess();

        if (patientOptional.isEmpty()) {
            throw new CommandException(MESSAGE_ADD_NOTE_ON_LIST);
        }

        Patient displayedPatient = patientOptional.get();

        assert model.hasPatient(displayedPatient) : "Patient in Context does not exist in model";

        if (model.getActiveUser().isEmpty()) {
            throw new CommandException(MESSAGE_USER_CANNOT_ADD);
        }

        Doctor activeUser = model.getActiveUser().get();
        MedicalNote newMedicalNote = new MedicalNote(medicalNoteDate, activeUser, medicalNoteContent);

        if (displayedPatient.alreadyHasMedicalNote(newMedicalNote)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        displayedPatient.addMedicalNote(newMedicalNote);

        logger.info("----------------[PATIENT AND ORDER OF MEDICAL NOTES:][" + displayedPatient + "\n"
                + displayedPatient.getMedicalNoteList() + "]");

        return new CommandResult(String.format(MESSAGE_SUCCESS, newMedicalNote));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && medicalNoteDate.equals(((AddNoteCommand) other).medicalNoteDate)
                && medicalNoteContent.equals(((AddNoteCommand) other).medicalNoteContent));
    }
}
