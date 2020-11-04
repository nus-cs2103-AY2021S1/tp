package nustorage.testutil;

import nustorage.logic.commands.UpdateInventoryCommand.UpdateInventoryDescriptor;

public class UpdateInventoryDescriptorBuilder {
    private UpdateInventoryDescriptor descriptor;

    public UpdateInventoryDescriptorBuilder() {
        descriptor = new UpdateInventoryDescriptor();
    }

    /**
     * The constructor for the builder object
     * @param changeInQuantity the change in quantity parameter for the descriptor
     */
    public UpdateInventoryDescriptorBuilder(int changeInQuantity) {
        descriptor = new UpdateInventoryDescriptor();
        descriptor.setChangeInQuantity(changeInQuantity);
    }

    public UpdateInventoryDescriptor build() {
        return descriptor;
    }
}
