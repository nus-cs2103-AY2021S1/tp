package tutorspet.logic.parser.student;

import static tutorspet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.student.DeleteStudentCommand;
import tutorspet.logic.parser.Parser;
import tutorspet.logic.parser.ParserUtil;
import tutorspet.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteStudentCommand object.
 */
public class DeleteStudentCommandParser implements Parser<DeleteStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStudentCommand
     * and returns a DeleteStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteStudentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteStudentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE), pe);
        }
    }
}
