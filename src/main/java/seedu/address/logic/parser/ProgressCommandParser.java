package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOUBLE_DEGREE;

import seedu.address.logic.commands.ProgressCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ProgressCommandParser implements Parser<ProgressCommand> {

    private static final boolean isDdp = true;

    @Override
    public ProgressCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ProgressCommand(!isDdp);
        } else if (trimmedArgs.equals(PREFIX_DOUBLE_DEGREE.getPrefix())) {
            return new ProgressCommand(isDdp);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));
        }
    }
}
