package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditEventCommand object
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand
     * and returns an EditEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME, PREFIX_EVENT_DATE,
                        PREFIX_EVENT_LOCATION, PREFIX_EVENT_DESC);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_EVENT_NAME).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseEventName(
                    argMultimap.getValue(PREFIX_EVENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_DATE).isPresent()) {
            editEventDescriptor.setEventDate(ParserUtil.parseEventDate(
                    argMultimap.getValue(PREFIX_EVENT_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_LOCATION).isPresent()) {
            editEventDescriptor.setLocation(ParserUtil.parseLocation(
                    argMultimap.getValue(PREFIX_EVENT_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_DESC).isPresent()) {
            editEventDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_EVENT_DESC).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditEventCommand(index, editEventDescriptor);
    }
}
