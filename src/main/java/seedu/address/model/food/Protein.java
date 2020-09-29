package seedu.address.model.food;

public class Protein extends Macronutrient {

    public Protein(int amount) {
        super(amount, 4);
    }

    @Override
    public String toString() {
        return
            "MacronutrientType: Protein\n" +
                "Amount: "+this.amount+"\n"+
                "Caloric Count: "+this.totalCalories+"\n";
    }

    // getter
    public int getAmount() {
        return this.amount;
    }

}
