package seedu.address.testutil;

import seedu.address.model.RoomServiceBook;
import seedu.address.model.roomservice.RoomService;

public class RoomServiceBookBuilder {
    private RoomServiceBook roomServiceBook;

    public RoomServiceBookBuilder() {
        roomServiceBook = new RoomServiceBook();
    }

    public RoomServiceBookBuilder(RoomServiceBook roomServiceBook) {
        this.roomServiceBook = roomServiceBook;
    }

    /**
     * Adds a new {@code RoomService} to the {@code RoomServiceBook} that we are building.
     */
    public RoomServiceBookBuilder withRoomService(RoomService roomService) {
        roomServiceBook.addRoomService(roomService);
        return this;
    }

    public RoomServiceBook build() {
        return roomServiceBook;
    }
}
