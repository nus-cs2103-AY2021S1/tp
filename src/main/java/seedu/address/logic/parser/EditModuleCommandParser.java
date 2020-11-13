package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditModuleCommandParser implements Parser<EditModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditModuleCommand.MESSAGE_USAGE), pe);
        }

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            editModuleDescriptor.setModuleId(ParserUtil.parseModuleId(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditModuleCommand.MESSAGE_NOT_EDITED);
        }
        return new EditModuleCommand(index, editModuleDescriptor);
    }

}
