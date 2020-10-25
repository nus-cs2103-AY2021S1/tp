package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DeleteAdditionalDetailCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAdditionalDetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAdditionalDetailCommandParser implements Parser<DeleteAdditionalDetailCommand> {

    /**
     * Parses the given {@code String} in the context of a DeleteAdditionalDetailCommand
     * and returns an DeleteAdditionalDetailCommand for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public DeleteAdditionalDetailCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index studentIndex = getStudentIndex(argMultimap);
        Index detailIndex = getDetailIndex(argMultimap);

        return new DeleteAdditionalDetailCommand(studentIndex, detailIndex);
    }

    private boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    private Index getStudentIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

    private Index getDetailIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

}
