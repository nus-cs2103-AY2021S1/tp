package seedu.address.testutil;

import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;

public class EditAccommodationDescriptorBuilder {
    private EditDescriptor descriptor;

    public EditAccommodationDescriptorBuilder() {
        descriptor = new EditDescriptor();
    }

    public EditAccommodationDescriptorBuilder(EditDescriptor descriptor) {
        this.descriptor = new EditDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAccommodationDescriptor} with fields containing {@code person}'s details
     */
    public EditAccommodationDescriptorBuilder(Accommodation accommodation) {
        descriptor = new EditDescriptor();
        descriptor.setName(accommodation.getName());
        descriptor.setStartDate(accommodation.getStartDate());
        descriptor.setEndDate(accommodation.getEndDate());
        descriptor.setCost(accommodation.getCost());
        descriptor.setLocation(accommodation.getLocation());
    }

    /**
     * Sets the {@code Name} of the {@code EditAccommodationDescriptor} that we are building.
     */
    public EditAccommodationDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditAccommodationDescriptor} that we are building.
     */
    public EditAccommodationDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditAccommodationDescriptor} that we are building.
     */
    public EditAccommodationDescriptorBuilder withCost(String cost) {
        descriptor.setCost(new Cost(cost));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTravelPlanDescriptor} that we are building.
     */
    public EditAccommodationDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new WanderlustDate(startDate));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTravelPlanDescriptor} that we are building.
     */
    public EditAccommodationDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new WanderlustDate(endDate));
        return this;
    }

    public EditDescriptor build() {
        return descriptor;
    }
}
