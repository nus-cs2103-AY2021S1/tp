package seedu.address.logic.parser;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.project.DeletePersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePersonCommand object
 */
public class DeletePersonCommandParser implements Parser<DeletePersonCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @param userInput input command from user
     * @return instance of DeletePersonCommandParser
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeletePersonCommand parse(String userInput) throws ParseException {
        GitUserIndex gitUserIndex = ParsePersonUtil.parseGitUserIndex(userInput);
        return new DeletePersonCommand(gitUserIndex);
    }
}
