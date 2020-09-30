package seedu.address.model.food;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Macronutrient {
    private String type;
    private int amount;
    private int caloricMultiplier;
    private int totalCalories;

    /**
     * Represents macronutrients of 3 types
     *
     * @param amount
     * @param caloricMultiplier
     */
    public Macronutrient(String type, int amount, int caloricMultiplier) {
        requireAllNonNull(type, amount, caloricMultiplier);

        assert !type.equals("") : "Name cannot be blank";

        // use this instead of assert because the amount < 0 error is more because of user input than developer's fault
        checkArgument(isValidAmount(amount), getMessageContraint());

        assert (caloricMultiplier == 4 || caloricMultiplier == 9) : "Invalid Macronutrient Multiplier";
        // initialise variables
        this.type = type;
        this.amount = amount;
        this.caloricMultiplier = caloricMultiplier;
        this.totalCalories = caloricMultiplier * amount;

    }

    private boolean isValidAmount(int amount) {
        return amount > 0;
    }

    private String getMessageContraint() {
        return this.type + " amount can only take in value larger than 0";
    }

    @Override
    public String toString() {
        return "MacronutrientType:" + this.type + "\n"
            + "Amount: "
            + this.amount + "\n"
            + "Caloric Count: " + this.totalCalories + "\n";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Macronutrient)) {
            return false;
        }

        Macronutrient otherMacronutrient = (Macronutrient) other;
        return this.getType().equals(otherMacronutrient.getType())
            && this.getTotalCalories() == otherMacronutrient.getTotalCalories();
    }

    public String getType() {
        return type;
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
