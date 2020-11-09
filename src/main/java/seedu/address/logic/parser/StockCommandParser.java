package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.StockCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;

/**
 * Parses input arguments and creates a new StockCommand object
 */
public class StockCommandParser implements Parser<StockCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StockingCommand
     * and returns a StockingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public StockCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                userInput,
                CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_ISBN
        );
        if (!isPrefixPresent(argumentMultimap, CliSyntax.PREFIX_NAME)
                && !isPrefixPresent(argumentMultimap, CliSyntax.PREFIX_ISBN)
                && !userInput.strip().equals("")) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    StockCommand.MESSAGE_USAGE));
        }

        List<String> names = null;
        List<String> numbers = null;

        if (argumentMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            if (!Name.isValidName(argumentMultimap.getValue(CliSyntax.PREFIX_NAME).get().strip())) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            names = Arrays.asList(argumentMultimap.getValue(CliSyntax.PREFIX_NAME).get().split("\\s+"));
        }
        if (argumentMultimap.getValue(CliSyntax.PREFIX_ISBN).isPresent()) {
            numbers = new ArrayList<>();
            if (!Isbn.isValidIsbn(argumentMultimap.getValue(CliSyntax.PREFIX_ISBN).get().strip())) {
                throw new ParseException(Isbn.MESSAGE_CONSTRAINTS);
            }
            numbers.add(argumentMultimap.getValue(CliSyntax.PREFIX_ISBN).get().strip());
        }

        return new StockCommand(names, numbers);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
