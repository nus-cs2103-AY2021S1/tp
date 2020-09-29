package seedu.address.model.food;

public abstract class Macronutrient {
    protected int amount;
    protected int caloricMultiplier;
    protected int totalCalories;

    // Constructor
    public Macronutrient(int amount, int caloricMultiplier) {
        assert amount>=0;
        System.out.println("amount cannot be negative");

        assert (caloricMultiplier==4 ||caloricMultiplier==9);
        System.out.println("caloric multiplier must be 7 or 9");

        this.amount = amount;
        this.caloricMultiplier= caloricMultiplier;
        this.totalCalories = caloricMultiplier*amount;

    }

}
