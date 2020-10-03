package seedu.address.model.food;


/**
 * Represents Fats in food item in McGymmy.
 */
public class Fat extends Macronutrient {
    private static final int FAT_MULTIPLIER = 9;

    /**
     * @param amount A valid amount.
     */
    public Fat(int amount) {
        super(amount, FAT_MULTIPLIER);
    }

}
