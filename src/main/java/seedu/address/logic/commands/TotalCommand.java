package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.storage.Storage;

public class TotalCommand extends Command {

    public static final String COMMAND_WORD = "total";

    public static final String MESSAGE_RESULT = "Total is $%.2f.";

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {

        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }

        Order order = new Order();
        order.setOrderItems(model.getObservableOrderItemList());
        double total = order.getTotal();
        return new CommandResult(String.format(MESSAGE_RESULT, total));
    }
}
