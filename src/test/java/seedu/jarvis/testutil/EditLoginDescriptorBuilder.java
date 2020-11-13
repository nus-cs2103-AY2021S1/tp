package seedu.jarvis.testutil;

import seedu.jarvis.logic.commands.edit.EditLoginCommand.EditLoginDescriptor;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.login.Username;

public class EditLoginDescriptorBuilder {
    private EditLoginDescriptor descriptor;

    public EditLoginDescriptorBuilder() {
        descriptor = new EditLoginDescriptor();
    }

    public EditLoginDescriptorBuilder(EditLoginDescriptor descriptor) {
        this.descriptor = new EditLoginDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLoginDescriptor} with fields containing {@code login} details
     */
    public EditLoginDescriptorBuilder(UserLogin userLogin) {
        descriptor = new EditLoginDescriptor();
        descriptor.setUsername(userLogin.getUsername());
        descriptor.setPassword(userLogin.getUserPassword());
    }

    /**
     * Sets the {@code Username} of the {@code EditLoginDescriptor} that we are building.
     */
    public EditLoginDescriptorBuilder withUsername(String username) {
        descriptor.setUsername(new Username(username));
        return this;
    }

    /**
     * Sets the {@code Password} of the {@code EditLoginDescriptor} that we are building.
     */
    public EditLoginDescriptorBuilder withPassword(String password) {
        descriptor.setPassword(password);
        return this;
    }

    public EditLoginDescriptor build() {
        return descriptor;
    }
}
