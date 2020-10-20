package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.animal.AnimalComparator;


/**
 * Sorts all animals in the zookeep book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the animals according to their name, ID or earliest feedtime. \n"
            + "Parameters: keyword for sorting category (name, id, feedtime) \n"
            + "Example: " + COMMAND_WORD + " name";
    public static final String MESSAGE_SUCCESS = "Sorted all animals by ";

    public static final String NAME_KEYWORD = "name";
    public static final String ID_KEYWORD = "id";
    public static final String FEEDTIME_KEYWORD = "feedtime";

    private final String keyword;

    public SortCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert keyword != "" : "Keyword input cannot be empty!";

        switch (keyword) {
        case NAME_KEYWORD:
            model.sortAnimals(AnimalComparator.ANIMAL_NAME_COMPARATOR);
            break;
        case ID_KEYWORD:
            model.sortAnimals(AnimalComparator.ANIMAL_ID_COMPARATOR);
            break;
        case FEEDTIME_KEYWORD:
            model.sortAnimals(AnimalComparator.ANIMAL_FEEDTIME_COMPARATOR);
            break;
        default:
            throw new CommandException(Messages.MESSAGE_INVALID_SORT_KEYWORD);
        }
        return new CommandResult(MESSAGE_SUCCESS + keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && keyword.equals(((SortCommand) other).keyword)); // state check
    }
}
