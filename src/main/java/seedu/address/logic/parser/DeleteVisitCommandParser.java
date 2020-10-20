package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DELETE;

public class DeleteVisitCommandParser implements Parser<DeleteVisitCommand> {
    @Override
    public DeleteVisitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_VISIT_DELETE);
        Index index;
        int visitIdx;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteVisitCommand.MESSAGE_USAGE), ive);
        }

        try {
            String input = argMultimap.getValue(PREFIX_VISIT_DELETE).orElse(ParserUtil.MESSAGE_EMPTY_VISIT_INDEX);
            visitIdx = ParserUtil.parseVisitReportIndex(input);
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteVisitCommand.MESSAGE_USAGE), nfe);
        }

        return new DeleteVisitCommand(index, visitIdx);
    }
}
