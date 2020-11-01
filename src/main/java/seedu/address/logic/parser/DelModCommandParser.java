package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.stream.Stream;

import seedu.address.logic.commands.DelModCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DelModCommandParser implements Parser<DelModCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DelModCommand
     * and returns a DelModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DelModCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DelModCommand.MESSAGE_USAGE));
        }

        checkDuplicatePrefix(argMultimap, PREFIX_MODULE_CODE);

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());

        try {
            return new DelModCommand(moduleCode);
        } catch (IllegalArgumentException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DelModCommand.MESSAGE_USAGE), e);
        }
    }

    /**
     * Throws a {@code ParseException} if there is a duplicate prefix.
     */
    private void checkDuplicatePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) throws ParseException {
        for (Prefix p : prefixes) {
            if (argumentMultimap.getAllValues(p).size() > 1) {
                throw new ParseException(String.format(MESSAGE_DUPLICATE_PREFIX, p));
            }
        }
    }

}
