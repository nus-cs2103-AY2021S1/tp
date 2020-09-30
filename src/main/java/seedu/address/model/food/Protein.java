package seedu.address.model.food;

public class Protein extends Macronutrient {
    private static final int PROTEIN_MULTIPLIER = 4;

    public Protein(int amount) {
        super("Protein", amount, PROTEIN_MULTIPLIER);
    }

}
