package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.deadline.Duration;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
public class DoneCommandParser implements Parser<DoneCommand> {

    static final String MAX_INT = "2147483647";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneCommand parse(String args) throws ParseException {
        try {

            String[] splited = args.trim().split(" ");
            Index[] indexes = new Index[splited.length];
            int[] durations = new int[splited.length];
            for (int i = 0; i < splited.length; i++) {
                String[] pair = splited[i].trim().split(":");
                if (pair.length <= 1) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", DoneCommand.MESSAGE_USAGE));
                }
                indexes[i] = ParserUtil.parseIndex(pair[0]);
                durations[i] = Integer.parseInt((pair[1]));
                if (!Duration.isValidDuration(durations[i])) {
                    throw new ParseException(Duration.INVALID_DURATION_FORMAT);
                }
            }
            return new DoneCommand(indexes, durations);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage(), DoneCommand.MESSAGE_USAGE), pe);
        } catch (NumberFormatException ne) {
            throw new ParseException("should be a positive number and the maximum duration is " + MAX_INT + " minutes");
        }
    }

}
