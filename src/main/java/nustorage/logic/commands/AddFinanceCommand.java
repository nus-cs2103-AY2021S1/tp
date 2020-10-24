package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static nustorage.logic.parser.CliSyntax.PREFIX_DATETIME;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;

/**
 * Adds a finance record to the address book.
 */
public class AddFinanceCommand extends Command {

    public static final String COMMAND_WORD = "add_finance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a finance record."
            + "Parameters: "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_DATETIME + "[DATE] [TIME]]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "30000 "
            + PREFIX_DATETIME + "2020-03-30 18:00";

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

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        //ignore comparison of internal ID field as user cannot set it
        if (other instanceof AddFinanceCommand) {
            return newRecord.hasSameData(((AddFinanceCommand) other).newRecord);
        }

        return false;
    }
}
