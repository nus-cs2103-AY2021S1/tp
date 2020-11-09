package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.logic.commands.EditBookingCommand;
import seedu.address.model.booking.Booking;

public class EditBookingDescriptorBuilder {
    private EditBookingCommand.EditBookingDescriptor descriptor;

    public EditBookingDescriptorBuilder() {
        descriptor = new EditBookingCommand.EditBookingDescriptor();
    }

    public EditBookingDescriptorBuilder(EditBookingCommand.EditBookingDescriptor descriptor) {
        this.descriptor = new EditBookingCommand.EditBookingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookingDescriptor} with fields containing {@code booking}'s details
     */
    public EditBookingDescriptorBuilder(Booking booking) {
        descriptor = new EditBookingCommand.EditBookingDescriptor();
        descriptor.setRoomId(booking.getRoomId());
        descriptor.setStartDate(booking.getStartDate());
        descriptor.setEndDate(booking.getEndDate());
    }

    /**
     * Sets the {@code RoomId} of the {@code EditBookingDescriptor} that we are building.
     */
    public EditBookingDescriptorBuilder withRoomId(int roomId) {
        descriptor.setRoomId(roomId);
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditBookingDescriptor} that we are building.
     */
    public EditBookingDescriptorBuilder withStartDate(LocalDate startDate) {
        descriptor.setStartDate(startDate);
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code EditBookingDescriptor} that we are building.
     */
    public EditBookingDescriptorBuilder withEndDate(LocalDate endDate) {
        descriptor.setEndDate(endDate);
        return this;
    }

    public EditBookingCommand.EditBookingDescriptor build() {
        return descriptor;
    }

}
