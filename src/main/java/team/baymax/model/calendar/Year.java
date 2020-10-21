package team.baymax.model.calendar;

import static java.util.Objects.requireNonNull;

public class Year {

    private final int value;

    public Year(int value) {
        requireNonNull(value);

        if (isValidYear(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns true if a given number is a valid year.
     */
    public static boolean isValidYear(int n) {
        return n > 2000;
    }

    public String getText() {
        return "" + value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && value == ((Year) other).value); // state check
    }
}
