package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_EDIT_NAME, PREFIX_NAME, PREFIX_ZOOM_LINK);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        String moduleName;
        moduleName = argMultimap.getValue(PREFIX_EDIT_NAME).get();

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();

        /*if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editModuleDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editModuleDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editModuleDescriptor::setTags);

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        */
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editModuleDescriptor.setModuleName(ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ZOOM_LINK).isPresent()) {
            editModuleDescriptor.setZoomLink(ParserUtil.parseZoomLink(argMultimap.getValue(PREFIX_ZOOM_LINK).get()));
        }

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(moduleName, editModuleDescriptor);
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
