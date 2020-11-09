package seedu.address.logic.parser.tagparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TagEditCommand;
import seedu.address.logic.commands.tagcommands.TagEditCommand.EditTagDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TagEditCommand object
 */
public class TagEditCommandParser implements Parser<TagEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagEditCommand
     * and returns a TagEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagEditCommand.MESSAGE_USAGE), pe);
        }

        EditTagDescriptor editTagDescriptor = new EditTagDescriptor();
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            editTagDescriptor.setTagName(ParserUtil.parseTagName(argMultimap.getValue(PREFIX_TAG).get()));
        } else {
            throw new ParseException(TagEditCommand.MESSAGE_NOT_EDITED);
        }

        return new TagEditCommand(index, editTagDescriptor);
    }
}
