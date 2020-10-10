package seedu.fma.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_REPS;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.EditCommand;
import seedu.fma.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXERCISE, PREFIX_REPS, PREFIX_COMMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditLogDescriptor editLogDescriptor = new EditCommand.EditLogDescriptor();
        if (argMultimap.getValue(PREFIX_EXERCISE).isPresent()) {
            editLogDescriptor.setExercise(ParserUtil.parseExercise(argMultimap.getValue(PREFIX_EXERCISE).get()));
        }
        if (argMultimap.getValue(PREFIX_REPS).isPresent()) {
            editLogDescriptor.setRep(ParserUtil.parseRep(argMultimap.getValue(PREFIX_REPS).get()));
        }
        if (argMultimap.getValue(PREFIX_COMMENT).isPresent()) {
            editLogDescriptor.setComment(ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get()));
        }

        if (!editLogDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editLogDescriptor);
    }
}
