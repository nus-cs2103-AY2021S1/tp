package seedu.zookeep.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.zookeep.model.Model;
import seedu.zookeep.model.animal.AnimalComparator;


/**
 * Sorts all animals in the ZooKeepBook based on the user specified parameter.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the animals according to their name, ID, earliest feed time or number of medical conditions. \n"
            + "Parameters: case-insensitive CATEGORY (name, id, feedtime, medical) \n"
            + "Example: " + COMMAND_WORD + " name";
    public static final String MESSAGE_SUCCESS = "Sorted all animals by ";

    private final AnimalComparator animalComparator;

    public SortCommand(AnimalComparator animalComparator) {
        this.animalComparator = animalComparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String category = animalComparator.getCategory();
        model.sortAnimals(animalComparator);
        return new CommandResult(MESSAGE_SUCCESS + category);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && animalComparator.equals(((SortCommand) other).animalComparator)); // state check
    }
}
