package seedu.address.testutil;

import seedu.address.logic.commands.EditModuleClassCommand;
import seedu.address.logic.commands.EditModuleClassCommand.EditModuleClassDescriptor;
import seedu.address.model.components.name.Name;
import seedu.address.model.moduleclass.ModuleClass;

/**
 * A utility class to help with building EditModuleClassDescriptor objects.
 */
public class EditModuleClassDescriptorBuilder {

    private EditModuleClassDescriptor descriptor;

    public EditModuleClassDescriptorBuilder() {
        descriptor = new EditModuleClassDescriptor();
    }

    public EditModuleClassDescriptorBuilder(EditModuleClassDescriptor descriptor) {
        this.descriptor = new EditModuleClassDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleClassDescriptor} with fields containing {@code moduleClass}'s details.
     */
    public EditModuleClassDescriptorBuilder(ModuleClass moduleClass) {
        descriptor = new EditModuleClassCommand.EditModuleClassDescriptor();
        descriptor.setName(moduleClass.getName());
    }

    /**
     * Sets the {@code Name} of the {@code EditModuleClassDescriptor} that we are building.
     */
    public EditModuleClassDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    public EditModuleClassDescriptor build() {
        return descriptor;
    }
}
