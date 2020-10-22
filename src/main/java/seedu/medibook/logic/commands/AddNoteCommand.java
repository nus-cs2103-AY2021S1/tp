package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_MCR;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;

/**
 * Adds a patient to the medi book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a medical note to the current patient "
            + "in MediBook. "
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + PREFIX_NAME + "NAME OF DOCTOR (W/O TITLE) "
            + PREFIX_MCR + "MEDICAL REGISTRATION NO. OF DOCTOR "
            + PREFIX_CONTENT + "MEDICAL NOTE CONTENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "04-11-1991 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_MCR + "M17273J "
            + PREFIX_CONTENT + "Patient has high fever and cough.";

    public static final String MESSAGE_SUCCESS = "New medical note entry added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This medical note entry already exists in the system.";
    public static final String MESSAGE_ADD_NOTE_ON_LIST = "You can only add medical note to a patient when you are "
            + "viewing his/her patient profile. Access the patient profile before adding medical note.";

    private final Logger logger = LogsCenter.getLogger(AddNoteCommand.class);

    private final MedicalNote newMedicalNote;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddNoteCommand(MedicalNote newMedicalNote) {
        requireNonNull(newMedicalNote);
        this.newMedicalNote = newMedicalNote;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Patient> patientOptional = model.getPatientToAccess();

        if (!patientOptional.isPresent()) {
            throw new CommandException(MESSAGE_ADD_NOTE_ON_LIST);
        }

        Patient displayedPatient = patientOptional.get();

        assert model.hasPatient(displayedPatient) : "Patient in context does not exist in model";

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
                && newMedicalNote.equals(((AddNoteCommand) other).newMedicalNote));
    }
}
