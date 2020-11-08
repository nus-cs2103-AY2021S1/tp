package seedu.address.logic.commands;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderItem;
import seedu.address.model.profile.Profile;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;
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

        StringBuilder profileText = new StringBuilder();
        try {
            Optional<Profile> optionalProfile = storage.readProfileManager();
            if (optionalProfile.isEmpty()) {
                throw new CommandException(Messages.MESSAGE_EMPTY_PROFILE);
            } else {
                Profile profile = optionalProfile.get();
                Address address = profile.getAddress();
                Phone phone = profile.getPhone();
                profileText.append(String.format("Address: %s\n", address.toString()));
                profileText.append(String.format("Phone: %s\n", phone.toString()));
                profileText.append("---------------------------------\n");
            }
        } catch (DataConversionException de) {
            throw new CommandException(Messages.MESSAGE_EMPTY_PROFILE);
        }

        StringBuilder orderText = new StringBuilder();
        orderText.append(profileText);
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
        feedback.append("\n" + String.format(ESTIMATE_TOTAL_MESSAGE, order.getTotal()));

        return new CommandResult(feedback.toString());
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof SubmitCommand;
    }
}
