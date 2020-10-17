package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.*;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = new String[0];
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_MODULE_CODE);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] names = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            nameKeywords = names;
            if (names.length == 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            Deadline deadlineKeyword;
            try {
                deadlineKeyword = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            return new FindCommand(new DeadlineContainsKeywordsPredicate(deadlineKeyword));
        }

        if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            ModuleCode moduleCodeKeyword;
            try {
                moduleCodeKeyword = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new FindCommand(new ModuleCodeContainsKeywordsPredicate(moduleCodeKeyword));
        }
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
