package seedu.address.logic.state;

import java.util.Optional;

import seedu.address.commons.core.index.Index;

public class StateManager {

    private static Optional<Index> state = Optional.empty();


    public static Index getState() {
        return state.get();
    }

    public static void setState(Index index) {
        assert (index != null) : "index should not be null";
        state = Optional.of(index);
    }

    public static boolean atCasePage() {
        return state.isPresent();
    }

    public static boolean atMainPage() {
        return state.isEmpty();
    }


}
