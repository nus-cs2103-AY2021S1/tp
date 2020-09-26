package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.InventoryRecord;

public class AddInventoryCommand extends Command{

    public static final String COMMAND_WORD = "add_inventory";

    public static final String MESSAGE_SUCCESS = "New Inventory record added: %1$s";

    private final InventoryRecord newRecord;


    public AddInventoryCommand(InventoryRecord newRecord) {
        requireNonNull(newRecord);
        this.newRecord = newRecord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException{
        requireNonNull(model);

        model.addInventoryRecord(newRecord);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newRecord));
    }

}
