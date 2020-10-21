package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.modulelistcommands.ViewModuleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewModuleParser implements Parser<ViewModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewModuleCommand parse(String args) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        try {
            ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());
            return new ViewModuleCommand(moduleName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
