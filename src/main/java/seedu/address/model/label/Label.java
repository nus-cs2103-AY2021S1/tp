package seedu.address.model.label;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Label {
    public static final String MESSAGE_CONSTRAINTS =
            "Label should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private String label;

    /**
     * Constructs a new {@code Label}
     *
     * @param label A valid label
     */
    public Label(String label) {
        requireNonNull(label);
        checkArgument(isValidLabel(label), MESSAGE_CONSTRAINTS);
        this.label = label;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidLabel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        assert label != null;
        this.label = label;
    }

    /**
     * Returns true if both description have the same identity and data fields.
     * This defines a stronger notion of equality between two labels.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Label)) {
            return false;
        }

        Label otherLabel = (Label) other;
        return otherLabel.getLabel().equals(getLabel());
    }

    @Override
    public String toString() {
        return "[" + label + "]";
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

}
