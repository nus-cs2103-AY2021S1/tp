package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Name;

public class EditModuleDescriptorBuilder {
    private EditModuleCommand.EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleCommand.EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleCommand.EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleCommand.EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code person}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleCommand.EditModuleDescriptor();
        descriptor.setModuleName(module.getModuleName());
        descriptor.setMemberNames(module.getClassmates().stream()
                .map(person -> person.getName()).collect(Collectors.toSet()));
    }

    /**
     * Sets the {@code ModuleName} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withModuleName(String moduleName) {
        descriptor.setModuleName(new ModuleName(moduleName));
        return this;
    }

    /**
     * Parses the {@code names} into a {@code Set<Name>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withMembers(String... names) {
        Set<Name> personNameSet = Stream.of(names).map(Name::new).collect(Collectors.toSet());
        descriptor.setMemberNames(personNameSet);
        return this;
    }

    public EditModuleCommand.EditModuleDescriptor build() {
        return descriptor;
    }
}
