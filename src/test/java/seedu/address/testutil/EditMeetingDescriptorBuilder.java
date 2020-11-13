package seedu.address.testutil;

import seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.meeting.From;
import seedu.address.model.meeting.meeting.Location;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.To;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

public class EditMeetingDescriptorBuilder {
    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code Meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setTitle(meeting.getTitle());
        descriptor.setDescription(meeting.getDescription());
        descriptor.setFrom(meeting.getFrom());
        descriptor.setTo(meeting.getTo());
        descriptor.setContacts(meeting.getContacts());
        descriptor.setLocation(meeting.getLocation());
    }

    /**
     * Sets the {@code Title} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code OptionalDescription} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code From} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withFrom(String from) {
        descriptor.setFrom(new From(from));
        return this;
    }

    /**
     * Sets the {@code To} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withTo(String to) {
        descriptor.setTo(new To(to));
        return this;
    }

    /**
     * Sets the {@code To} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withContacts(String contacts) {
        descriptor.setContacts(new Contacts(contacts));
        return this;
    }

    /**
     * Sets the {@code To} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}

