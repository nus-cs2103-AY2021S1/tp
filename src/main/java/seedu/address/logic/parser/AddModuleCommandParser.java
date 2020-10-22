package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddModuleCommand
     * and returns a AddModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleCommand parse(String args) throws ParseException {
        try {
            Module module = ParserUtil.parseModule(args);
            return new AddModuleCommand(module);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
