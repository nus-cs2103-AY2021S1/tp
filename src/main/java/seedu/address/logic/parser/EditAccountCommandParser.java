package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.EditAccountCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;
import seedu.address.model.account.Name;


/**
 * Parses input arguments and creates a new EditAccountCommand object
 */
public class EditAccountCommandParser implements Parser<EditAccountCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAccountCommand
     * and returns an EditAccountCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAccountCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAccountCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new EditAccountCommand(name);
    }

}
