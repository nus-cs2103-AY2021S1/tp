package seedu.address.model.room;

public class Suite extends Room {
    private int suitePrice = 150;

    /**
     * Creates a Suite Room Object with the standard {@code SUITE_PRICE} and given room ID.
     */
    public Suite(int roomID) {
        super(roomID);
        this.price = suitePrice;
    }
}
