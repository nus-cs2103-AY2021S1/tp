package seedu.simplykitchen.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX;
import static seedu.simplykitchen.commons.util.StringUtil.isIntegerOverflow;
import static seedu.simplykitchen.commons.util.StringUtil.isNonPositiveUnsignedDouble;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;
import seedu.simplykitchen.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_PRIORITY,
                        PREFIX_EXPIRY_DATE, PREFIX_QUANTITY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            logger.info(generateParseExceptionMessage(args));
            throw new ParseException(generateParseExceptionMessage(argMultimap.getPreamble()), pe);
        }

        EditFoodDescriptor editFoodDescriptor = new EditCommand.EditFoodDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editFoodDescriptor.setDescription(ParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editFoodDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }

        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            editFoodDescriptor.setExpiryDate(ParserUtil.parseExpiryDate(
                    argMultimap.getValue(PREFIX_EXPIRY_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            editFoodDescriptor.setQuantity(ParserUtil.parseQuantity(
                    argMultimap.getValue(PREFIX_QUANTITY).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editFoodDescriptor::setTags);

        if (!editFoodDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editFoodDescriptor);
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

    /**
     * Generate the error message for the invalid index number.
     */
    private String generateParseExceptionMessage(String args) {
        if (isNonPositiveUnsignedDouble(args.trim()) || isIntegerOverflow(args.trim())) {
            return MESSAGE_INVALID_FOOD_DISPLAYED_INDEX;
        } else {
            return String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        }
    }

}
