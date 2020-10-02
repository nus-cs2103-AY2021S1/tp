package seedu.address.model.food;

/**
 * Represents Carbodydrates in food item in AddressBook.
 */
public class Carbohydrate extends Macronutrient {
    private static final int CARBOHYDRATE_MULTIPLIER = 4;

    /**
     * @param amount A valid amount.
     */
    public Carbohydrate(int amount) {
        super(Carbohydrate.class.getName(), amount, CARBOHYDRATE_MULTIPLIER);
    }

}
