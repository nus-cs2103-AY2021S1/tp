package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;

/**
 * A utility class to help with building EditDayDescriptor objects.
 */
public class EditDayDescriptorBuilder {

    private EditCommand.EditDayDescriptor descriptor;

    public EditDayDescriptorBuilder() {
        descriptor = new EditCommand.EditDayDescriptor();
    }

    public EditDayDescriptorBuilder(EditCommand.EditDayDescriptor descriptor) {
        this.descriptor = new EditCommand.EditDayDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDayDescriptor} with fields containing {@code day}'s details
     */
    public EditDayDescriptorBuilder(Day day) {
        descriptor = new EditCommand.EditDayDescriptor();
        descriptor.setDate(day.getDate());
        descriptor.setWeight(day.getWeight());
    }

    /**
     * Sets the {@code Date} of the {@code EditDayDescriptor} that we are building.
     */
    public EditDayDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code EditDayDescriptor} that we are building.
     */
    public EditDayDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    public EditCommand.EditDayDescriptor build() {
        return descriptor;
    }
}
