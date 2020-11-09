package seedu.address.logic.command.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.FRIEND_INDEX;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;


public class SortFriendCommandTest {
    public static final String KEYWORD_NAME = "name";
    public static final String KEYWORD_MOBILE = "mobile";
    public static final String KEYWORD_PASSPORT = "passport";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @AfterEach
    public void tearDown() {
        model = null;
    }

    @Test
    public void execute_sortFriendName_success() {
        SortFriendCommand sortFriendCommand = new SortFriendCommand(KEYWORD_NAME);

        String expectedMessage = String.format(SortFriendCommand.MESSAGE_SORT_FRIEND_SUCCESS,
                KEYWORD_NAME);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Friend> nameComparator = Comparator.comparing(d -> d.getName().toString());

        expectedModel.setDirectory(0);
        expectedModel.sortFriendList(nameComparator);

        assertCommandSuccess(sortFriendCommand, model, expectedMessage, expectedModel, FRIEND_INDEX);
    }

    @Test
    public void execute_sortFriendMobile_success() {
        SortFriendCommand sortFriendCommand = new SortFriendCommand(KEYWORD_MOBILE);

        String expectedMessage = String.format(SortFriendCommand.MESSAGE_SORT_FRIEND_SUCCESS,
                KEYWORD_MOBILE);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Friend> mobileComparator = Comparator.comparing(f -> f.getMobile().getValue());

        expectedModel.setDirectory(0);
        expectedModel.sortFriendList(mobileComparator);

        assertCommandSuccess(sortFriendCommand, model, expectedMessage, expectedModel, FRIEND_INDEX);
    }

    @Test
    public void execute_sortFriendPassport_success() {
        SortFriendCommand sortFriendCommand = new SortFriendCommand(SortCommand.KEYWORD_PASSPORT);

        String expectedMessage = String.format(SortFriendCommand.MESSAGE_SORT_FRIEND_SUCCESS,
                KEYWORD_PASSPORT);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Friend> passportComparator = Comparator.comparing(f -> f.getPassport().getValue());

        expectedModel.setDirectory(0);
        expectedModel.sortFriendList(passportComparator);

        assertCommandSuccess(sortFriendCommand, model, expectedMessage, expectedModel, FRIEND_INDEX);
    }

    @Test
    public void equals() {
        SortFriendCommand sortFriendFirstCommand = new SortFriendCommand(KEYWORD_NAME);
        SortFriendCommand sortFriendSecondCommand = new SortFriendCommand(KEYWORD_MOBILE);

        // same object -> returns true
        assertTrue(sortFriendFirstCommand.equals(sortFriendFirstCommand));

        // same values -> returns true
        SortFriendCommand sortFriendFirstCommandCopy = new SortFriendCommand(KEYWORD_NAME);
        assertTrue(sortFriendFirstCommand.equals(sortFriendFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFriendFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFriendFirstCommand.equals(null));

        // different sort keyword -> returns false
        assertFalse(sortFriendFirstCommand.equals(sortFriendSecondCommand));
    }
}
