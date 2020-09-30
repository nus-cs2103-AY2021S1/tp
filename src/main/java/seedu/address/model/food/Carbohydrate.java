package seedu.address.model.food;

public class Carbohydrate extends Macronutrient {
    private static final int CARBOHYDRATE_MULTIPLIER = 4;

    public Carbohydrate(int amount) {
        super("Carbohydrate", amount, CARBOHYDRATE_MULTIPLIER);
    }

}
