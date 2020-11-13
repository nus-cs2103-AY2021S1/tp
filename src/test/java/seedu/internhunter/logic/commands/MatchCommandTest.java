package seedu.internhunter.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.logic.commands.CommandTestUtil.showNoCompany;
import static seedu.internhunter.logic.commands.CommandTestUtil.showNoProfile;
import static seedu.internhunter.logic.commands.MatchCommand.NO_MATCHING_INTERNSHIPS_MESSAGE;
import static seedu.internhunter.logic.commands.MatchCommand.SHOWING_MATCH_COMMAND_MESSAGE;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_SWE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOLDMAN_BA;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.R_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code MatchCommand}. Testing is done
 * through the {@link MatchCommandTest#assertMatchCommandSuccess(Model, CommandResult)} method since the match
 * command does not change the model.
 */
public class MatchCommandTest {

    private Model model;
    private MatchCommand matchCommand;
    private CommandResult matchSuccessResult;
    private CommandResult noMatchResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), new ItemList<>(), getSampleProfileItemList(),
                new UserPrefs());
        matchCommand = new MatchCommand();
        matchSuccessResult = new CommandResult(SHOWING_MATCH_COMMAND_MESSAGE);
        noMatchResult = new CommandResult(NO_MATCHING_INTERNSHIPS_MESSAGE);
    }

    @Test
    public void execute_noChangesToModel_success() {
        Model expectedModel = new ModelManager(getSampleCompanyList(), new ItemList<>(), getSampleProfileItemList(),
                new UserPrefs());
        matchCommand.execute(model);
        assertEquals(model, expectedModel);
    }

    @Test
    public void execute_onlyOneCompanyMatched_successfulMatch() {
        matchSuccessResult.setMatchingInternships(FXCollections.observableArrayList(FACEBOOK_SWE));
        assertMatchCommandSuccess(model, matchSuccessResult);
    }

    @Nested
    class MultipleInternshipMatches {

        @BeforeEach
        public void setUp() {
            model.addProfileItem(R_SKILL);
            assertTrue(GOLDMAN_BA.matches(List.of(R_SKILL.getTitleValue())));
            matchSuccessResult.setMatchingInternships(FXCollections.observableArrayList(GOLDMAN_BA, FACEBOOK_SWE));
        }

        @Test
        public void execute_unfilteredLists_successfulMatch() {
            assertMatchCommandSuccess(model, matchSuccessResult);
        }

        @Test
        public void execute_filteredEmptyProfileList_successfulMatch() {
            showNoProfile(model);
            assertMatchCommandSuccess(model, matchSuccessResult);
        }

        @Test
        public void execute_filteredEmptyCompanyList_successfulMatch() {
            showNoCompany(model);
            assertMatchCommandSuccess(model, matchSuccessResult);
        }

        @Test
        public void execute_filteredBothLists_successfulMatch() {
            showNoCompany(model);
            showNoProfile(model);
            assertMatchCommandSuccess(model, matchSuccessResult);
        }

    }

    @Nested
    class NoInternshipMatches {

        @BeforeEach
        public void setUp() {
            model.deleteProfileItem(HTML_SKILL);
            // Asserts that there are no matching internships at this point
            assertMatchCommandSuccess(model, noMatchResult);
        }

        @Test
        public void execute_achievementCategory_noMatchingInternships() {
            ProfileItem profileItemToAdd =
                    new ProfileItemBuilder(HTML_SKILL).withCategory(VALID_CATEGORY_ACHIEVEMENT).build();
            model.addProfileItem(profileItemToAdd);
            assertMatchCommandSuccess(model, noMatchResult);
        }

        @Test
        public void execute_experienceCategory_noMatchingInternships() {
            ProfileItem profileItemToAdd =
                    new ProfileItemBuilder(HTML_SKILL).withCategory(VALID_CATEGORY_ACHIEVEMENT).build();
            model.addProfileItem(profileItemToAdd);
            assertMatchCommandSuccess(model, noMatchResult);
        }
    }

    @Nested
    class EmptyListsNoMatch {

        @Test
        public void execute_emptyCompanyList_noMatchingInternships() {
            model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleProfileItemList(),
                    new UserPrefs());
            assertMatchCommandSuccess(model, noMatchResult);
        }

        @Test
        public void execute_emptyProfileList_noMatchingInternships() {
            model = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(),
                    new UserPrefs());
            assertMatchCommandSuccess(model, noMatchResult);
        }

        @Test
        public void execute_bothEmptyLists_noMatchingInternships() {
            model = new ModelManager(new ItemList<>(), new ItemList<>(), new ItemList<>(),
                    new UserPrefs());
            assertMatchCommandSuccess(model, noMatchResult);
        }

    }

    /**
     * Checks if the given command result is the same as the command result from the execution of the match command.
     * Whether the 2 list of matching internships are equal is also tested through the equals method of the
     * CommandResult class.
     *
     * @param model Model used.
     * @param expectedCommandResult Expected CommandResult.
     */
    private void assertMatchCommandSuccess(Model model, CommandResult expectedCommandResult) {
        CommandResult actualCommandResult = matchCommand.execute(model);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

}
