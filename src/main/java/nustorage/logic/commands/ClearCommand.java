package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import nustorage.model.FinanceAccount;
import nustorage.model.Inventory;
import nustorage.model.Model;

/**
 * Clears the inventory and finance account
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "NUStorage data has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFinanceAccount(new FinanceAccount());
        model.setInventory(new Inventory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
