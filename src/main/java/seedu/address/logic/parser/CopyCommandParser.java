//@@author jerrylchong
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.FullNameMatchesKeywordPredicate;
import seedu.address.model.person.PersonHasTagsAndNamePredicate;
import seedu.address.model.person.PersonHasTagsPredicate;
import seedu.address.model.tag.Tag;

public class CopyCommandParser implements Parser<CopyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_MODULE);

        boolean isEmail;
        String preamble = argMultimap.getPreamble().trim().toLowerCase();
        if (preamble.equals("email")) {
            isEmail = true;
        } else if (preamble.equals("phone")) {
            isEmail = false;
        } else {
            // invalid preamble
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG, PREFIX_MODULE)) {
            Set<String> nameSet = ParserUtil.parseAllNames(argMultimap.getAllValues(PREFIX_NAME));
            Set<Tag> tagSet = parseTagsForFind(argMultimap.getAllValues(PREFIX_TAG)).orElse(new HashSet<>());
            List<String> moduleNames = argMultimap.getAllValues(PREFIX_MODULE);
            // check if any of the collections are empty (no text after prefixes)
            if ((nameSet.size() == 1 && nameSet.contains(""))
                    || tagSet.isEmpty()
                    || (moduleNames.size() == 1 && moduleNames.contains(""))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
            }
            Set<ModuleName> moduleNameSet = ParserUtil.parseAllModules(moduleNames);
            return new CopyCommand(
                    new PersonHasTagsAndNamePredicate(new ArrayList<>(nameSet), new ArrayList<>(tagSet)),
                    isEmail, new ArrayList<>(moduleNameSet));
        } else if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)) {
            Set<String> nameSet = ParserUtil.parseAllNames(argMultimap.getAllValues(PREFIX_NAME));
            Set<Tag> tagSet = parseTagsForFind(argMultimap.getAllValues(PREFIX_TAG)).orElse(new HashSet<>());
            // check if any of the collections are empty (no text after prefixes)
            if ((nameSet.size() == 1 && nameSet.contains("")) || tagSet.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
            }
            return new CopyCommand(
                    new PersonHasTagsAndNamePredicate(new ArrayList<>(nameSet), new ArrayList<>(tagSet)),
                    isEmail, new ArrayList<ModuleName>());
        } else if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE)) {
            Set<String> nameSet = ParserUtil.parseAllNames(argMultimap.getAllValues(PREFIX_NAME));
            List<String> moduleNames = argMultimap.getAllValues(PREFIX_MODULE);
            // check if any of the collections are empty (no text after prefixes)
            if ((nameSet.size() == 1 && nameSet.contains(""))
                    || (moduleNames.size() == 1 && moduleNames.contains(""))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
            }
            Set<ModuleName> moduleNameSet = ParserUtil.parseAllModules(moduleNames);
            return new CopyCommand(
                    new FullNameMatchesKeywordPredicate(new ArrayList<>(nameSet)),
                    isEmail, new ArrayList<>(moduleNameSet));
        } else if (arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_TAG)) {
            Set<Tag> tagSet = parseTagsForFind(argMultimap.getAllValues(PREFIX_TAG)).orElse(new HashSet<>());
            List<String> moduleNames = argMultimap.getAllValues(PREFIX_MODULE);
            // check if any of the collections are empty (no text after prefixes)
            if (tagSet.isEmpty() || (moduleNames.size() == 1 && moduleNames.contains(""))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
            }
            Set<ModuleName> moduleNameSet = ParserUtil.parseAllModules(moduleNames);
            return new CopyCommand(
                    new PersonHasTagsPredicate(new ArrayList<>(tagSet)),
                    isEmail, new ArrayList<>(moduleNameSet));
        } else if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            Set<String> nameSet = ParserUtil.parseAllNames(argMultimap.getAllValues(PREFIX_NAME));
            // check if any of the collections are empty (no text after prefixes)
            if (nameSet.size() == 1 && nameSet.contains("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
            }
            return new CopyCommand(
                    new FullNameMatchesKeywordPredicate(new ArrayList<>(nameSet)),
                    isEmail, new ArrayList<ModuleName>());
        } else if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            Set<Tag> tagSet = parseTagsForFind(argMultimap.getAllValues(PREFIX_TAG))
                    .orElseThrow(() -> new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE)));
            return new CopyCommand(
                    new PersonHasTagsPredicate(new ArrayList<>(tagSet)),
                    isEmail, new ArrayList<ModuleName>());
        } else if (arePrefixesPresent(argMultimap, PREFIX_MODULE)) {
            List<String> moduleNames = argMultimap.getAllValues(PREFIX_MODULE);
            // check if any of the collections are empty (no text after prefixes)
            if (moduleNames.size() == 1 && moduleNames.contains("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
            }
            Set<ModuleName> moduleNameSet = ParserUtil.parseAllModules(moduleNames);
            return new CopyCommand(
                    new PersonHasTagsPredicate(new ArrayList<>()),
                    isEmail, new ArrayList<>(moduleNameSet));
        } else {
            // no valid prefixes provided
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForFind(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty() || (tags.size() == 1 && tags.contains(""))) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
