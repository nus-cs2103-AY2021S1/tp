package seedu.address.model.food;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Macronutrient {
    private String macronutrientType;
    private int amount;
    private int caloricMultiplier;
    private int totalCalories;

    /**
     * Represents macronutrients of 3 types
     *
     * @param macronutrientType The type of the macronutrient
     * @param amount The amount of the macronutrient
     * @param caloricMultiplier This value varies for each macronutrient type
     */
    public Macronutrient(String macronutrientType, int amount, int caloricMultiplier) {
        requireAllNonNull(macronutrientType, amount, caloricMultiplier);

        assert !macronutrientType.equals("") : "Name cannot be blank";

        // use this instead of assert because the amount < 0 error is more because of user input than developer's fault
        checkArgument(isValidAmount(amount), getMessageContraint());

        assert (caloricMultiplier == 4 || caloricMultiplier == 9) : "Invalid Macronutrient Multiplier";
        // initialise variables
        this.macronutrientType = macronutrientType;
        this.amount = amount;
        this.caloricMultiplier = caloricMultiplier;
        this.totalCalories = caloricMultiplier * amount;

    }

    private boolean isValidAmount(int amount) {
        return amount > 0;
    }

    private String getMessageContraint() {
        return this.macronutrientType + " amount can only take in value larger than 0";
    }

    @Override
    public String toString() {
        return "MacronutrientType:" + this.macronutrientType + "\n"
            + "Amount: "
            + this.amount + "\n"
            + "Caloric Count: " + this.totalCalories + "\n";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Macronutrient)) {
            return false;
        }

        Macronutrient otherMacronutrient = (Macronutrient) other;
        return this.getMacronutrientType().equals(otherMacronutrient.getMacronutrientType())
            && this.getAmount() == otherMacronutrient.getAmount()
            && this.getCaloricMultiplier() == otherMacronutrient.getCaloricMultiplier();
    }

    public String getMacronutrientType() {
        return macronutrientType;
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
