//@@author EkamSinghPandher
package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandHistory {
    private List<String> commandList = new ArrayList<>();
    private int index = 0;
    private int length = 0;
    private boolean isFirstUp = true;

    /**
     * Adds the command entered into the command history
     * @param command
     */
    public void addCommand(String command) {
        commandList.add(command);
        length = commandList.size();
        index = length - 1;
        isFirstUp = true;
    }

    /**
     * Finds the previous command in the command history.
     * @return Command text if there is any
     */
    public Optional<String> up() {
        if (length == 0) {
            return Optional.empty();
        } else if (index == 0) {
            return Optional.of(commandList.get(index));
        } else if (isFirstUp) {
            Optional<String> result = Optional.of(commandList.get(index));
            isFirstUp = false;
            return result;
        } else {
            index = index - 1;
            Optional<String> result = Optional.of(commandList.get(index));
            return result;
        }
    }

    /**
     * Finds the next command in the command history
     * @return Command text if there is any
     */
    public Optional<String> down() {
        if (length == 0) {
            return Optional.empty();
        } else if (index == length - 1) {
            return Optional.of(commandList.get(index));
        } else {
            index = index + 1;
            Optional<String> result = Optional.of(commandList.get(index));
            return result;
        }
    }
}
