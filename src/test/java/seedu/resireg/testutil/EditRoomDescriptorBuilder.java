package seedu.resireg.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.resireg.logic.commands.EditRoomCommand.EditRoomDescriptor;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;

/**
 * A utility class to help with building {@code EditStudentDescriptor} objects.
 */
public class EditRoomDescriptorBuilder {
    private final EditRoomDescriptor descriptor;

    public EditRoomDescriptorBuilder() {
        descriptor = new EditRoomDescriptor();
    }

    public EditRoomDescriptorBuilder(EditRoomDescriptor descriptor) {
        this.descriptor = new EditRoomDescriptor(descriptor);
    }

    /**
     * Returns a {@code EditRoomDescriptor} with fields containing the {@code room}'s details.
     */
    public EditRoomDescriptorBuilder(Room room) {
        descriptor = new EditRoomDescriptor();
        descriptor.setFloor(room.getFloor());
        descriptor.setRoomNumber(room.getRoomNumber());
        descriptor.setRoomType(room.getRoomType());
        descriptor.setTags(room.getTags());
    }

    /**
     * Sets the {@code Floor} of the {@code EditRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withFloor(String floor) {
        descriptor.setFloor(new Floor(floor));
        return this;
    }

    /**
     * Sets the {@code RoomNumber} of the {@code EditRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withRoomNumber(String roomNumber) {
        descriptor.setRoomNumber(new RoomNumber(roomNumber));
        return this;
    }

    /**
     * Sets the {@code RoomType} of the {@code EditRoomDescriptor} that we are building.
     */
    public EditRoomDescriptorBuilder withRoomType(String roomType) {
        descriptor.setRoomType(new RoomType(roomType));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditRoomDescriptor}
     * that we are building.
     */
    public EditRoomDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditRoomDescriptor build() {
        return descriptor;
    }
}
