package seedu.medibook.model.medicaldetail;

/**
 * Represents a medical Treatment in the medi book.
 * This {@code Treatment} class inherits from the {@code Tag} class.
 * It is a medical tag describing one of the medical treatments being given to a patient,
 * where the set of treatments is a medical detail of that patient.
 */
public class Treatment extends Tag {

    /**
     * Constructs a medical {@code Treatment}.
     *
     * @param tagName A valid tag name.
     */
    public Treatment(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Treatment // instanceof handles nulls
            && tagName.equals(((Treatment) other).tagName)); // state check
    }
}
