package seedu.homerce.model.undo;

import seedu.homerce.logic.commands.Command;
import seedu.homerce.model.Model;

/**
 * Contains information of the state of Homerce at a particular time instance.
 */
public class History {
    /** State of storage of Homerce */
    private final Model model;
    /** Command that caused the change to Homerce's state */
    private final Command command;

    /**
     * Constructor to create a history object encapsulating Homerce's state at a particular time instance.
     *
     * @param model stores the state of Homerce
     * @param command command executed that changed the state of Homerce
     */
    public History(Model model, Command command) {
        this.model = model;
        this.command = command;
    }

    public Model getModel() {
        return this.model;
    }

    public Command getCommand() {
        return this.command;
    }
}
