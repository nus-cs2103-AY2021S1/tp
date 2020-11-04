package nustorage.testutil;

import nustorage.logic.commands.UpdateInventoryCommand;
import nustorage.logic.commands.UpdateInventoryCommand.UpdateInventoryDescriptor;
import nustorage.model.record.InventoryRecord;

public class UpdateInventoryDescriptorBuilder {
    private UpdateInventoryDescriptor descriptor;

    public UpdateInventoryDescriptorBuilder() {
        descriptor = new UpdateInventoryDescriptor();
    }

    public UpdateInventoryDescriptorBuilder(int changeInQuantity) {
        descriptor = new UpdateInventoryDescriptor();
        descriptor.setChangeInQuantity(changeInQuantity);
    }

    public UpdateInventoryDescriptor build() {
        return descriptor;
    }
}
