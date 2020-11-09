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

import seedu.medibook.commons.core.Messages;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.patient.Patient;

/**
 * Adds a patient to the medi book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the medi book. "
            + "Parameters: "
            + PREFIX_IC + "IC "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE OF BIRTH "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_BLOOD_TYPE + "BLOOD TYPE] "
            + "[" + PREFIX_ALLERGY + "ALLERGY]... "
            + "[" + PREFIX_CONDITION + "CONDITION]... "
            + "[" + PREFIX_TREATMENT + "TREATMENT]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_IC + "S9123456A "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "04-11-1991 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_HEIGHT + "180 "
            + PREFIX_WEIGHT + "70.5 "
            + PREFIX_BLOOD_TYPE + "A+ "
            + PREFIX_ALLERGY + "shellfish "
            + PREFIX_ALLERGY + "dust "
            + PREFIX_CONDITION + "fever "
            + PREFIX_TREATMENT + "Paracetamol "
            + PREFIX_TREATMENT + "Ibuprofen (for high fever only)";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the medi book";

    private final Patient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getPatientToAccess().isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_IN_PATIENT_PROFILE);
        }

        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addPatient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
