package seedu.address.model.food;

public abstract class Macronutrient {
    private String name;
    private int amount;
    private int caloricMultiplier;
    private int totalCalories;

    /**
     * Represents macronutrients of 3 types
     *
     * @param amount
     * @param caloricMultiplier
     */
    public Macronutrient(String name, int amount, int caloricMultiplier) {
        assert amount >= 0;
        System.out.println("amount cannot be negative");

        assert (caloricMultiplier == 4 || caloricMultiplier == 9);
        System.out.println("caloric multiplier must be 7 or 9");
        // initialise variables
        this.name = name;
        this.amount = amount;
        this.caloricMultiplier = caloricMultiplier;
        this.totalCalories = caloricMultiplier * amount;

    }

    @Override
    public String toString() {
        return "MacronutrientType:" + this.name + "\n"
            + "Amount: "
            + this.amount + "\n"
            + "Caloric Count: " + this.totalCalories + "\n";
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getCaloricMultiplier() {
        return caloricMultiplier;
    }

    public int getTotalCalories() {
        return totalCalories;
    }
}
