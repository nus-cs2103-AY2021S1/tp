package seedu.address.logic.parser.events;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.SYMBOL_WILDCARD;

import java.util.stream.Stream;

import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.logic.commands.events.AddEventCommand.AddEventDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Description;
import seedu.address.model.event.Time;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {

        assert args != null;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATETIME, PREFIX_ADD_PERSON);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_FAILURE));
        }

        AddEventDescriptor addEventDescriptor = new AddEventDescriptor();

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        addEventDescriptor.setDescription(description);

        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_DATETIME).get());
        addEventDescriptor.setTime(time);

        if (argMultimap.getValue(PREFIX_ADD_PERSON).isPresent()) {
            if (argMultimap.getValue(PREFIX_ADD_PERSON).get().equals(SYMBOL_WILDCARD)) {
                addEventDescriptor.setWildCardAdd();
            } else {
                addEventDescriptor.setPersonsToAdd(
                        ParserUtil.parseIndexes(argMultimap.getValue(PREFIX_ADD_PERSON).get()));
            }
        }

        return new AddEventCommand(addEventDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
