package seedu.address.model.food;

public class Carbohydrate extends Macronutrient {

    public Carbohydrate(int amount) {
        super(amount, 4);
    }

    @Override
    public String toString() {
        return
            "MacronutrientType: Carbohydrate\n" +
                "Amount: " + this.amount + "\n" +
                "Caloric Count: " + this.totalCalories + "\n";
    }

    // getter
    public int getAmount() {
        return this.amount;
    }

}
