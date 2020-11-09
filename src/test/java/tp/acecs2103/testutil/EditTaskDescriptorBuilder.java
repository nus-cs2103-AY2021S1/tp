package tp.acecs2103.testutil;

import java.time.LocalDate;

import tp.acecs2103.logic.commands.EditCommand.EditTaskDescriptor;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.WeekNumber;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {
    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code Task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setRemark(task.getRemark());
        descriptor.setCustomizedDeadline(task.getCustomizedDeadline());
        if (task.isCustomized()) {
            descriptor.setWeekNumber(task.getWeekNumber());
            descriptor.setDescription(task.getDescription());
        }
    }

    /**
     * Sets the {@code WeekNumber} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withWeekNumber(String weekNumber) {
        descriptor.setWeekNumber(new WeekNumber(weekNumber));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code CustomizedDeadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withCustomizedDeadline(String customizedDeadline) {
        descriptor.setCustomizedDeadline(new CustomizedDeadline(customizedDeadline,
                LocalDate.parse(customizedDeadline)));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
