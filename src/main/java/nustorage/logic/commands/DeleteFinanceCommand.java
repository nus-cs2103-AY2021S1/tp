package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import nustorage.commons.core.Messages;
import nustorage.commons.core.index.Index;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;

/**
 * Deletes an existing finance record from the address book.
 */
public class DeleteFinanceCommand extends Command {

    public static final String COMMAND_WORD = "delete_finance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the finance record identified by the index number used in the finance list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Finance record deleted: %1$s";

    private final Index targetIndex;

    /**
     * Creates an AddFinanceCommand to add the specified {@code Finance Record}
     */
    public DeleteFinanceCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        assert !model.getFilteredFinanceList().isEmpty();
        Optional<FinanceRecord> record = model.deleteFinanceRecord(targetIndex);

        if (record.isPresent()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, record.get()));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
