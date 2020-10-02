package seedu.address.model.food;


/**
 * Represents Fats in food item in AddressBook.
 */
public class Fat extends Macronutrient {
    private static final int FAT_MULTIPLIER = 9;

    /**
     * @param amount A valid amount.
     */
    public Fat(int amount) {
        super(Fat.class.getName(), amount, FAT_MULTIPLIER);
    }

}
