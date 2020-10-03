package seedu.address.model.food;

/**
 * Represents Protein in food item in McGymmy.
 */
public class Protein extends Macronutrient {
    private static final int PROTEIN_MULTIPLIER = 4;

    /**
     * @param amount A valid amount.
     */
    public Protein(int amount) {
        super(amount, PROTEIN_MULTIPLIER);
    }

}
