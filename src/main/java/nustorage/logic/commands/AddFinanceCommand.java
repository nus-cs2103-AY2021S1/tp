package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;

/**
 * Adds a finance record to the address book.
 */
public class AddFinanceCommand extends Command {

    public static final String COMMAND_WORD = "add_finance";

    public static final String MESSAGE_SUCCESS = "New finance record added: %1$s";

    private final FinanceRecord newRecord;

    /**
     * Creates an AddFinanceCommand to add the specified {@code Finance Record}
     */
    public AddFinanceCommand(FinanceRecord newRecord) {
        requireNonNull(newRecord);
        this.newRecord = newRecord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addFinanceRecord(newRecord);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newRecord));
    }
}
