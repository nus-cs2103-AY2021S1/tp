package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
        String[] keywords = new String[0];
        //cannot contain more than one prefix
        //must start with that prefix
        //can search multiple for each field
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_MODULE_CODE);

        if (moreThanOnePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_DEADLINE)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        try {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
            }
            if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
                keywords = argMultimap.getValue(PREFIX_MODULE_CODE).get().split("\\s+");
                for (String keyword : keywords) {
                    System.out.println(keyword);
                    ModuleCode moduleCode = ParserUtil.parseModuleCode(keyword);
                }
                return new FindCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList(keywords)));
            }
            if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
                //not working yet
                keywords = argMultimap.getValue(PREFIX_DEADLINE).get()
                        .split("(?<!\\G\\S+)\\s");
                for (String keyword : keywords) {
                    Deadline deadline = ParserUtil.parseDeadline(keyword);
                }
                return new FindCommand(new DeadlineContainsKeywordsPredicate(Arrays.asList(keywords)));
            }

        } catch (ParseException pe) {
            throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        //have to edit this
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

    private static boolean moreThanOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long countPrefixesPresent = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count();
        return countPrefixesPresent > 1;
    }
}
