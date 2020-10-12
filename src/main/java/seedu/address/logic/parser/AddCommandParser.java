package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.Food;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Dummy parse method
     */
    @Override
    public AddCommand parse(String userInput) throws ParseException {
        return null;
    }
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args, ObservableList<Food> filteredFoodList) throws ParseException {
        String trimArgs = args.trim();
        String[] argList = trimArgs.split(" ");
        if (argList.length == 0 || argList.length > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argList[0]);
        if (index.getZeroBased() >= filteredFoodList.size()) {
            throw new ParseException("INDEX SELECTED IS TOO LARGE.");
        }
        Food food = filteredFoodList.get(index.getZeroBased());
        if (argList.length == 1) {
            return new AddCommand(food);
        }

        int quantity = ParserUtil.parseQuantity(argList[1]);
        return new AddCommand(food, quantity);

    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


