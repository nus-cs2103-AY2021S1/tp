package seedu.resireg.logic;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Code for CommandHistory is adapted from addressbook-level4, which can be found at
 * https://github.com/se-edu/addressbook-level4.
 */
/**
 * A class that stores the history of previously executed commands
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
     * Appends a non-empty {@code input} and its corresponding position
     * to the list of inputs previously entered.
     */
    public void add(String input) {
        requireNonNull(input);
        if (!input.equals("")) {
            counter++;
            history.add(input);
        }
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

    /**
     * Returns the string to display to user as the
     * result of executing a history command. Each previously entered command
     * will be formatted as "n \t commandWord" where n indicates the command's
     * position in the execution sequence, and separated by "\n".
     */
    public String getCommandResultString() {

        if (counter.equals(0)) {
            return "";
        }

        List<String> commands = new ArrayList<>(unmodifiableHistory);

        for (int i = 1; i <= counter; i++) {
            String labelled = i + "\t" + commands.get(i - 1);
            commands.set(i - 1, labelled);
        }

        return String.join("\n", commands);
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

    // Used only for testing equals() works with different counters.
    void setCounter(Integer newCounter) {
        counter = newCounter;
    }
}
