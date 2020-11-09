package seedu.address.logic.parser.schedulerparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.schedulercommands.AddEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddEventParser object.
 */
public class AddEventParser implements Parser<AddEventCommand> {
    @Override
    public AddEventCommand parse(String userInput) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME, PREFIX_DATE, PREFIX_TAG);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_DATE)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEventCommand.MESSAGE_USAGE));
        }
        EventName name = ParserUtil.parseEventName(argMultiMap.getValue(PREFIX_NAME).get());
        String dateTime = argMultiMap.getValue(PREFIX_DATE).get();
        EventTime time = ParserUtil.parseEventTime(dateTime);
        Set<Tag> tags = ParserUtil.parseTags(argMultiMap.getAllValues(PREFIX_TAG));
        Event event = new Event(name, time, tags);
        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
