package seedu.address.logic.parser.deliverable;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_MILESTONE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deliverable.EditCommand;
import seedu.address.logic.commands.deliverable.EditCommand.EditDeliverableDescriptor;
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
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_MILESTONE, PREFIX_DESCRIPTION, PREFIX_DEADLINE,
                        PREFIX_CONTACTS);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditDeliverableDescriptor editDeliverableDescriptor = new EditDeliverableDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editDeliverableDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }

        if (argMultimap.getValue(PREFIX_MILESTONE).isPresent()) {
            editDeliverableDescriptor.setMilestone(ParserUtil.parseMilestone(
                    argMultimap.getValue(PREFIX_MILESTONE).get()));
        }

        // Description takes optional String
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editDeliverableDescriptor.setDescription(ParserCommon.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION)));
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editDeliverableDescriptor.setDeadline(ParserUtil.parseDeadline(
                    argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        // Contacts takes optional String
        if (argMultimap.getValue(PREFIX_CONTACTS).isPresent()) {
            editDeliverableDescriptor.setContacts(ParserCommon.parseContacts(argMultimap.getValue(PREFIX_CONTACTS)));
        }

        if (!editDeliverableDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editDeliverableDescriptor);
    }
}
