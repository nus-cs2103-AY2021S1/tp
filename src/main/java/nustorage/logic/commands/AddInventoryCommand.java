package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

/**
 * An add inventory command class
 */
public class AddInventoryCommand extends Command {

    public static final String COMMAND_WORD = "add_inventory";

    public static final String MESSAGE_SUCCESS = "New Inventory record added: %1$s";

    private final InventoryRecord newInventoryRecord;
    private final FinanceRecord newFinanceRecord;

    /**
     * Constructs an AddInventoryCommand class using a new inventory record.
     * @param newInventoryRecord New inventory record.
     * @param newFinanceRecord New finance record.
     */
    public AddInventoryCommand(InventoryRecord newInventoryRecord, FinanceRecord newFinanceRecord) {
        requireNonNull(newInventoryRecord);
        this.newInventoryRecord = newInventoryRecord;
        this.newFinanceRecord = newFinanceRecord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addInventoryRecord(newInventoryRecord);
        model.addFinanceRecord(newFinanceRecord);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newInventoryRecord));
    }

}
