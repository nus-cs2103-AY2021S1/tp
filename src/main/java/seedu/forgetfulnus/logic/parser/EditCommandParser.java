package seedu.forgetfulnus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_MULTIPLE_PREFIX;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_DIFFICULTY_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GENDER_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.EditCommand;
import seedu.forgetfulnus.logic.commands.EditCommand.EditFlashCardDescriptor;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private static Logger logger = Logger.getLogger("EditCommandParserLogger");

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE, PREFIX_DIFFICULTY_TAG, PREFIX_GENDER_TAG, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        try {
            checkMultiplePrefix(argMultimap, PREFIX_GERMAN_PHRASE, PREFIX_ENGLISH_PHRASE, PREFIX_DIFFICULTY_TAG,
                    PREFIX_GENDER_TAG);
        } catch (ParseException pe) {
            logger.log(Level.INFO, "Multiple prefix detected.");
            throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
        }

        EditFlashCardDescriptor editFlashCardDescriptor = new EditFlashCardDescriptor();
        if (argMultimap.getValue(PREFIX_GERMAN_PHRASE).isPresent()) {
            editFlashCardDescriptor
                    .setGermanPhrase(ParserUtil.parseGermanPhrase(argMultimap.getValue(PREFIX_GERMAN_PHRASE).get()));
        }
        if (argMultimap.getValue(PREFIX_ENGLISH_PHRASE).isPresent()) {
            editFlashCardDescriptor
                    .setEnglishPhrase(ParserUtil.parseEnglishPhrase(argMultimap.getValue(PREFIX_ENGLISH_PHRASE).get()));
        }
        if (argMultimap.getValue(PREFIX_DIFFICULTY_TAG).isPresent()) {
            editFlashCardDescriptor
                    .setDifficultyTag((DifficultyTag) ParserUtil.parsePredefinedTag(PREFIX_DIFFICULTY_TAG,
                            argMultimap.getValue(PREFIX_DIFFICULTY_TAG).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER_TAG).isPresent()) {
            editFlashCardDescriptor.setGenderTag((GenderTag) ParserUtil.parsePredefinedTag(PREFIX_GENDER_TAG,
                            argMultimap.getValue(PREFIX_GENDER_TAG).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editFlashCardDescriptor::setTags);
        editFlashCardDescriptor.setOrder(null);

        if (!editFlashCardDescriptor.isAnyFieldEdited()) {
            logger.log(Level.INFO, "Flashcard not edited.");
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editFlashCardDescriptor);
    }

    /**
     * Checks the given {@code prefixArray} of prefixes if there are multiple values associated to that prefix
     * @throws ParseException if the user input does not conform the expected format
     */
    private void checkMultiplePrefix(ArgumentMultimap argMultimap, Prefix... prefixes) throws ParseException {
        for (Prefix prefix: prefixes) {
            if (argMultimap.isMultipleValue(prefix)) {
                throw new ParseException(MESSAGE_INVALID_MULTIPLE_PREFIX);
            }
        }
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
