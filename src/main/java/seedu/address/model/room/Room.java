package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Room {

    // Identity fields
    protected int price;
    protected final int roomID;

    /**
     * Every field must be present and not null.
     */
    public Room(int roomID) {
        requireAllNonNull(roomID);
        this.roomID = roomID;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    /**
     * Returns true if both rooms have the same price and roomId.
     */
    public boolean equals(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
                && otherRoom.getPrice() == (getPrice())
                && otherRoom.getRoomID() == (getRoomID());
    }

    @Override
    public String toString() {
        return "Room{" + "price=" + price + ", roomID=" + roomID + '}';
    }
}
