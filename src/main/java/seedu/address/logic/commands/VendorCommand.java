package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.Storage;

public class VendorCommand extends Command {
    public static final String COMMAND_WORD = "vendor";
    public static final String MESSAGE_RESET_VENDOR_SUCCESS = "Vendors have been displayed.";

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        model.selectVendor(-1);
        model.clearOrder();
        return new CommandResult(MESSAGE_RESET_VENDOR_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VendorCommand); // instanceof handles nulls
    }
}
