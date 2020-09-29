package seedu.stock.logic.commands;

import java.util.Optional;

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

    public UpdateCommand() {}

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
