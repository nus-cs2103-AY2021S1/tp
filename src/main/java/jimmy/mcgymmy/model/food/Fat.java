package jimmy.mcgymmy.model.food;


/**
 * Represents Fats in food item in McGymmy.
 */
public class Fat extends Macronutrient {
    private static final int FAT_MULTIPLIER = 9;
    public static final String MESSAGE_CONSTRAINTS = "Fat" + Macronutrient.MESSAGE_CONSTRAINTS;

    /**
     * @param amount A valid amount.
     */
    public Fat(int amount) {
        super(amount, FAT_MULTIPLIER);
    }

    public Fat(String amount) {
        this(Integer.parseInt(amount));
    }
}
