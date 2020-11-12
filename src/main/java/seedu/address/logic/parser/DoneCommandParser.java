package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.deadline.Duration;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
public class DoneCommandParser implements Parser<DoneCommand> {

    static final String MAX_INT = String.valueOf(Integer.MAX_VALUE);
    static final String NUMBER_FORMAT_ERROR = "should be a positive number and the maximum duration is "
            + MAX_INT + " minutes";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneCommand parse(String args) throws ParseException {
        try {
            String[] splited = args.trim().split("[ \n\t]+");
            int length = splited.length;
            Index[] indexes = new Index[length];
            int[] durations = new int[length];
            for (int i = 0; i < length; i++) {
                String[] pair = splited[i].trim().split(":");
                if (pair.length < 2) {
                    throw new ParseException(Messages.INVALID_DONE_INDEX_FORMAT);
                }
                indexes[i] = ParserUtil.parseIndex(pair[0]);
                durations[i] = Integer.parseInt((pair[1]));
                if (!Duration.isValidDuration(durations[i])) {
                    throw new ParseException(Messages.INVALID_DURATION_FORMAT);
                }
            }
            return new DoneCommand(indexes, durations);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage(),
                            DoneCommand.MESSAGE_USAGE), pe);
        } catch (NumberFormatException ne) {
            throw new ParseException(NUMBER_FORMAT_ERROR);
        }
    }

}
