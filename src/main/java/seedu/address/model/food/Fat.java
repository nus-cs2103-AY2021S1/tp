package seedu.address.model.food;

public class Fat extends Macronutrient {
    private static final int FAT_MULTIPLIER = 9;

    public Fat(int amount) {
        super("Fat", amount, FAT_MULTIPLIER);
    }
    // getter
    public int getAmount() {
        return this.amount;
    }

}
