package jimmy.mcgymmy.model.food;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

/**
 * Represents Protein in food item in McGymmy.
 */
public class Protein extends Macronutrient {
    public static final int MULTIPLIER = 4;
    public static final String MESSAGE_CONSTRAINTS =
            "Protein " + Macronutrient.MESSAGE_CONSTRAINTS;

    /**
     * @param amount A valid amount.
     */
    public Protein(int amount) throws IllegalValueException {
        super(amount, MULTIPLIER);
    }

    public Protein(String amount) throws IllegalValueException {
        this(Integer.parseInt(amount));
    }

}
