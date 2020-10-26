package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_COST;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.Optional;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

/**
 * An add inventory command class
 */
public class AddInventoryRecordCommand extends Command {

    public static final String COMMAND_WORD = "add_inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an inventory record to the Inventory. "
            + "Parameters: "
            + PREFIX_ITEM_DESCRIPTION + "ITEM_DESCRIPTION "
            + PREFIX_QUANTITY + "QUANTITY "
            + "[" + PREFIX_ITEM_COST + "ITEM_COST]";

    public static final String MESSAGE_SUCCESS = "New Inventory record added: %1$s";
    public static final String MESSAGE_DUPLICATE_INVENTORY_RECORD = "This item already exists in the Inventory";
    private final InventoryRecord newInventoryRecord;
    private final Optional<FinanceRecord> newFinanceRecord;

    /**
     * Constructs an AddInventoryCommand class using a new inventory record.
     * @param newInventoryRecord New inventory record.
     * @param newFinanceRecord New finance record.
     */
    public AddInventoryRecordCommand(InventoryRecord newInventoryRecord, Optional<FinanceRecord> newFinanceRecord) {
        requireNonNull(newInventoryRecord);
        this.newInventoryRecord = newInventoryRecord;
        this.newFinanceRecord = newFinanceRecord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInventoryRecord(newInventoryRecord)) {
            throw new CommandException(MESSAGE_DUPLICATE_INVENTORY_RECORD);
        }

        model.addInventoryRecord(newInventoryRecord);
        if (newFinanceRecord.isPresent()) {
            newInventoryRecord.setFinanceRecord(newFinanceRecord.get());
            model.addFinanceRecord(newFinanceRecord.get());
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, newInventoryRecord));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInventoryRecordCommand // instanceof handles nulls
                && newInventoryRecord.equals(((AddInventoryRecordCommand) other).newInventoryRecord));
    }
}
