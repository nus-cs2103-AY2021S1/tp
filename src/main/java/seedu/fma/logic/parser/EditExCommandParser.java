package seedu.fma.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_C;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_E;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.EditExCommand;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.ReadOnlyLogBook;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditExCommandParser implements Parser<EditExCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditExCommand
     * and returns an EditExCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditExCommand parse(String args, ReadOnlyLogBook logBook) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_E, PREFIX_C);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExCommand.MESSAGE_USAGE), pe);
        }

        EditExCommand.EditExDescriptor editExDescriptor = new EditExCommand.EditExDescriptor();
        if (argMultimap.getValue(PREFIX_E).isPresent()) {
            editExDescriptor.setExerciseName(ParserUtil.parseName(argMultimap
                    .getValue(PREFIX_E).get()));
        }
        if (argMultimap.getValue(PREFIX_C).isPresent()) {
            editExDescriptor.setCaloriesPerRep(ParserUtil.parseCalories(argMultimap.getValue(PREFIX_C).get()));
        }

        if (!editExDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditExCommand.MESSAGE_NOT_EDITED);
        }

        return new EditExCommand(index, editExDescriptor);
    }
}
