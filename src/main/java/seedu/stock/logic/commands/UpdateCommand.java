package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIALNUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.List;
import java.util.Optional;

import seedu.stock.commons.util.CollectionUtil;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the stock with "
            + "the given serial number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_SERIALNUMBER + "SERIAL NUMBER "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_NEW_QUANTITY + "NEW QUANTITY "
            + PREFIX_NAME + "NAME "
            + PREFIX_SOURCE + "SOURCE "
            + PREFIX_LOCATION + "LOCATION \n"
            + "Note that only one of " + PREFIX_QUANTITY
            + "and " + PREFIX_NEW_QUANTITY
            + "can be specified. \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SERIALNUMBER + "CS2103 "
            + PREFIX_QUANTITY + "2103 "
            + PREFIX_NAME + "CS2103 "
            + PREFIX_SOURCE + "National University of Singapore "
            + PREFIX_LOCATION + "Group 3 ";

    public static final String MESSAGE_UPDATE_STOCK_SUCCESS = "Updated Stock: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_STOCK = "This stock already exists in the address book.";

    private final UpdateStockDescriptor updateStockDescriptor;

    /**
     * Constructs a new update command.
     * @param updateStockDescriptor Details to be updated.
     */
    public UpdateCommand(UpdateStockDescriptor updateStockDescriptor) {
        requireNonNull(updateStockDescriptor);

        this.updateStockDescriptor = updateStockDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stock> lastShownInventory = model.getFilteredStockList();

        // Dummy stock for now
        Stock stockToUpdate = lastShownInventory.get(0);
        Stock updatedStock = createUpdatedStock(stockToUpdate, updateStockDescriptor);

        if (!stockToUpdate.isSameStock(updatedStock) && model.hasStock(updatedStock)) {
            throw new CommandException(MESSAGE_DUPLICATE_STOCK);
        }

        model.setStock(stockToUpdate, updatedStock);
        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_UPDATE_STOCK_SUCCESS, updatedStock));
    }

    /**
     * Creates the stock with updated attributes.
     * @param stockToUpdate The stock in the list to be updated.
     * @param updateStockDescriptor The collection of values to be updated.
     * @return The stock with updated attributes.
     */
    private static Stock createUpdatedStock(Stock stockToUpdate, UpdateStockDescriptor updateStockDescriptor) {
        assert stockToUpdate != null;

        Quantity updatedQuantity = updateStockDescriptor.getQuantity().orElse(stockToUpdate.getQuantity());
        Name updatedName = updateStockDescriptor.getName().orElse(stockToUpdate.getName());
        Source updatedSource = updateStockDescriptor.getSource().orElse(stockToUpdate.getSource());
        Location updatedLocation = updateStockDescriptor.getLocation().orElse(stockToUpdate.getLocation());
        boolean isIncrement = updateStockDescriptor.getIsIncrement();

        if (isIncrement) {
            Quantity originalQuantity = stockToUpdate.getQuantity();
            updatedQuantity = originalQuantity.incrementQuantity(updatedQuantity);
        }

        return new Stock(updatedName, new SerialNumber("12"), updatedSource, updatedQuantity, updatedLocation);
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

        private boolean isIncrement;

        public UpdateStockDescriptor() {
            isIncrement = false;
        }

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
            setIsIncrement(toCopy.isIncrement);
        }

        /**
         * Checks if any updates exists.
         * @return A boolean value indicating if an update exists.
         */
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

        public void setIsIncrement(boolean isIncrement) {
            this.isIncrement = isIncrement;
        }

        public boolean getIsIncrement() {
            return isIncrement;
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
                    && getLocation().equals(castedOther.getLocation())
                    && (getIsIncrement() == castedOther.getIsIncrement());
        }
    }
}
