package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_INDEX;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteVisitCommandParser object
 */
public class DeleteVisitCommandParser implements Parser<DeleteVisitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteVisitCommandParser}
     * and returns a {@code DeleteVisitCommandParser} object for execution.
     * @throws IllegalValueException if the user input does not conform the expected format
     * @throws NumberFormatException if the user input does not conform the expected format
     */
    @Override
    public DeleteVisitCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index patientIndex;
        int visitIndex;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_VISIT_INDEX);

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            String input = argMultimap.getValue(PREFIX_VISIT_INDEX).orElse(ParserUtil.MESSAGE_EMPTY_VISIT_INDEX);
            visitIndex = ParserUtil.parseVisitIndex(input);
        } catch (IllegalValueException | NumberFormatException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteVisitCommand.MESSAGE_USAGE), ive);
        }

        return new DeleteVisitCommand(patientIndex, visitIndex);
    }
}
