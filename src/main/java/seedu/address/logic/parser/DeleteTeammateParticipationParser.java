package seedu.address.logic.parser;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.project.DeleteTeammateParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteTeammateParticipationParser implements Parser<DeleteTeammateParticipationCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @param userInput input after "deletepart" command from user
     * @return instance of DeleteTeammateCommandParser
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteTeammateParticipationCommand parse(String userInput) throws ParseException {
            GitUserIndex gitUserIndex = ParsePersonUtil.parseGitUserIndex(userInput);
            return new DeleteTeammateParticipationCommand(gitUserIndex);
    }
}
