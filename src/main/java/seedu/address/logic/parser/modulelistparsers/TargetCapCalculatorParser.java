package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.modulelistcommands.TargetCapCalculatorCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TargetCapCalculatorCommand object.
 */
public class TargetCapCalculatorParser implements Parser<TargetCapCalculatorCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TargetCapCalculatorCommand
     * and returns an TargetCapCalculatorCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TargetCapCalculatorCommand parse(String args) throws ParseException {
        try {
            double targetCap = Double.parseDouble(args);
            return new TargetCapCalculatorCommand(targetCap);
        } catch (NumberFormatException ne) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TargetCapCalculatorCommand.MESSAGE_USAGE), ne);
        }
    }
}
