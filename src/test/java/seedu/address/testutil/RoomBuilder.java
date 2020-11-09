package seedu.address.testutil;

import seedu.address.model.room.Room;
import seedu.address.model.room.Single;

/**
 * A utility class to help with building Room objects.
 */
public class RoomBuilder {
    public static final int DEFAULT_ROOMID = 2103;
    public static final int DEFAULT_PRICE = 100;

    private int roomId;
    private int price;

    /**
     * Default Constructor for RoomBuilder.
     */
    public RoomBuilder() {
        this.price = DEFAULT_PRICE;
        this.roomId = DEFAULT_ROOMID;
    }

    /**
     * Constructs a {@code RoomBuilder}
     * @param tobeCopied a valid Room.
     */
    public RoomBuilder(Room tobeCopied) {
        this.roomId = tobeCopied.getRoomID();
        this.price = tobeCopied.getPrice();
    }

    /**
     * Sets RoomBuilder with new RoomID.
     * @param roomId new RoomID
     * @return a {@code RoomBuilder} with modified roomID
     */
    public RoomBuilder withRoomID(int roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * Sets RoomBuilder with new Price
     * @param price Price for room
     * @return a {@code RoomBuilder} with modified price
     */
    public RoomBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    /**
     * Creates a Room object from the current fields.
     * @return {@code Room}
     */
    public Room build() {
        Room ret = new Single(this.roomId);
        ret.setPrice(this.price);
        return ret;
    }
}
