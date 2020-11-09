package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FORCE_DELETE_USAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ForceDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new ForceDeleteCommand object
 */
public class ForceDeleteCommandParser implements Parser<ForceDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ForceDeleteCommand
     * and returns a ForceDeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ForceDeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_FORCE_DELETE_USAGE));
        }
        ModuleCode code = ParserUtil.parseModuleCode(args);
        return new ForceDeleteCommand(code);
    }
}
