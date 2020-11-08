package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.project.DeletePersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePersonCommand object
 */
public class DeleteTeammateCommandParser implements Parser<DeletePersonCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @param userInput input after "deleteteammate" command from user
     * @return instance of DeleteTeammateCommandParser
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeletePersonCommand parse(String userInput) throws ParseException {
        try {
            GitUserIndex gitUserIndex = ParsePersonUtil.parseGitUserIndex(userInput);
            return new DeletePersonCommand(gitUserIndex);
        } catch (ParseException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE), e);
        }
    }
}
