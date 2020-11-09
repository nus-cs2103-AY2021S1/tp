package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEVERITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditProblemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditProblemCommand object
 */
public class EditProblemCommandParser implements Parser<EditProblemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditProblemCommand
     * and returns an EditProblemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditProblemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SEVERITY, PREFIX_DESCRIPTION);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException exception) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditProblemCommand.MESSAGE_USAGE), exception);
        }

        EditProblemCommand.EditProblemDescriptor editProblemDescriptor =
                new EditProblemCommand.EditProblemDescriptor();

        if (argMultimap.getValue(PREFIX_SEVERITY).isPresent()) {
            editProblemDescriptor.setSeverity(ParserUtil.parseSeverity(argMultimap.getValue(PREFIX_SEVERITY).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editProblemDescriptor
                    .setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (!editProblemDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProblemCommand(index, editProblemDescriptor);
    }
}
