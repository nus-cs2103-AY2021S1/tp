package seedu.address.testutil;

import seedu.address.logic.commands.room.SearchRoomCommand.SearchRoomDescriptor;
import seedu.address.model.patient.Name;

//@@author chiamyunqing
/**
 * Builds SearchRoomDescriptor for testing.
 */
public class SearchRoomDescriptorBuilder {

    private SearchRoomDescriptor descriptor;

    public SearchRoomDescriptorBuilder() {
        descriptor = new SearchRoomDescriptor();
    }

    /**
     * Sets the {@code Name} of the {@code SearchRoomDescriptor} that we are building.
     */
    public SearchRoomDescriptorBuilder setPatientName(String name) {
        descriptor.setPatientName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Room Number} of the {@code SearchRoomDescriptor} that we are building.
     */
    public SearchRoomDescriptorBuilder setRoomNumber(Integer roomNumber) {
        descriptor.setRoomNumber(roomNumber);
        return this;
    }

    public SearchRoomDescriptor build() {
        return descriptor;
    }
}
