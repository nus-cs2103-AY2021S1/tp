package jimmy.mcgymmy.model.food;

/**
 * Represents Carbodydrates in food item in McGymmy.
 */
public class Carbohydrate extends Macronutrient {
    private static final int CARBOHYDRATE_MULTIPLIER = 4;
    public static final String MESSAGE_CONSTRAINTS = "Carbohydrate" + Macronutrient.MESSAGE_CONSTRAINTS;

    /**
     * @param amount A valid amount.
     */
    public Carbohydrate(int amount) {
        super(amount, CARBOHYDRATE_MULTIPLIER);
    }

    public Carbohydrate(String amount) {
        this(Integer.parseInt(amount));
    }

}
