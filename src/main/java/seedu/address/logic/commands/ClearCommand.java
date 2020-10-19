package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.ZooKeepBook;

/**
 * Clears the zookeep book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Zookeep book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setZooKeepBook(new ZooKeepBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
