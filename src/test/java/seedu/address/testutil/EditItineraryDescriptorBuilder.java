package seedu.address.testutil;

import seedu.address.logic.commands.itinerary.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryDate;

/**
 * A utility class to help with building EditItineraryDescriptor objects.
 */
public class EditItineraryDescriptorBuilder {
    private EditItineraryDescriptor descriptor;

    public EditItineraryDescriptorBuilder() {
        descriptor = new EditItineraryDescriptor();
    }

    public EditItineraryDescriptorBuilder(EditItineraryDescriptor descriptor) {
        this.descriptor = new EditItineraryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItineraryDescriptor} with fields containing {@code itinerary}'s details
     */
    public EditItineraryDescriptorBuilder(Itinerary itinerary) {
        descriptor = new EditItineraryDescriptor();
        descriptor.setName(itinerary.getName());
        descriptor.setDescription(itinerary.getDescription());
        descriptor.setStartDate(itinerary.getStartDate());
        descriptor.setEndDate(itinerary.getEndDate());
        descriptor.setBudget(itinerary.getBudget());
    }

    /**
     * Sets the {@code Name} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new ItineraryDate(startDate));
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new ItineraryDate(endDate));
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code EditItineraryDescriptor} that we are building.
     */
    public EditItineraryDescriptorBuilder withBudget(String budget) {
        descriptor.setBudget(new Budget(budget));
        return this;
    }

    public EditItineraryDescriptor build() {
        return descriptor;
    }
}
