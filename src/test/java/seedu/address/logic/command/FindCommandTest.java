package seedu.address.logic.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ACCOMMODATIONS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_FRIENDS_LISTED_OVERVIEW;
import static seedu.address.logic.command.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.command.CommandTestUtil.assertFindCommandSuccess;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TravelPlanner;
import seedu.address.model.UserPrefs;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.NameContainsKeywordsPredicate;

/**
 * Tests the properties of find command
 */
public class FindCommandTest {

    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @Test
    public void execute_findFriend_success() {
        List<String> keywords = Arrays.asList("Alice");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.updateFilteredFriendList(predicate);
        int expectedSize = expectedModel.getFilteredFriendList().size();
        String expectedMessage = String.format(MESSAGE_FRIENDS_LISTED_OVERVIEW, expectedSize);

        FindCommand findFriendCommand = new FindCommand(new NameContainsKeywordsPredicate(keywords),
                ParserUtil.FRIEND_INDEX);
        assertFindCommandSuccess(findFriendCommand, ParserUtil.FRIEND_INDEX, model, expectedMessage, expectedModel);
    }

    /** Finding friend in wishlist will throw an error */
    @Test
    public void execute_findFriendWrongDirectory_failure() {
        model.setDirectory(-1);

        List<String> keywords = Arrays.asList("Alice");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        FindCommand findFriendCommand = new FindCommand(predicate,
                ParserUtil.FRIEND_INDEX);
        String expectedMessage = Messages.MESSAGE_INVALID_TRAVEL_PLAN_OBJECT_AT_WISHLIST;

        assertCommandFailure(findFriendCommand, model, expectedMessage);
    }

    /**
     * Travel Plan Object matching at least one keyword will be returned.
     * 'Alice Meier' and 'Daniel Meier' matches keyword 'Meier'.
     */
    @Test
    public void execute_findMatchMultiple_success() {
        List<String> keywords = Arrays.asList("Meier");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.updateFilteredFriendList(predicate);
        int expectedSize = expectedModel.getFilteredFriendList().size(); //2 matches Alice Meier and Daniel Meier

        assertTrue(expectedSize == 2);
    }

    /**
     * The search is case-insensitive.
     * `alice` will match `Alice`
     */
    @Test
    public void execute_findCaseInsensitive_success() {

        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("alice"));
        NameContainsKeywordsPredicate testPredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice"));

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.updateFilteredFriendList(predicate); //expected model uses testPredicate
        int expectedSize = expectedModel.getFilteredFriendList().size();
        String expectedMessage = String.format(MESSAGE_FRIENDS_LISTED_OVERVIEW, expectedSize);

        //actual model uses predicate
        FindCommand findFriendCommand = new FindCommand(testPredicate, ParserUtil.FRIEND_INDEX);
        assertFindCommandSuccess(findFriendCommand, ParserUtil.FRIEND_INDEX, model, expectedMessage, expectedModel);
    }

    /**
     * The order of the keywords does not matter.
     * e.g. `alice benson` will match `benson alice`
     */
    @Test
    public void execute_findDifferentOrder_success() {

        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Arrays.asList("alice", "benson"));

        NameContainsKeywordsPredicate testPredicate = new NameContainsKeywordsPredicate(
                Arrays.asList("benson", "alice"));

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.updateFilteredFriendList(predicate); //expected model uses testPredicate
        int expectedSize = expectedModel.getFilteredFriendList().size();
        String expectedMessage = String.format(MESSAGE_FRIENDS_LISTED_OVERVIEW, expectedSize);

        //actual model uses predicate
        FindCommand findFriendCommand = new FindCommand(testPredicate, ParserUtil.FRIEND_INDEX);
        assertFindCommandSuccess(findFriendCommand, ParserUtil.FRIEND_INDEX, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_findAccommodation_success() {
        List<String> keywords = Arrays.asList("inn");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.updateFilteredAccommodationList(predicate);
        int expectedSize = expectedModel.getFilteredAccommodationList().size();
        String expectedMessage = String.format(MESSAGE_ACCOMMODATIONS_LISTED_OVERVIEW, expectedSize);

        FindCommand findAccommodation = new FindCommand(new NameContainsKeywordsPredicate(keywords),
                ParserUtil.ACCOMMODATION_INDEX);
        assertFindCommandSuccess(findAccommodation, ParserUtil.ACCOMMODATION_INDEX, model, expectedMessage,
                expectedModel);
    }

    /** Finding accommodation in wishlist will throw an error */
    @Test
    public void execute_findAccommodationWrongDirectory_failure() {
        model.setDirectory(-1);

        List<String> keywords = Arrays.asList("inn");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        FindCommand findAccommodationCommand = new FindCommand(predicate,
                ParserUtil.ACCOMMODATION_INDEX);
        String expectedMessage = Messages.MESSAGE_INVALID_TRAVEL_PLAN_OBJECT_AT_WISHLIST;

        assertCommandFailure(findAccommodationCommand, model, expectedMessage);
    }

    @Test
    public void execute_findActivity_success() {
        List<String> keywords = Arrays.asList("archery");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        expectedModel.updateFilteredActivityList(predicate);
        int expectedSize = expectedModel.getFilteredActivityList().size();
        String expectedMessage = String.format(MESSAGE_ACTIVITIES_LISTED_OVERVIEW, expectedSize);

        FindCommand findAccommodation = new FindCommand(new NameContainsKeywordsPredicate(keywords),
                ParserUtil.ACTIVITY_INDEX);
        assertFindCommandSuccess(findAccommodation, ParserUtil.ACTIVITY_INDEX, model, expectedMessage, expectedModel);
    }

    /**
     * Only Activity with name matching "archery" should appear in filtered activity list
     * DonutDate Activity should not be in the list
     */
    @Test
    public void execute_findCorrectActivity() {
        List<String> keywords = Arrays.asList("archery");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        Activity archery = expectedModel.getFilteredActivityList().get(0); //archery activity
        Activity donutDate = expectedModel.getFilteredActivityList().get(3); //donutdate activity
        expectedModel.updateFilteredActivityList(predicate);

        assertTrue(expectedModel.getFilteredActivityList().contains(archery));
        assertFalse(expectedModel.getFilteredActivityList().contains(donutDate));

    }

    /**
     * Only full words will be matched
     * Archery Activity should not match 'archerys'
     */
    @Test
    public void execute_findMatchFullWord() {
        List<String> keywords = Arrays.asList("archerys");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        ModelManager expectedModel = new ModelManager(new TravelPlanner(model.getTravelPlanner()), new UserPrefs());
        expectedModel.setDirectory(0);
        Activity archery = expectedModel.getFilteredActivityList().get(0); //archery activity
        expectedModel.updateFilteredActivityList(predicate);

        assertFalse(expectedModel.getFilteredActivityList().contains(archery));

    }

}
