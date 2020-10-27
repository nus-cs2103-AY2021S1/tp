package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderItem;
import seedu.address.storage.Storage;

public class SubmitCommand extends Command {

    public static final String COMMAND_WORD = "submit";

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {

        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }

        Order order = new Order();
        order.setOrderItems(model.getObservableOrderItemList());
        StringBuilder text = new StringBuilder();
        for (OrderItem orderItem: order) {
            text.append(String.format("%s x %d\n", orderItem.getName(), orderItem.getQuantity()));
        }
        text.append(String.format("Estimated total: $%.2f", order.getTotal()));
        return new CommandResult(text.toString());
    }
}
