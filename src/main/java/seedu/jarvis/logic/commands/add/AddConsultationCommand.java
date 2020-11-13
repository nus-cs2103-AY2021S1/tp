package seedu.jarvis.logic.commands.add;

import static java.util.Objects.requireNonNull;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.consultation.Consultation;

public class AddConsultationCommand extends AddCommand {
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in jarvis";
    public static final String MESSAGE_SUCCESS_CONSULTATION = "New consultation added: %1$s";

    private final Consultation toAddConsultation;

    /**
     * Creates an AddConsultationCommand to add the specified {@code Consultation}
     */
    public AddConsultationCommand(Consultation consultation) {
        requireNonNull(consultation);
        toAddConsultation = consultation;
    }

    public Consultation getToAddConsultation() {
        return toAddConsultation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasConsultation(toAddConsultation)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        model.addConsultation(toAddConsultation);
        return new CommandResult(String.format(MESSAGE_SUCCESS_CONSULTATION, toAddConsultation),
                CommandTargetFeature.Consultations);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddConsultationCommand)) {
            return false;
        }

        // state check
        AddConsultationCommand e = (AddConsultationCommand) other;

        return toAddConsultation.equals(e.getToAddConsultation());
    }
}
