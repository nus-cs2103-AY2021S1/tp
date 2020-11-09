package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactTaskTagCommand;
import seedu.address.logic.commands.ContactTaskTagCommand.EditPersonTags;
import seedu.address.logic.commands.ContactTaskTagCommand.EditTaskTags;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ContactTaskTagCommand object
 */
public class ContactTaskTagParser implements Parser<ContactTaskTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ContactTaskTagCommand
     * and returns an ContactTaskTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ContactTaskTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitArgs = args.trim().split(" ", 1);
        if (splitArgs.length < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactTaskTagCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + splitArgs[0], PREFIX_TAG,
                PREFIX_CONTACT_INDEX, PREFIX_TASK_INDEX);

        List<String> tag = argMultimap.getAllValues(PREFIX_TAG);
        if (tag.size() < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactTaskTagCommand.MESSAGE_USAGE));
        }
        try {
            Index contactIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get());
            Index taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
            EditPersonTags editPersonTags = new EditPersonTags();
            editPersonTags.setTags(ParserUtil.parseTags(tag));

            EditTaskTags editTaskTags = new EditTaskTags();
            editTaskTags.setTags(ParserUtil.parseTags(tag));

            return new ContactTaskTagCommand(contactIndex, taskIndex, editPersonTags, editTaskTags);
        } catch (NoSuchElementException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactTaskTagCommand.MESSAGE_USAGE), pe);
        } catch (ParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }
    }
}
