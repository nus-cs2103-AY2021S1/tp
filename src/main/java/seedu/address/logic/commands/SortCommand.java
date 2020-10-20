package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;


/**
 * Sorts all animals in the zookeep book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all animals";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortAnimals();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
