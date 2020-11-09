package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 *  Creates a description of a class.
 *
 *  Use to display commands on the Help Window.
 */
public class CommandDescription {
    private final SimpleStringProperty command = new SimpleStringProperty("");
    private final SimpleStringProperty description = new SimpleStringProperty("");

    CommandDescription(String command, String description) {
        this.command.set(command);
        this.description.set(description);
    }

    /**
     * Returns command name.
     * @return command name.
     */
    public String getCommand() {
        return command.get();
    }

    /**
     * Sets the command name.
     * @param command
     */
    public void setCommand(String command) {
        this.command.set(command);
    }

    /**
     * Returns command Description.
     * @return
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the command description.
     * @param description
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
}
