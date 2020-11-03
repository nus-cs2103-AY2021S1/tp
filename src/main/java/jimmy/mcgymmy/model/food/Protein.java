package jimmy.mcgymmy.model.food;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

/**
 * Represents Protein in food item in McGymmy.
 */
public class Protein extends Macronutrient {
    public static final int MULTIPLIER = 4;
    public static final String MESSAGE_CONSTRAINTS = "Protein" + Macronutrient.MESSAGE_CONSTRAINTS;
    public static final int DEFAULT_PROTEIN = 0;

    /**
     * @param amount A valid amount.
     */
    public Protein(int amount) throws IllegalValueException {
        super(amount, MULTIPLIER);
    }

    /**
     * Create a new Protein using the default value.
     * Mostly boilerplate code.
     * @return new Protein with default value.
     */
    public static Protein newDefault() {
        try {
            return new Protein(DEFAULT_PROTEIN);
        } catch (IllegalValueException e) {
            assert false : "Error in creation of default";
            throw new IllegalArgumentException("Error in creation using default values.");
        }
    }
}
