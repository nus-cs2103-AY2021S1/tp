package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACANCY;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJobCommand;
import seedu.address.logic.commands.EditJobCommand.EditJobDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditJobCommand object
 */
public class EditJobCommandParser implements Parser<EditJobCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditJobCommand
     * and returns an EditJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditJobCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOB_TITLE, PREFIX_COMPANY_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRIORITY, PREFIX_VACANCY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditJobCommand.MESSAGE_USAGE), pe);
        }

        EditJobDescriptor editJobDescriptor = new EditJobDescriptor();
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            editJobDescriptor.setJobTitle(ParserUtil.parseName(argMultimap.getValue(PREFIX_JOB_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editJobDescriptor.setCompanyName(ParserUtil.parseCompanyName(
                    argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editJobDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editJobDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editJobDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editJobDescriptor.setPriority(ParserUtil.parsePriorityString(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        if (argMultimap.getValue(PREFIX_VACANCY).isPresent()) {
            editJobDescriptor.setVacancy(ParserUtil.parseVacancy(argMultimap.getValue(PREFIX_VACANCY).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editJobDescriptor::setTags);

        if (!editJobDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditJobCommand.MESSAGE_NOT_EDITED);
        }

        return new EditJobCommand(index, editJobDescriptor);
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
