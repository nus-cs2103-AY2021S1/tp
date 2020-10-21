package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.SortCommand.MESSAGE_INVALID_FIELD;
import static seedu.stock.logic.commands.SortCommand.MESSAGE_INVALID_ORDER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_TYPE;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.util.SortUtil;
import seedu.stock.commons.util.SortUtil.Order;
import seedu.stock.logic.commands.SortCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {
    private static final Logger logger = LogsCenter.getLogger(SortCommandParser.class);

    /**
     * Parses {@code args} into a sort command.
     *
     * @param args The user input to be parsed.
     * @return A new sort command.
     * @throws ParseException If a parsing error occurs.
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_ORDER, PREFIX_SORT_TYPE);

        if (!argMultimap.getValue(PREFIX_SORT_TYPE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_SORT_ORDER).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        boolean isReversed = false;
        try {
            Order order = Order.valueOf(argMultimap.getValue(PREFIX_SORT_ORDER).get().toUpperCase());
            switch (order) {
            case ASCENDING:
                isReversed = false;
                break;
            case DESCENDING:
                isReversed = true;
                break;
            default:
                throw new ParseException(MESSAGE_INVALID_ORDER);
            }
        } catch (IllegalArgumentException ex) {
            throw new ParseException(MESSAGE_INVALID_ORDER);
        }

        String field = argMultimap.getValue(PREFIX_SORT_TYPE).get().toLowerCase();
        for (String validField : SortUtil.FIELDS) {
            if (field.equals(validField)) {
                return new SortCommand(field, isReversed);
            }
        }

        logger.log(Level.WARNING, "Field not valid");
        throw new ParseException(MESSAGE_INVALID_FIELD);
    }
}
