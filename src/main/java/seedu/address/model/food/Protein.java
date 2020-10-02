package seedu.address.model.food;

/**
 * Represents Protein in food item in AddressBook.
 */
public class Protein extends Macronutrient {
    private static final int PROTEIN_MULTIPLIER = 4;

    /**
     * @param amount A valid amount.
     */
    public Protein(int amount) {
        super(Protein.class.getName(), amount, PROTEIN_MULTIPLIER);
    }

}
