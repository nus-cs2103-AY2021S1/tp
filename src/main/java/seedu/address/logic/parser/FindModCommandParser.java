package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INSTRUCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindModCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.model.module.ModuleNameContainsKeywordsPredicate;

public class FindModCommandParser implements Parser<FindModCommand> {

    /**
     * Returns true if one all more of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    private static boolean areAnyDuplicatePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getAllValues(prefix).size() > 1);
    }
    /**
     * Parses the given {@code String} of arguments in the context of the FindModCommand
     * and returns a FindModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindModCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_MODULE_NAME, PREFIX_MODULE_INSTRUCTOR);
        ModuleCodeContainsKeywordsPredicate codePredicate = null;
        ModuleNameContainsKeywordsPredicate namePredicate = null;
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = null;


        if (!areAnyPrefixesPresent(argMultimap, PREFIX_MODULE_CODE, PREFIX_MODULE_NAME, PREFIX_MODULE_INSTRUCTOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModCommand.MESSAGE_USAGE));
        }

        if (areAnyDuplicatePrefixesPresent(argMultimap,
                PREFIX_MODULE_CODE, PREFIX_MODULE_NAME, PREFIX_MODULE_INSTRUCTOR)) {
            // We might have to create a new Duplicate Prefix Exception class
            throw new ParseException("Duplicate prefix! Might have to implement a DuplicatePrefixException class!");
        }

        if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            String keyword = ParserUtil.parseString(argMultimap.getValue(PREFIX_MODULE_CODE).get());
            try {
                // Checks if module parameter is a single word
                checkArgument(keyword.split("\\s+").length == 1,
                        "Module code parameter should be a single word");
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage());
            }
            codePredicate = new ModuleCodeContainsKeywordsPredicate(keyword);
        }

        if (argMultimap.getValue(PREFIX_MODULE_NAME).isPresent()) {
            String keywords = ParserUtil.parseString(argMultimap.getValue(PREFIX_MODULE_NAME).get());
            String[] splitKeywords = keywords.split("\\s+");
            namePredicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList(splitKeywords));
        }

        if (argMultimap.getValue(PREFIX_MODULE_INSTRUCTOR).isPresent()) {
            String keywords = ParserUtil.parseString(argMultimap.getValue(PREFIX_MODULE_INSTRUCTOR).get());
            String[] splitKeywords = keywords.split("\\s+");
            instructorPredicate = new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList(splitKeywords));
        }
        // All predicates should not be null.
        assert(!(codePredicate == null && namePredicate == null && instructorPredicate == null));

        return new FindModCommand(codePredicate, namePredicate, instructorPredicate);
    }
}
