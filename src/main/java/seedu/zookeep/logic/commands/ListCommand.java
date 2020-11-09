package seedu.zookeep.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.model.Model.PREDICATE_SHOW_ALL_ANIMALS;

import seedu.zookeep.model.Model;

/**
 * Lists all animals in the ZooKeepBook to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all animals";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
