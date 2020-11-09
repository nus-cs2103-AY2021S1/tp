package seedu.address.testutil;

import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.UpdateCommand.UpdateModNameDescriptor;
import seedu.address.model.module.Grade;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.semester.Semester;

/**
 * A utility class to help with building UpdatePersonDescriptor objects.
 */
public class UpdateModNameDescriptorBuilder {

    private UpdateModNameDescriptor descriptor;

    public UpdateModNameDescriptorBuilder() {
        descriptor = new UpdateCommand.UpdateModNameDescriptor();
    }

    public UpdateModNameDescriptorBuilder(UpdateModNameDescriptor descriptor) {
        this.descriptor = new UpdateModNameDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateModNameDescriptor} with fields containing {@code module}'s details
     */
    public UpdateModNameDescriptorBuilder(Module module) {
        descriptor = new UpdateModNameDescriptor();
        descriptor.setName(module.getModuleName());
        descriptor.setGrade(module.getGrade());
        descriptor.setSemester(module.getSemester());
    }

    /**
     * Sets the {@code module name} of the {@code UpdateModNameDescriptor} that we are building.
     */
    public UpdateModNameDescriptorBuilder withName(String name) {
        descriptor.setName(new ModuleName(name));
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code UpdateModNameDescriptor} that we are building.
     */
    public UpdateModNameDescriptorBuilder withGrade(String grade) {
        descriptor.setGrade(new Grade(grade));
        return this;
    }

    /**
     * Sets the {@code semester} of the {@code EditModNameDescriptor} that we are building.
     */
    public UpdateModNameDescriptorBuilder withSemester(Semester semester) {
        descriptor.setSemester(semester);
        return this;
    }

    public UpdateModNameDescriptor build() {
        return descriptor;
    }
}
