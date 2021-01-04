package seedu.homerce.testutil.service;

import seedu.homerce.logic.commands.service.EditServiceCommand;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;

/**
 * A utility class to help with building EditServiceDescriptor objects.
 */
public class EditServiceDescriptorBuilder {

    private EditServiceCommand.EditServiceDescriptor descriptor;

    public EditServiceDescriptorBuilder() {
        descriptor = new EditServiceCommand.EditServiceDescriptor();
    }

    public EditServiceDescriptorBuilder(EditServiceCommand.EditServiceDescriptor descriptor) {
        this.descriptor = new EditServiceCommand.EditServiceDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditServiceDescriptor} with fields containing {@code service}'s details
     */
    public EditServiceDescriptorBuilder(Service service) {
        descriptor = new EditServiceCommand.EditServiceDescriptor();
        descriptor.setTitle(service.getTitle());
        descriptor.setDuration(service.getDuration());
        descriptor.setAmount(service.getAmount());
        descriptor.setServiceCode(service.getServiceCode());
    }

    /**
     * Sets the {@code Title} of the {@code EditServiceDescriptor} that we are building.
     */
    public EditServiceDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditServiceDescriptor} that we are building.
     */
    public EditServiceDescriptorBuilder withDuration(Double duration) {
        descriptor.setDuration(new Duration(duration));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditServiceDescriptor} that we are building.
     */
    public EditServiceDescriptorBuilder withAmount(Double amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Parses the {@code serviceCode} into a {@code ServiceCode} and set it to the {@code EditServiceDescriptor}
     * that we are building.
     */
    public EditServiceDescriptorBuilder withServiceCode(String serviceCode) {
        descriptor.setServiceCode(new ServiceCode(serviceCode));
        return this;
    }

    public EditServiceCommand.EditServiceDescriptor build() {
        return descriptor;
    }
}
