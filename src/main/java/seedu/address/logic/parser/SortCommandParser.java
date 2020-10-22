package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.AnimalComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String NAME_CATEGORY = "name";
    public static final String ID_CATEGORY = "id";
    public static final String FEEDTIME_CATEGORY = "feedtime";

    public static final String MESSAGE_INVALID_SORT_CATEGORY = "Please key in a proper sort category: "
            + "name, id or feedtime";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String category = args.trim().toLowerCase();
        if (category.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        AnimalComparator animalComparator;
        assert !category.equals("") : "Category input cannot be empty!";
        switch (category) {
        case NAME_CATEGORY:
            animalComparator = AnimalComparator.createAnimalNameComparator();
            break;
        case ID_CATEGORY:
            animalComparator = AnimalComparator.createAnimalIdComparator();
            break;
        case FEEDTIME_CATEGORY:
            animalComparator = AnimalComparator.createAnimalFeedTimeComparator();
            break;
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_SORT_CATEGORY, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(animalComparator);
    }
}
