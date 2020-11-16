package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.exceptions.ConflictingGroupConsultationException;

/**
 * Represents the command that is used to list all consultations, in classes.
 */
public class AddConsultCommand extends Command {
    public static final String COMMAND_WORD = "add-consult";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation to TAsker.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_ADDRESS + "LOCATION "
            + PREFIX_TYPE + "TYPE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "10/10/2020 "
            + PREFIX_TIME + "18:00 "
            + PREFIX_ADDRESS + "SOC Basement "
            + PREFIX_TYPE + "personal ";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s\nYou may view results in the "
        + "Consultations tab.";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation slot has already been taken up!";
    public static final String MESSAGE_WRONG_LOCATION_GROUP_CONSULTATION =
            "Group consultations sharing the same time slot should be at the same location!";

    private final Consultation toAdd;

    /**
     * Creates an AddConsultCommand to add the specified {@code Consultation}.
     *
     * @param consultation Consultation(s) being called.
     */
    public AddConsultCommand(Consultation consultation) {
        requireNonNull(consultation);
        toAdd = consultation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasConsult(toAdd) || model.hasConflictingPersonalConsultation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        try {
            model.addConsultation(toAdd);
        } catch (ConflictingGroupConsultationException e) {
            throw new CommandException(MESSAGE_WRONG_LOCATION_GROUP_CONSULTATION);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddConsultCommand // instanceof handles nulls
                && toAdd.equals(((AddConsultCommand) other).toAdd));
    }
}
