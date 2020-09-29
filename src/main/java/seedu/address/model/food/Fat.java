package seedu.address.model.food;

public class Fat extends Macronutrient {

    public Fat(int amount) {
        super(amount, 9);
    }

    @Override
    public String toString() {
        return
            "MacronutrientType: Fats\n" +
                "Amount: " + this.amount + "\n" +
                "Caloric Count: " + this.totalCalories + "\n";
    }

    // getter
    public int getAmount() {
        return this.amount;
    }

}
