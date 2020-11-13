package seedu.jarvis.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.consultation.Consultation;

public class DeleteConsultationCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_CONSULTATION_USAGE = COMMAND_WORD
            + ": Deletes the consultation identified by the index number of the currently displayed "
            + "consultation list.\n"
            + "Example: " + COMMAND_WORD + " -c 1\n"
            + "Type \"view -c\" (or any consultation viewing command) to verify your index before deleting!";
    public static final String MESSAGE_SUCCESS = "Deleted Consultation: %1$s";

    private final Index index;

    /**
     * Creates an DeleteConsultation to delete the specified {@code Consultation}
     */
    public DeleteConsultationCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consultation> lastShownList = model.getFilteredConsultationsList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
        }

        Consultation consultationToDelete = lastShownList.get(index.getZeroBased());
        model.deleteConsultation(consultationToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, consultationToDelete),
                CommandTargetFeature.Consultations);
    }
}
