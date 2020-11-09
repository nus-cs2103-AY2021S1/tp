package seedu.address.model.room;

public class Single extends Room {
    private int singlePrice = 70;
    /**
     * Creates a Single Room Object with the standard {@code SINGLE_PRICE} and given room ID.
     */
    public Single(int roomID) {
        super(roomID);
        this.price = singlePrice;
    }
}
