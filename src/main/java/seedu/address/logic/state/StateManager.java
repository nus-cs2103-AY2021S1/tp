package seedu.address.logic.state;

import java.util.Optional;

import seedu.address.commons.core.index.Index;

/**
 * Tracks the state of the program.
 */
public class StateManager {

    private static Optional<Index> state = Optional.empty();


    /**
     * Gets the state of the program.
     *
     * @return Index of case that the program is at,
     * or null if at main page.
     */
    public static Index getState() {
        return state.orElse(null);
    }

    /**
     * Sets the state of program to a case index.
     *
     * @param index Index of a case in the list.
     */
    public static void setState(Index index) {
        assert (index != null) : "index should not be null";
        state = Optional.of(index);
    }

    /**
     * Resets the state of program to an empty state.
     */
    public static void resetState() {
        state = Optional.empty();
    }

    /**
     * Checks if the state of the program is at an investigation case.
     *
     * @return true if the state is non empty.
     */
    public static boolean atCasePage() {
        return state.isPresent();
    }

    /**
     * Checks if the state of the program is at the main page.
     *
     * @return true if the state of the program is empty.
     */
    public static boolean atMainPage() {
        return state.isEmpty();
    }


}
