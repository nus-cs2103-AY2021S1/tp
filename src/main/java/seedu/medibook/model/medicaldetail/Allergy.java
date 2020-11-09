package seedu.medibook.model.medicaldetail;

/**
 * Represents an Allergy in the medi book.
 * This {@code Allergy} class inherits from the {@code Tag} class.
 * It is a medical tag describing one of the allergies that a patient has,
 * where the set of allergies is a medical detail of that patient.
 */
public class Allergy extends Tag {

    /**
     * Constructs an {@code Allergy}.
     *
     * @param tagName A valid tag name.
     */
    public Allergy(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Allergy // instanceof handles nulls
            && tagName.equals(((Allergy) other).tagName)); // state check
    }
}
