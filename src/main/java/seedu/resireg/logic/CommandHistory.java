package seedu.resireg.logic;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A class to store the history of previously executed commands.
 */
public class CommandHistory {
    private final ObservableList<String> history = FXCollections.observableArrayList();
    private final ObservableList<String> unmodifiableHistory =
            FXCollections.unmodifiableObservableList(history);

    public CommandHistory() {}

    public CommandHistory(CommandHistory commandHistory) {
        history.addAll(commandHistory.history);
    }

    /**
     * Appends {@code input} to the list of input entered
     */
    public void add(String input) {
        requireNonNull(input);
        history.add(input);
    }

    /**
     * Return unmodifiable view of {@code history}
     */
    public ObservableList<String> getHistory() {
        return unmodifiableHistory;
    }

    @Override
    public boolean equals(Object o) {
        // short circuit if same object
        if (o == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(o instanceof CommandHistory)) {
            return false;
        }

        // state check
        CommandHistory other = (CommandHistory) o;
        return history.equals(other.history);
    }

    @Override
    public int hashCode() {
        return history.hashCode();
    }
}
