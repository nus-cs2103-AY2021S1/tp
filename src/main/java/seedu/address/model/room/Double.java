package seedu.address.model.room;

public class Double extends Room {
    private int doublePrice = 100;

    /**
     * Creates a Double Room Object with the standard {@code DOUBLE_PRICE} and given room ID.
     */
    public Double(int roomID) {
        super(roomID);
        this.price = doublePrice;
    }
}
