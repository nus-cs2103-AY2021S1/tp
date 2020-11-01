package seedu.zookeep.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.zookeep.model.Model;
import seedu.zookeep.model.ZooKeepBook;

/**
 * Clears the ZooKeepBook.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ZooKeepBook has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setZooKeepBook(new ZooKeepBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
