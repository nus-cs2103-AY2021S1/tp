package seedu.address.model.food;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Food item in McGymmy.
 */
public class Food {

    // Identity field names
    private final String name;
    private final Protein protein;
    private final Carbohydrate carbs;
    private final Fat fat;

    /**
     * Every field must be present and not null.
     */
    public Food(String name, Protein protein, Carbohydrate carbs, Fat fat) {
        requireAllNonNull(name, protein, carbs, fat);
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    // Constructor for convenience

    /**
     * Every field must be present and not null.
     * A Constructor made for convenience
     */
    public Food(String name, int proteinAmount, int carbsAmount, int fatAmount) {
        this(name, new Protein(proteinAmount), new Carbohydrate(carbsAmount), new Fat(fatAmount));
    }
    // getters : make when needed

    // isSameFood : make when needed

    // Displays
    // name + PCF details + total calories


    @Override
    public String toString() {
        return "Food:" + this.name + "\n"
            + "protein: " + protein.getAmount() + "\n"
            + "carbs: " + carbs.getAmount() + "\n"
            + "fat: " + fat.getAmount() + "\n";
    }

}
