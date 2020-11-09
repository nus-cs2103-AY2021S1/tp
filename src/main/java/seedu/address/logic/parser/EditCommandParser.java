package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.UNKNOWN_EDIT_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.edit.EditContactCommand;
import seedu.address.logic.commands.edit.EditContactCommand.EditPersonDescriptor;
import seedu.address.logic.commands.edit.EditEventCommand;
import seedu.address.logic.commands.edit.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.commands.edit.EditTodoCommand;
import seedu.address.logic.commands.edit.EditTodoCommand.EditTodoDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

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
        String[] splitArgs = args.trim().split(" ", 2);
        if (splitArgs[0].equals("contact")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditContactCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_INDEX, PREFIX_NAME,
                            PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

            Index index;

            if (!argMultimap.getValue(PREFIX_INDEX).isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditContactCommand.MESSAGE_USAGE));
            }

            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditContactCommand.MESSAGE_USAGE), pe);
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
            if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditContactCommand.MESSAGE_NOT_EDITED);
            }

            return new EditContactCommand(index, editPersonDescriptor);
        } else if (splitArgs[0].equals("todo")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTodoCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1],
                            PREFIX_INDEX, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TIME, PREFIX_TAG);

            Index index;

            if (!argMultimap.getValue(PREFIX_INDEX).isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTodoCommand.MESSAGE_USAGE));
            }

            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditTodoCommand.MESSAGE_USAGE), pe);
            }

            EditTodoDescriptor editTodoDescriptor = new EditTodoDescriptor();
            if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                editTodoDescriptor.setDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            }
            if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
                String date = argMultimap.getValue(PREFIX_DATE).get();
                editTodoDescriptor.setDate(date);
            }
            if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
                String time = argMultimap.getValue(PREFIX_TIME).get();
                editTodoDescriptor.setTime(time);
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTodoDescriptor::setTags);

            if (!editTodoDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditTodoCommand.MESSAGE_NOT_EDITED);
            }

            return new EditTodoCommand(index, editTodoDescriptor);
        } else if (splitArgs[0].equals("event")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditEventCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_INDEX, PREFIX_DESCRIPTION,
                            PREFIX_STARTDATE, PREFIX_STARTTIME, PREFIX_ENDDATE, PREFIX_ENDTIME, PREFIX_TAG);

            Index index;

            if (!argMultimap.getValue(PREFIX_INDEX).isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditEventCommand.MESSAGE_USAGE));
            }

            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditEventCommand.MESSAGE_USAGE), pe);
            }

            EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
            if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                editEventDescriptor.setDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            }
            if (argMultimap.getValue(PREFIX_STARTDATE).isPresent()) {
                editEventDescriptor.setStartDate(argMultimap.getValue(PREFIX_STARTDATE).get());
            }
            if (argMultimap.getValue(PREFIX_STARTTIME).isPresent()) {
                editEventDescriptor.setStartTime(argMultimap.getValue(PREFIX_STARTTIME).get());
            }
            if (argMultimap.getValue(PREFIX_ENDDATE).isPresent()) {
                editEventDescriptor.setEndDate(argMultimap.getValue(PREFIX_ENDDATE).get());
            }
            if (argMultimap.getValue(PREFIX_ENDTIME).isPresent()) {
                editEventDescriptor.setEndTime(argMultimap.getValue(PREFIX_ENDTIME).get());
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEventDescriptor::setTags);

            if (!editEventDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
            }

            return new EditEventCommand(index, editEventDescriptor);
        } else {
            throw new ParseException(UNKNOWN_EDIT_COMMAND);
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
