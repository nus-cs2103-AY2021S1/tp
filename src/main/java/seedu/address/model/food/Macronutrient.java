package seedu.address.model.food;

public abstract class Macronutrient {
    int amount;

    // Constructor
    public Macronutrient(int amount) {
        assert amount>=0;System.out.println("amount cannot be negative");
        this.amount = amount;
    }

}
