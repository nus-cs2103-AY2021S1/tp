package seedu.resireg.logic;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A class that stores the history of previously executed commands
 */

/*
 * Code for CommandHistory is adapted from addressbook-level4, which can be found at
 * https://github.com/se-edu/addressbook-level4.
 */
public class CommandHistory {
    private final ObservableList<String> history = FXCollections.observableArrayList();
    private final ObservableList<String> unmodifiableHistory =
            FXCollections.unmodifiableObservableList(history);
    private Integer counter = 0;

    public CommandHistory() {}

    /**
     * Constructs a commandHistory object
     */
    public CommandHistory(CommandHistory commandHistory) {
        history.addAll(commandHistory.history);
        counter = commandHistory.counter;
    }

    /**
     * Appends {@code input} and its corresponding position
     * to the list of inputs previously entered.
     */
    public void add(String input) {
        requireNonNull(input);
        counter++;
        history.add(input);
    }

    /**
     * Return an immutable {@code history} object.
     */
    public ObservableList<String> getHistory() {
        return unmodifiableHistory;
    }

    /**
     * Return the number of previously entered commands.
     */
    public Integer getCounter() {
        return counter;
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
        return history.equals(other.history)
                && counter.equals(other.counter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(history, counter);
    }
}
