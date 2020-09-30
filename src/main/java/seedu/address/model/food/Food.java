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

    private String getName() {
        return this.name;
    }

    private Protein getProtein() {
        return this.protein;
    }

    private Carbohydrate getCarbs() {
        return this.carbs;
    }

    private Fat getFat() {
        return this.fat;
    }

    /**
     * Compare if two food item are the same
     * Compare criteria: same name (case sensitive) and same nutrition values
     * @param otherFood Other food to be compared with this food
     * @return True if the 2 are the same
     */
    public boolean isSameFood(Food otherFood) {
        requireAllNonNull(protein, carbs, fat);
        if (otherFood == this) {
            return true;
        }

        return otherFood != null
            && this.getName().equals(otherFood.getName())
            && this.getProtein().equals(otherFood.getProtein())
            && this.getCarbs().equals(otherFood.getCarbs())
            && this.getFat().equals(otherFood.getFat());
    }

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
