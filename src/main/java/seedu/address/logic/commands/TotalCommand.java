package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

public class TotalCommand extends Command {

    public static final String COMMAND_WORD = "total";

    public static final String MESSAGE_RESULT = "Total is $%.2f.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Order order = new Order();
        order.setOrderItems(model.getFilteredOrderItemList());
        double total = order.getTotal();
        return new CommandResult(String.format(MESSAGE_RESULT, total));
    }
}
