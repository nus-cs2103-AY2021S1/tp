package seedu.resireg.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;
import seedu.resireg.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class RoomBuilder {

    public static final String DEFAULT_FLOOR = "8";
    public static final String DEFAULT_ROOM_NUMBER = "105";
    public static final String DEFAULT_ROOM_TYPE = "CN";

    private Floor floor;
    private RoomNumber roomNumber;
    private RoomType roomType;
    private Set<Tag> tags;

    /**
     * Creates a {@code RoomBuilder} with the default details.
     */
    public RoomBuilder() {
        floor = new Floor(DEFAULT_FLOOR);
        roomNumber = new RoomNumber(DEFAULT_ROOM_NUMBER);
        roomType = new RoomType(DEFAULT_ROOM_TYPE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the RoomBuilder with the data of {@code roomToCopy}.
     */
    public RoomBuilder(Room roomToCopy) {
        floor = roomToCopy.getFloor();
        roomNumber = roomToCopy.getRoomNumber();
        roomType = roomToCopy.getRoomType();
        tags = new HashSet<>(roomToCopy.getTags());
    }

    /**
     * Sets the {@code Floor} of the {@code Room} that we are building.
     */
    public RoomBuilder withFloor(String floor) {
        this.floor = new Floor(floor);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Room} that we are building.
     */
    public RoomBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code RoomType} of the {@code Student} that we are building.
     */
    public RoomBuilder withRoomType(String type) {
        this.roomType = new RoomType(type);
        return this;
    }

    /**
     * Sets the {@code RoomNumber} of the {@code Room} that we are building.
     */
    public RoomBuilder withRoomNumber(String number) {
        this.roomNumber = new RoomNumber(number);
        return this;
    }

    public Room build() {
        return new Room(floor, roomNumber, roomType, tags);
    }

}
