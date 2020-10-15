package seedu.address.history;

import java.util.Optional;

public interface History {

    /**
     * Adds input to history.
     * @param command input for adding to history.
     */
    void addToHistory(String command);

    /**
     * Provides previous command, if any.
     * @return an Optional of the previous command.
     */
    Optional<String> previousCommand();

    /**
     * Provides next command, if any.
     * @return an Optional of the next command.
     */
    Optional<String> nextCommand();

    /**
     * Provides current command, if any.
     * @return an Optional of the current command.
     */
    Optional<String> currentCommand();
}
