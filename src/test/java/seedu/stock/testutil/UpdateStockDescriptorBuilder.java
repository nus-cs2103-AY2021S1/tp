package seedu.stock.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.stock.logic.commands.UpdateCommand.UpdateStockDescriptor;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

/**
 * A utility class to help with building UpdateStockDescriptor objects.
 */
public class UpdateStockDescriptorBuilder {

    private UpdateStockDescriptor descriptor;

    public UpdateStockDescriptorBuilder() {
        descriptor = new UpdateStockDescriptor();
    }

    public UpdateStockDescriptorBuilder(UpdateStockDescriptor descriptor) {
        this.descriptor = new UpdateStockDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateStockDescriptorBuilder} with fields containing {@code stock}'s details
     */
    public UpdateStockDescriptorBuilder(Stock stock) {
        descriptor = new UpdateStockDescriptor();
        List<SerialNumber> currentSerialNumber = new ArrayList<>();
        currentSerialNumber.add(stock.getSerialNumber());
        descriptor.setName(stock.getName());
        descriptor.setSerialNumbers(currentSerialNumber);
        descriptor.setQuantity(stock.getQuantity());
        descriptor.setLocation(stock.getLocation());
        descriptor.setSource(stock.getSource());
    }

    /**
     * Sets the {@code Name} of the {@code UpdateStockDescriptor} that we are building.
     */
    public UpdateStockDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code SerialNumber} of the {@code UpdateStockDescriptor} that we are building.
     */
    public UpdateStockDescriptorBuilder withSerialNumber(String... serialNumber) {
        List<SerialNumber> currentSerialNumber = new ArrayList<>();
        for (String serial : serialNumber) {
            currentSerialNumber.add(new SerialNumber(serial));
        }
        descriptor.setSerialNumbers(currentSerialNumber);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code UpdateStockDescriptor} that we are building.
     */
    public UpdateStockDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code QuantityAdder} of the {@code UpdateStockDescriptor} that we are building.
     * @param quantityToBeAdded The quantity to be added into a quantity object.
     * @return A new {@code UpdateStockDescriptor} with the {@code QuantityAdder}
     */
    public UpdateStockDescriptorBuilder withQuantityAdder(String quantityToBeAdded) {
        descriptor.setQuantityAdder(new QuantityAdder(quantityToBeAdded));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code UpdateStockDescriptor} that we are building.
     */
    public UpdateStockDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Source} of the {@code UpdateStockDescriptor} that we are building.
     */
    public UpdateStockDescriptorBuilder withSource(String source) {
        descriptor.setSource(new Source(source));
        return this;
    }

    /**
     * Builds the update stock descriptor required.
     * @return The update stock descriptor required.
     */
    public UpdateStockDescriptor build() {
        return descriptor;
    }
}
