package seedu.jarvis.testutil;

import seedu.jarvis.logic.commands.edit.EditMasteryCheckCommand;
import seedu.jarvis.model.masterycheck.MasteryCheck;

public class EditMasteryCheckDescriptorBuilder {
    private EditMasteryCheckCommand.EditMasteryCheckDescriptor descriptor;

    public EditMasteryCheckDescriptorBuilder() {
        descriptor = new EditMasteryCheckCommand.EditMasteryCheckDescriptor();
    }

    public EditMasteryCheckDescriptorBuilder(EditMasteryCheckCommand.EditMasteryCheckDescriptor descriptor) {
        this.descriptor = new EditMasteryCheckCommand.EditMasteryCheckDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with the boolean value containing {@code mastery check}'s pass status
     */
    public EditMasteryCheckDescriptorBuilder(MasteryCheck masteryCheck) {
        descriptor = new EditMasteryCheckCommand.EditMasteryCheckDescriptor();
        descriptor.setPassed(masteryCheck.hasPassed());
    }

    /**
     * Sets the {@code hasPassed} of the {@code EditMasteryCheckDescriptorBuilder} that we are building.
     */
    public EditMasteryCheckDescriptorBuilder withHasPassed(boolean hasPassed) {
        descriptor.setPassed(hasPassed);
        return this;
    }

    public EditMasteryCheckCommand.EditMasteryCheckDescriptor build() {
        return descriptor;
    }
}
