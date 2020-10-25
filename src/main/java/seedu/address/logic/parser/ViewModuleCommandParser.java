package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewModuleCommand;
import seedu.address.logic.commands.ViewTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewModuleCommandParser implements Parser<ViewModuleCommand> {
    /**
     //     * Parses the given {@code String} of arguments in the context of the ViewModuleCommand
     //     * and returns a ViewModuleCommand object for execution.
     //     * @throws ParseException if the user input does not conform the expected format
     //     */
    public ViewModuleCommand parse(String args) throws ParseException {
        try {
            if (args.length() > 0) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
            return new ViewModuleCommand();
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
