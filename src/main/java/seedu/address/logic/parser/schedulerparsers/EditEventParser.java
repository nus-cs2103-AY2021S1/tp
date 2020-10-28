package seedu.address.logic.parser.schedulerparsers;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.schedulercommands.EditEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

public class EditEventParser implements Parser<EditEventCommand> {
    @Override
    public EditEventCommand parse(String userInput) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME, PREFIX_DATE);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        return null;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
