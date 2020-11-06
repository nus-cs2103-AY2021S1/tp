package seedu.cc.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.entrylevel.EditCommand;
import seedu.cc.logic.commands.entrylevel.EditCommand.EditEntryDescriptor;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.util.ArgumentMultimap;
import seedu.cc.logic.parser.util.ArgumentTokenizer;
import seedu.cc.logic.parser.util.ParserUtil;
import seedu.cc.model.tag.Tag;



public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_TAG);

        Index index;

        // Parsing index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        // Check if there is category
        boolean isCategoryPrefixPresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CATEGORY);
        if (!isCategoryPrefixPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE));
        }

        // Check if category prefix is only used once
        boolean isNumberOfCategoryPrefixCorrect = ParserUtil.areNumberOfPrefixesOnlyOne(argMultimap, PREFIX_CATEGORY);

        // Check if optional prefixes are only used once or none
        boolean isNumberOfOtherPrefixCorrect =
                ParserUtil.areNumberOfPrefixesOneOrNone(argMultimap, PREFIX_DESCRIPTION, PREFIX_AMOUNT);

        if (!isNumberOfCategoryPrefixCorrect || !isNumberOfOtherPrefixCorrect) {
            throw new ParseException(String.format(MESSAGE_MULTIPLE_PREFIXES, EditCommand.PREFIXES));
        }

        // Parse category
        EditEntryDescriptor editEntryDescriptor = new EditEntryDescriptor();
        editEntryDescriptor.setCategory(ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));

        // Parse description if have
        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            editEntryDescriptor.setDescription(ParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }

        // Parse amount if have
        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_AMOUNT)) {
            editEntryDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }

        // Parse tags if have
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEntryDescriptor::setTags);

        if (!editEntryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEntryDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
