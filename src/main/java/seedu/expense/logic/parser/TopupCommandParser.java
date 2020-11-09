package seedu.expense.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.logic.commands.TopupCommand;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.tag.Tag;

/**
 * Parses input arguments and creates a new TopupCommand object
 */
public class TopupCommandParser implements Parser<TopupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TopupCommand}
     * and returns a {@code TopupCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TopupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TopupCommand.MESSAGE_USAGE));
        }

        Amount amount;
        try {
            amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TopupCommand.MESSAGE_USAGE), ive);
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()
                && !argMultimap.getValue(PREFIX_TAG).get().isBlank()) {
            Tag category = new Tag(argMultimap.getValue(PREFIX_TAG).get());
            return new TopupCommand(amount, category);
        }

        return new TopupCommand(amount);
    }

}
