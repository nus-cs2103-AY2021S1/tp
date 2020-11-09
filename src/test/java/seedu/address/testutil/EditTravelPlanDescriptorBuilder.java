package seedu.address.testutil;

import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.travelplan.TravelPlan;



public class EditTravelPlanDescriptorBuilder {
    private EditDescriptor descriptor;

    public EditTravelPlanDescriptorBuilder() {
        descriptor = new EditDescriptor();
    }

    public EditTravelPlanDescriptorBuilder(EditDescriptor descriptor) {
        this.descriptor = new EditDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTravelPlanDescriptor} with fields containing {@code person}'s details
     */
    public EditTravelPlanDescriptorBuilder(TravelPlan tp) {
        descriptor = new EditDescriptor();
        descriptor.setName(tp.getName());
        descriptor.setStartDate(tp.getStartDate());
        descriptor.setEndDate(tp.getEndDate());
    }

    /**
     * Sets the {@code Name} of the {@code EditTravelPlanDescriptor} that we are building.
     */
    public EditTravelPlanDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTravelPlanDescriptor} that we are building.
     */
    public EditTravelPlanDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new WanderlustDate(startDate));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTravelPlanDescriptor} that we are building.
     */
    public EditTravelPlanDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new WanderlustDate(endDate));
        return this;
    }

    public EditDescriptor build() {
        return descriptor;
    }
}
