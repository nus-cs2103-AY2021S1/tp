package seedu.address.logic.parser;

import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_GIT_USERNAME;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParsePersonUtil.MESSAGE_INVALID_GIT_INDEX;
import static seedu.address.testutil.TypicalGitIndexes.GIT_USERINDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.AddTeammateParticipationCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

public class AddTeammateParticipationCommandParsertest {

    /**
     * Parses valid arguments and returns Teammate Participation Command
     * (required by checkstyle)
     */
    @Test
    public void parse_validArgs_returnAddTeammateParticipationCommand() {
        AddTeammateParticipationCommandParser parser = new AddTeammateParticipationCommandParser();
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.addParticipation(DESC_A);
        model.addParticipation(project.getParticipation(DESC_A.getGitUserNameString()));

        assertParseSuccess(parser, VALID_TEAMMATE_GIT_USERNAME_A,
            new AddTeammateParticipationCommand(GIT_USERINDEX_FIRST_TEAMMATE));
    }

    /**
     * Parses invalid arguments and throws parse exception
     * (required by checkstyle)
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        AddTeammateParticipationCommandParser parser = new AddTeammateParticipationCommandParser();
        assertParseFailure(parser, INVALID_TEAMMATE_GIT_USERNAME,
            String.format(MESSAGE_INVALID_GIT_INDEX));
    }
}
