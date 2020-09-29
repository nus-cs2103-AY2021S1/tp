package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.Optional;

import seedu.stock.commons.core.index.Index;
import seedu.stock.commons.util.CollectionUtil;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;

public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the stock with "
            + "the given serial number "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_SERIAL_NUMBER + "SERIAL NUMBER "
            + "Followed with one of: "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_NEW_QUANTITY + "NEW QUANTITY "
            + PREFIX_NAME + "NAME "
            + PREFIX_SOURCE + "SOURCE "
            + PREFIX_LOCATION + "LOCATION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Umbrella "
            + PREFIX_SOURCE + "Kc company "
            + PREFIX_QUANTITY + "100 "
            + PREFIX_LOCATION + "section B ";

    public static final String MESSAGE_UPDATE_STOCK_SUCCESS = "Updated Stock: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_STOCK = "This stock already exists in the address book.";

    private final Index index;
    private final UpdateStockDescriptor updateStockDescriptor;

    /**
     * Constructs a new update command.
     * @param index Index of the stock in inventory.
     * @param updateStockDescriptor Details to be updated.
     */
    public UpdateCommand(Index index, UpdateStockDescriptor updateStockDescriptor) {
        requireNonNull(index);
        requireNonNull(updateStockDescriptor);

        this.index = index;
        this.updateStockDescriptor = updateStockDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        return false;
    }

    public static class UpdateStockDescriptor {

        // Identity fields
        private Name name;
        private SerialNumber serialNumber;

        // Data fields
        private Source source;
        private Quantity quantity;
        private Location location;

        public UpdateStockDescriptor() {}

        /**
         * Copy constructor.
         * @param toCopy Object to be copied.
         */
        public UpdateStockDescriptor(UpdateStockDescriptor toCopy) {
            setName(toCopy.name);
            setSerialNumber(toCopy.serialNumber);
            setSource(toCopy.source);
            setQuantity(toCopy.quantity);
            setLocation(toCopy.location);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, serialNumber, source, quantity, location);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setSerialNumber(SerialNumber serialNumber) {
            this.serialNumber = serialNumber;
        }

        public Optional<SerialNumber> getSerialNumber() {
            return Optional.ofNullable(serialNumber);
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Optional<Source> getSource() {
            return Optional.ofNullable(source);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateStockDescriptor)) {
                return false;
            }

            // state check
            UpdateStockDescriptor castedOther = (UpdateStockDescriptor) other;

            return getName().equals(castedOther.getName())
                    && getSerialNumber().equals(castedOther.getSerialNumber())
                    && getSource().equals(castedOther.getSource())
                    && getQuantity().equals(castedOther.getQuantity())
                    && getLocation().equals(castedOther.getLocation());
        }
    }
}
