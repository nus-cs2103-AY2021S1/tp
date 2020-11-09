package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL_LINK;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditPersonCommand object
 */
public class EditPersonCommandParser implements Parser<EditPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_DATE_OF_APPLICATION, PREFIX_BLACKLIST, PREFIX_EXPERIENCE,
                        PREFIX_ADDRESS, PREFIX_URL_LINK, PREFIX_SALARY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditPersonCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_EXPERIENCE).isPresent()) {
            editPersonDescriptor.setExperience(ParserUtil
                    .parseExperience(argMultimap.getValue(PREFIX_EXPERIENCE).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_OF_APPLICATION).isPresent()) {
            editPersonDescriptor.setDateOfApplication(ParserUtil
                    .parseDate(argMultimap.getValue(PREFIX_DATE_OF_APPLICATION).get()));
        }
        if (argMultimap.getValue(PREFIX_BLACKLIST).isPresent()) {
            editPersonDescriptor.setBlackListStatus(ParserUtil
                    .parseBlacklistStatus(argMultimap.getValue(PREFIX_BLACKLIST).get()));
        }

        final boolean isAddressPrefixPresent = argMultimap.getValue(PREFIX_ADDRESS).isPresent();
        if (isAddressPrefixPresent && argMultimap.getValue(PREFIX_ADDRESS).get().equals("")) {
            // reset address to nothing
            editPersonDescriptor.setAddressOptional(Optional.empty());
        } else if (isAddressPrefixPresent && !argMultimap.getValue(PREFIX_ADDRESS).get().equals("")) {
            editPersonDescriptor.setAddressOptional(Optional.of(ParserUtil
                    .parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get())));
        }

        final boolean isUrlLinkPrefixPresent = argMultimap.getValue(PREFIX_URL_LINK).isPresent();
        if (isUrlLinkPrefixPresent && argMultimap.getValue(PREFIX_URL_LINK).get().equals("")) {
            // reset url link to nothing
            editPersonDescriptor.setUrlLinkOptional(Optional.empty());
        } else if (isUrlLinkPrefixPresent && !argMultimap.getValue(PREFIX_URL_LINK).get().equals("")) {
            editPersonDescriptor.setUrlLinkOptional(Optional.of(ParserUtil
                        .parseUrlLink(argMultimap.getValue(PREFIX_URL_LINK).get())));
        }

        final boolean isSalaryPrefixPresent = argMultimap.getValue(PREFIX_SALARY).isPresent();
        if (isSalaryPrefixPresent && argMultimap.getValue(PREFIX_SALARY).get().equals("")) {
            // reset salary to nothing
            editPersonDescriptor.setSalaryOptional(Optional.empty());
        } else if (isSalaryPrefixPresent && !argMultimap.getValue(PREFIX_SALARY).get().equals("")) {
            editPersonDescriptor.setSalaryOptional(Optional.of(ParserUtil
                    .parseSalary(argMultimap.getValue(PREFIX_SALARY).get())));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPersonCommand(index, editPersonDescriptor);
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
