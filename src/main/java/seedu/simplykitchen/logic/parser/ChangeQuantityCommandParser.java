package seedu.simplykitchen.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_AMOUNT;

import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.ChangeQuantityCommand;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ChangeQuantityCommand object
 */
public class ChangeQuantityCommandParser implements Parser<ChangeQuantityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeQuantityCommand
     * and returns a ChangeQuantityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeQuantityCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT);

        Index index;
        double amount;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeQuantityCommand.MESSAGE_USAGE),
                    pe);
        }

        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeQuantityCommand.MESSAGE_USAGE));
        }

        return new ChangeQuantityCommand(index, amount);
    }
}
