package seedu.address.logic.parser.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISITED;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.attraction.EditAttractionCommand;
import seedu.address.logic.commands.attraction.EditAttractionCommand.EditAttractionDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditAttractionCommand object
 */
public class EditAttractionCommandParser implements Parser<EditAttractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAttractionCommand
     * and returns an EditAttractionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAttractionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_DESCRIPTION, PREFIX_LOCATION, PREFIX_OPENING_HOURS,
                        PREFIX_PRICE_RANGE, PREFIX_RATING, PREFIX_VISITED, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAttractionCommand.MESSAGE_USAGE), pe);
        }

        EditAttractionDescriptor editAttractionDescriptor = new EditAttractionDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editAttractionDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editAttractionDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editAttractionDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editAttractionDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editAttractionDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editAttractionDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_OPENING_HOURS).isPresent()) {
            editAttractionDescriptor.setOpeningHours(ParserUtil.parseOpeningHours(
                    argMultimap.getValue(PREFIX_OPENING_HOURS).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            editAttractionDescriptor.setPriceRange(ParserUtil.parsePriceRange(
                    argMultimap.getValue(PREFIX_PRICE_RANGE).get()));
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editAttractionDescriptor.setRating(ParserUtil.parseRating(
                    argMultimap.getValue(PREFIX_RATING).get()));
        }
        if (argMultimap.getValue(PREFIX_VISITED).isPresent()) {
            editAttractionDescriptor.setVisited(ParserUtil.parseVisited(
                    argMultimap.getValue(PREFIX_VISITED).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editAttractionDescriptor::setTags);

        if (!editAttractionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAttractionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAttractionCommand(index, editAttractionDescriptor);
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
