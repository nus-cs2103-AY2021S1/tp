package seedu.homerce.logic.parser.service;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_DURATION;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_PRICE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_TITLE;

import java.util.stream.Stream;

import seedu.homerce.logic.commands.service.AddServiceCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.Prefix;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;


/**
 * Parses input arguments and creates a new AddServiceCommand object.
 */
public class AddServiceCommandParser implements Parser<AddServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of AddServiceCommand
     * and returns an AddServiceCommand object.
     *
     * @param args is the user input.
     * @return an AddServiceCommand for execution.
     * @throws ParseException if the user input does not abide by the expected format.
     */
    @Override
    public AddServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SERVICE_TITLE, PREFIX_SERVICE_DURATION, PREFIX_SERVICE_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_SERVICE_TITLE, PREFIX_SERVICE_DURATION, PREFIX_SERVICE_PRICE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddServiceCommand.MESSAGE_USAGE));
        }
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_SERVICE_TITLE).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_SERVICE_DURATION).get());
        Amount price = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_SERVICE_PRICE).get());

        Service service = new Service(title, duration, price);

        return new AddServiceCommand(service);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
