package seedu.address.logic.parser;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.project.AddTeammateParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GitUserName;

public class AddTeammateParticipationParser implements Parser<AddTeammateParticipationCommand> {


    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     * @param userInput input string from user
     */
    @Override
    public AddTeammateParticipationCommand parse(String userInput) throws ParseException {
        GitUserIndex gitUserIndex = ParsePersonUtil.parseGitUserIndex(userInput);

        return new AddTeammateParticipationCommand(gitUserIndex);
    }
}
