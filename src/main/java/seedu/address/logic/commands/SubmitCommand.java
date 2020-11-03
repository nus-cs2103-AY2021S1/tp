package seedu.address.logic.commands;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderItem;
import seedu.address.storage.Storage;

public class SubmitCommand extends Command {

    public static final String COMMAND_WORD = "submit";
    public static final String CLIPBOARD_SUCCESS_MESSAGE = "Successfully copied to clipboard!\n";
    public static final String ESTIMATE_TOTAL_MESSAGE = "Estimated total: $%.2f\n";

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }
        Order order = new Order();
        order.setOrderItems(model.getObservableOrderItemList());
        double total = order.getTotal();

        if (total == 0.0) {
            throw new CommandException(Messages.MESSAGE_EMPTY_ORDER);
        }

        StringBuilder orderText = new StringBuilder();
        for (OrderItem orderItem: order) {
            orderText.append(orderItem.toOrderText());
        }
        boolean copySuccess = true;
        try {
            StringSelection stringSelection = new StringSelection(orderText.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } catch (HeadlessException e) {
            copySuccess = false;
            e.printStackTrace();
        }

        StringBuilder feedback = new StringBuilder();
        if (copySuccess) {
            feedback.append(CLIPBOARD_SUCCESS_MESSAGE);
        }
        feedback.append(orderText.toString());
        feedback.append(String.format(ESTIMATE_TOTAL_MESSAGE, order.getTotal()));

        return new CommandResult(feedback.toString());
    }
}
