package seedu.stock.model.stock;

import seedu.stock.commons.core.index.Index;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;

public class NoteIndex {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid note index entered!\n"
                    + "Note Index should only contain non-negative integers"
                    + ", from 0 up to the largest note index for the stock.";

    /*
     * Note index should be a non-negative integer.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String oneBasedNoteIndex;

    /**
     * Constructs a {@code NoteIndex}.
     *
     * @param noteIndex A valid note index.
     */
    public NoteIndex(String noteIndex) {
        requireNonNull(noteIndex);
        checkArgument(isValidNoteIndex(noteIndex), MESSAGE_CONSTRAINTS);
        this.oneBasedNoteIndex = noteIndex;
    }

    /**
     * Returns true if a given string is a non-negative number.
     */
    public static boolean isValidNoteIndex(String index) {
        requireNonNull(index);
        try {
            //protective layer against huge string input.
            Integer.parseInt(index);
            return index.matches(VALIDATION_REGEX);

        } catch (Exception e) {
            return false;
        }
    }

    public int getZeroBased() {
        return getOneBased() - 1;
    }

    public int getOneBased() {
        return Integer.parseInt(this.oneBasedNoteIndex);
    }

    /**
     * Creates a new {@code NoteIndex} using a zero-based index.
     */
    public static NoteIndex fromZeroBased(String zeroBasedIndex) {
        return new NoteIndex(zeroBasedIndex);
    }

    /**
     * Creates a new {@code NoteIndex} using a one-based index.
     */
    public static NoteIndex fromOneBased(String oneBasedIndex) {
        NoteIndex oneBasedNoteIndex = new NoteIndex(oneBasedIndex);
        int oneBased = oneBasedNoteIndex.getZeroBased();
        return new NoteIndex(String.valueOf(oneBased - 1));
    }


    @Override
    public String toString() {
        return oneBasedNoteIndex;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteIndex // instanceof handles nulls
                && oneBasedNoteIndex.equals(((NoteIndex) other).oneBasedNoteIndex)); // state check
    }

    @Override
    public int hashCode() {
        return oneBasedNoteIndex.hashCode();
    }

}
