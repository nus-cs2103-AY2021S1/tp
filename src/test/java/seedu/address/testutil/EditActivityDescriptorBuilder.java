package seedu.address.testutil;

import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;


public class EditActivityDescriptorBuilder extends EditDescriptor {
    private EditDescriptor descriptor;

    public EditActivityDescriptorBuilder() {
        descriptor = new EditDescriptor();
    }

    public EditActivityDescriptorBuilder(EditDescriptor descriptor) {
        this.descriptor = new EditDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditActivityDescriptor} with fields containing {@code person}'s details
     */
    public EditActivityDescriptorBuilder(Activity activity) {
        descriptor = new EditDescriptor();
        descriptor.setName(activity.getName());
        descriptor.setCost(activity.getCost());
        descriptor.setLevelOfImportance(activity.getLevelOfImportance());
        descriptor.setActivityDateTime(activity.getActivityDateTime());
        descriptor.setLocation(activity.getLocation());
    }

    /**
     * Sets the {@code Name} of the {@code EditActivityDescriptor} that we are building.
     */
    public EditActivityDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditActivityDescriptor} that we are building.
     */
    public EditActivityDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditActivityDescriptor} that we are building.
     */
    public EditActivityDescriptorBuilder withCost(String cost) {
        descriptor.setCost(new Cost(cost));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditActivityDescriptor} that we are building.
     */
    public EditActivityDescriptorBuilder withImportance(String importance) {
        descriptor.setLevelOfImportance(new Importance(importance));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditActivityDescriptor} that we are building.
     */
    public EditActivityDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setActivityDateTime(new WanderlustDateTime(dateTime));
        return this;
    }

    public EditDescriptor build() {
        return descriptor;
    }
}
