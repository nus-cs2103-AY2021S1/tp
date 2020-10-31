package jimmy.mcgymmy.model.food;


import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

/**
 * Represents Fats in food item in McGymmy.
 */
public class Fat extends Macronutrient {
    public static final String MESSAGE_CONSTRAINTS =
            "Fat " + Macronutrient.MESSAGE_CONSTRAINTS;
    private static final int FAT_MULTIPLIER = 9;

    /**
     * @param amount A valid amount.
     */
    public Fat(int amount) throws IllegalValueException {
        super(amount, FAT_MULTIPLIER);
    }

    public Fat(String amount) throws IllegalValueException {
        this(Integer.parseInt(amount));
    }
}
