package seedu.address.logic.parser.contactlistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new EditContactCommand object.
 */
public class EditContactParser implements Parser<EditContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditContactCommand
     * and returns an EditContactCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public EditContactCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME,
                PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_TAG);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditContactCommand.MESSAGE_USAGE), pe);
        }

        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editContactDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editContactDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            parseTelegramForEdit(argMultimap.getValue(PREFIX_TELEGRAM).get())
                    .ifPresentOrElse(telegram -> editContactDescriptor.setTelegram(telegram), () ->
                            editContactDescriptor.setTelegramDeleted());
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editContactDescriptor::setTags);
        if (!editContactDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditContactCommand.MESSAGE_NOT_EDITED);
        }

        return new EditContactCommand(index, editContactDescriptor);
    }

    /**
     * Parses the String containing the edited telegram into a {@code Telegram} if the String is non-empty.
     *
     * @param telegram String containing the telegram argument.
     * @return Optional describing a Telegram object.
     * @throws ParseException If the telegram argument does not conform the expected format.
     */
    private Optional<Telegram> parseTelegramForEdit(String telegram) throws ParseException {
        requireNonNull(telegram);

        if (telegram.isBlank()) {
            return Optional.empty();
        }
        Telegram editedTelegram = ParserUtil.parseTelegram(telegram);
        return Optional.of(editedTelegram);
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
