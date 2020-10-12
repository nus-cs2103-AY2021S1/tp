package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Quantity;


public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns a RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), ive);
        }

        if (!areQuantityPresent(argMultimap, PREFIX_QUANTITY)) {
            throw new ParseException(RemoveCommand.MESSAGE_NO_QUANTITY);
        }

        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());

        return new RemoveCommand(index, quantity);
    }

    /**
     * Returns true if Prefix Quantity does not contains empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areQuantityPresent(ArgumentMultimap argumentMultimap, Prefix quantity) {
        return Stream.of(quantity).allMatch(prefix -> argumentMultimap.getValue(quantity).isPresent());
    }

}
