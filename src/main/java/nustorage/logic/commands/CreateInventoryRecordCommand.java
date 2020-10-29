package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_COST;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

/**
 * An add inventory command class
 */
public class CreateInventoryRecordCommand extends Command {

    public static final String COMMAND_WORD = "create_inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an inventory record in the Inventory. "
            + "Parameters: "
            + PREFIX_ITEM_DESCRIPTION + "ITEM_DESCRIPTION "
            + PREFIX_QUANTITY + "QUANTITY "
            + "[" + PREFIX_ITEM_COST + "ITEM_COST]";

    public static final String MESSAGE_SUCCESS = "New Inventory record created: %1$s";
    public static final String MESSAGE_DUPLICATE_INVENTORY_RECORD = "This item already exists in the Inventory";
    private final InventoryRecord newInventoryRecord;
    private final FinanceRecord newFinanceRecord;

    /**
     * Constructs an AddInventoryCommand class using a new inventory record.
     * @param newInventoryRecord New inventory record.
     * @param newFinanceRecord New finance record.
     */
    public CreateInventoryRecordCommand(InventoryRecord newInventoryRecord, FinanceRecord newFinanceRecord) {
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
        newInventoryRecord.setFinanceRecord(newFinanceRecord);
        model.addFinanceRecord(newFinanceRecord);

        return new CommandResult(String.format(MESSAGE_SUCCESS, newInventoryRecord));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateInventoryRecordCommand // instanceof handles nulls
                && newInventoryRecord.equals(((CreateInventoryRecordCommand) other).newInventoryRecord));
    }
}
