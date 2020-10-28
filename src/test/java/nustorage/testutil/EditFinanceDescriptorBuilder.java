package nustorage.testutil;

import java.time.LocalDateTime;

import nustorage.logic.commands.EditFinanceCommand.EditFinanceDescriptor;
import nustorage.model.record.FinanceRecord;

public class EditFinanceDescriptorBuilder {
    private EditFinanceDescriptor descriptor;

    public EditFinanceDescriptorBuilder() {
        descriptor = new EditFinanceDescriptor();
    }

    public EditFinanceDescriptorBuilder(EditFinanceDescriptor descriptor) {
        this.descriptor = new EditFinanceDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFinanceDescriptor} with fields containing {@code financeRecord}'s details
     */
    public EditFinanceDescriptorBuilder(FinanceRecord financeRecord) {
        descriptor = new EditFinanceDescriptor();
        descriptor.setAmount(financeRecord.getAmount());
        descriptor.setDatetime(financeRecord.getDateTime());
    }

    /**
     * Sets the amount of the {@code EditFinanceDescriptor} that we are building.
     */
    public EditFinanceDescriptorBuilder withAmount(double amount) {
        descriptor.setAmount(amount);
        return this;
    }

    /**
     * Sets the date and time of the {@code EditFinanceDescriptor} that we are building.
     */
    public EditFinanceDescriptorBuilder withDateTime(LocalDateTime dateTime) {
        descriptor.setDatetime(dateTime);
        return this;
    }

    public EditFinanceDescriptor build() {
        return descriptor;
    }
}
