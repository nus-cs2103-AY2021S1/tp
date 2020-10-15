package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        try {
            ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());
            return new ViewCommand(moduleName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}
