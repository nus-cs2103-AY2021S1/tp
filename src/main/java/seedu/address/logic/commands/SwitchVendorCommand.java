package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vendor.Vendor;
import seedu.address.storage.Storage;

/**
 * Selects a Vendor to order from.
 */
public class SwitchVendorCommand extends VendorCommand {

    public static final String COMMAND_WORD = "vendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects a Vendor in the VendorManager. "
            + "Parameters: "
            + " Index of Vendor";

    public static final String MESSAGE_SELECT_VENDOR_SUCCESS = "The vendor %s, has been selected.";
    public static final String MESSAGE_SELECT_VENDOR_SAME = "You are already on the vendor %s,\n"
            + "1. Use the clear command if you wish to clear your current order.\n"
            + "2. Use the menu command if you wish to reset to the original menu.\n"
            + "3. Use the vendor command if you wish to reselect vendors";

    private final Index vendorIndex;

    /**
     * Creates a VendorCommand to select the vendor at the specified {@code index} of the VendorManager
     */
    public SwitchVendorCommand(Index index) {
        requireNonNull(index);
        this.vendorIndex = index;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);
        ObservableList<Vendor> vendors = model.getObservableVendorList();
        int index = vendorIndex.getZeroBased();

        if (vendors.size() <= index) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENDOR_DISPLAYED_INDEX);
        }

        int oldIndex = model.getVendorIndex();
        model.selectVendor(index);

        Vendor currVendor = vendors.get(index);
        String message;
        boolean isSameVendor = false;
        if (oldIndex != index) {
            model.resetOrder();
            message = String.format(MESSAGE_SELECT_VENDOR_SUCCESS, currVendor.getName());
        } else {
            message = String.format(MESSAGE_SELECT_VENDOR_SAME, currVendor.getName());
            isSameVendor = true;
        }

        return new CommandResult(message, false, false, false, !isSameVendor);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SwitchVendorCommand // instanceof handles nulls
                && vendorIndex.equals(((SwitchVendorCommand) other).vendorIndex));
    }

}
