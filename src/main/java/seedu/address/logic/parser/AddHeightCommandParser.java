package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddHeightCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.body.Height;

public class AddHeightCommandParser implements Parser<AddHeightCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddHeightCommand
     * and returns an AddHeightCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddHeightCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_HEIGHT);

        if (!arePrefixesPresent(argMultimap, PREFIX_HEIGHT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHeightCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_HEIGHT).size() != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHeightCommand.MESSAGE_USAGE));
        }

        Height height = ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get());

        return new AddHeightCommand(height);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
