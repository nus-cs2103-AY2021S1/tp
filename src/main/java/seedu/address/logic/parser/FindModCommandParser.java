package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_CODE_KEYWORD;
import static seedu.address.logic.parser.ArgumentMultimap.checkDuplicatePrefix;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INSTRUCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindModCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.predicates.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleNameContainsKeywordsPredicate;

public class FindModCommandParser implements Parser<FindModCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindModCommand
     * and returns a FindModCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindModCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_MODULE_NAME, PREFIX_MODULE_INSTRUCTOR);

        List<Predicate<Module>> predicates = new ArrayList<>();

        if (!ArgumentMultimap.areAnyPrefixesPresent(argMultimap, PREFIX_MODULE_CODE, PREFIX_MODULE_NAME,
            PREFIX_MODULE_INSTRUCTOR)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModCommand.MESSAGE_USAGE));
        }

        checkDuplicatePrefix(argMultimap, PREFIX_MODULE_CODE, PREFIX_MODULE_NAME, PREFIX_MODULE_INSTRUCTOR);

        getKeyword(argMultimap, PREFIX_MODULE_CODE)
            .ifPresent(k -> predicates.add(new ModuleCodeContainsKeywordsPredicate(k)));
        getKeywords(argMultimap, PREFIX_MODULE_NAME)
            .ifPresent(k -> predicates.add(new ModuleNameContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_MODULE_INSTRUCTOR)
            .ifPresent(k -> predicates.add(new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList(k))));

        assert (!predicates.isEmpty());
        return new FindModCommand(predicates);
    }

    /**
     * Returns keywords stored as the value of {@code prefix}.
     */
    private Optional<String[]> getKeywords(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        if (argMultimap.getValue(prefix).isEmpty()) {
            return Optional.empty();
        }

        String[] keywords;
        keywords = ParserUtil.parseString(argMultimap.getValue(prefix).get()).split("\\s+");
        return Optional.of(keywords);
    }

    /**
     * Returns the keyword stored as the value of {@code prefix}.
     */
    private Optional<String> getKeyword(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        if (argMultimap.getValue(prefix).isEmpty()) {
            return Optional.empty();
        }

        String[] keywords = ParserUtil.parseString(argMultimap.getValue(prefix).get()).split("\\s+");

        if (keywords.length != 1) {
            throw new ParseException(MESSAGE_INVALID_MODULE_CODE_KEYWORD);
        }

        return Optional.of(keywords[0]);
    }
}
