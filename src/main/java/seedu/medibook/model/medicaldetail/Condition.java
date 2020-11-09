package seedu.medibook.model.medicaldetail;

/**
 * Represents a medical Condition in the medi book.
 * This {@code Condition} class inherits from the {@code Tag} class.
 * It is a medical tag describing one of the medical conditions that a patient is diagnosed with,
 * where the set of conditions is a medical detail of that patient.
 */
public class Condition extends Tag {

    /**
     * Constructs a medical {@code Condition}.
     *
     * @param tagName A valid tag name.
     */
    public Condition(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Condition // instanceof handles nulls
            && tagName.equals(((Condition) other).tagName)); // state check
    }
}
