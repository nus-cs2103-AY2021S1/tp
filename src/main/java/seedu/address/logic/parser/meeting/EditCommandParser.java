package seedu.address.logic.parser.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TO;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditCommand;
import seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ParserCommon;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_TO, PREFIX_FROM,
                        PREFIX_CONTACTS, PREFIX_LOCATION);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editMeetingDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }

        // Description takes optional String
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editMeetingDescriptor.setDescription(ParserCommon.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION)));
        }

        if (argMultimap.getValue(PREFIX_FROM).isPresent()) {
            editMeetingDescriptor.setFrom(ParserUtil.parseFrom(argMultimap.getValue(PREFIX_FROM).get()));
        }

        if (argMultimap.getValue(PREFIX_TO).isPresent()) {
            editMeetingDescriptor.setTo(ParserUtil.parseTo(argMultimap.getValue(PREFIX_TO).get()));
        }

        // Contacts takes optional String
        if (argMultimap.getValue(PREFIX_CONTACTS).isPresent()) {
            editMeetingDescriptor.setContacts(ParserCommon.parseContacts(argMultimap
                    .getValue(PREFIX_CONTACTS)));
        }

        // Location takes optional String
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editMeetingDescriptor.setLocation(ParserUtil.parseLocation(argMultimap
                    .getValue(PREFIX_LOCATION)));
        }

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editMeetingDescriptor);
    }
}
