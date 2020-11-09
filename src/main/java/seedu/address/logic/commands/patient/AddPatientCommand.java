package seedu.address.logic.commands.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

//@@author chiamyunqing
/**
 * Adds a patient to the app.
 */
public class AddPatientCommand extends Command {

    public static final String COMMAND_WORD = "addpatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to Covigent. "
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TEMP + "TEMPERATURE "
            + PREFIX_PERIOD_OF_STAY + "PERIOD OF STAY "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_AGE + "AGE "
            + "[" + PREFIX_COMMENTS + "COMMENTS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TEMP + "37.0 "
            + PREFIX_PERIOD_OF_STAY + "20200914-20200928 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_AGE + "22 "
            + PREFIX_COMMENTS + "Vegan,asthmatic ";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in Covigent.";

    private final Patient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}.
     */
    public AddPatientCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }
        model.addPatient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPatientCommand // instanceof handles nulls
                && toAdd.equals(((AddPatientCommand) other).toAdd));
    }
}
