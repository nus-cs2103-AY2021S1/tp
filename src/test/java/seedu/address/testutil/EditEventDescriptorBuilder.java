package seedu.address.testutil;

import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;

/**
 * A utility class to help with building EditEventDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventCommand.EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns a {@code EditEventDescriptor} with fields containing {@code event}'s details.
     * {@code attendees} is not copied over.
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setEventName(event.getName());
        descriptor.setEventDate(event.getEventDate());
        descriptor.setLocation(event.getLocation());
        descriptor.setDescription(event.getDescription());
    }

    /**
     * Sets the {@code EventName} of the {@EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setEventName(new EventName(name));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDate(String date) {
        descriptor.setEventDate(new EventDate(date));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}
