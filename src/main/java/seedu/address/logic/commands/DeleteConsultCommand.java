package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;

/**
 * Deletes a consultation
 */
public class DeleteConsultCommand extends Command {
    public static final String COMMAND_WORD = "del-consult";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a consultation from TAsker.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Consultation deleted: %1$s\nYou may view results in the "
        + "Consultations tab.";

    private final Index targetIndex;

    /**
     * Creates an DeleteConsultCommand to delete the specified {@code Consultation} by supplying its {@code Index}.
     *
     * @param targetIndex Index of consultation being deleted.
     */
    public DeleteConsultCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consultation> lastShownConsultations = model.getFilteredConsultList();

        if (targetIndex.getZeroBased() >= lastShownConsultations.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Consultation consultationToDelete = lastShownConsultations.get(targetIndex.getZeroBased());
        model.deleteConsultation(consultationToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, consultationToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteConsultCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteConsultCommand) other).targetIndex));
    }
}
