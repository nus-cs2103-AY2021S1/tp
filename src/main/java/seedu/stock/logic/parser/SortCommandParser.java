package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.SortCommand.MESSAGE_INVALID_FIELD;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_TYPE;

import seedu.stock.logic.commands.SortCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser {

    public static final String[] FIELDS = new String[]{"name", "quantity", "source", "location", "serialnumber"};

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_TYPE);

        if (!argMultimap.getValue(PREFIX_SORT_TYPE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String field = argMultimap.getValue(PREFIX_SORT_TYPE).get().toLowerCase();
        for (String validField : FIELDS) {
            if (field.equals(validField)) {
                return new SortCommand(field);
            }
        }
        
        throw new ParseException(MESSAGE_INVALID_FIELD);
    }
}
