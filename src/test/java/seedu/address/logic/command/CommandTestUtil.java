package seedu.address.logic.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.edit.EditDescriptor;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TravelPlanner;
import seedu.address.model.commons.NameContainsKeywordsPredicate;
import seedu.address.model.commons.TravelPlanObject;
import seedu.address.model.travelplan.TravelPlan;
import seedu.address.testutil.EditAccommodationDescriptorBuilder;
import seedu.address.testutil.EditActivityDescriptorBuilder;
import seedu.address.testutil.EditFriendDescriptorBuilder;
import seedu.address.testutil.EditTravelPlanDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {


    //Travel Plan
    public static final String VALID_NAME_EUROPE = "Europe Trip";
    public static final String VALID_NAME_NYC = "New York City";
    public static final String VALID_START_DATE_EUROPE = "2021-12-31";
    public static final String VALID_START_DATE_NYC = "2021-01-01";
    public static final String VALID_END_DATE_EUROPE = "2021-12-31";
    public static final String VALID_END_DATE_NYC = "2021-12-02";

    //Activity
    public static final String VALID_NAME_ZOO = "Singapore Mandai Zoo";
    public static final String VALID_LOCATION_ZOO = "124 Mandai Road";
    public static final String VALID_COST_ZOO = "100";
    public static final String VALID_LEVELOFIMPORTANCE_ZOO = "3";
    public static final String VALID_ACTIVITYDATETIME_ZOO = "2021-06-03 12:00";

    public static final String VALID_NAME_SKI = "Gore Mountain Ski Resort";
    public static final String VALID_LOCATION_SKI = "124 Ski Avenue";
    public static final String VALID_COST_SKI = "200";
    public static final String VALID_LEVELOFIMPORTANCE_SKI = "5";
    public static final String VALID_ACTIVITYDATETIME_SKI = "2021-12-10 12:00";

    //Accommodation
    public static final String VALID_NAME_HOME = "Singapore";
    public static final String VALID_LOCATION_HOME = "123 Singapore Road";
    public static final String VALID_COST_HOME = "0";
    public static final String VALID_START_DATE_HOME = "2021-01-05";
    public static final String VALID_END_DATE_HOME = "2021-05-31";

    public static final String VALID_NAME_INN = "Lloyd's Inn";
    public static final String VALID_LOCATION_INN = "2 Lloyd Rd";
    public static final String VALID_COST_INN = "250";
    public static final String VALID_START_DATE_INN = "2021-01-10";
    public static final String VALID_END_DATE_INN = "2021-01-16";

    //Friend
    public static final String VALID_NAME_AMY = "Amy Choo";
    public static final String VALID_MOBILE_AMY = "81234567";
    public static final String VALID_PASSPORT_AMY = "E1234567T";

    public static final String VALID_NAME_BOB = "Bob Tan";
    public static final String VALID_MOBILE_BOB = "91238765";
    public static final String VALID_PASSPORT_BOB = "E1443482G";

    //With Prefix Travel Plan
    public static final String NAME_DESC_EUROPE = " " + PREFIX_NAME + VALID_NAME_EUROPE;
    public static final String NAME_DESC_NYC = " " + PREFIX_NAME + VALID_NAME_NYC;
    public static final String START_DATE_DESC_EUROPE = " " + PREFIX_START + VALID_START_DATE_EUROPE;
    public static final String START_DATE_DESC_NYC = " " + PREFIX_START + VALID_START_DATE_NYC;
    public static final String END_DATE_DESC_EUROPE = " " + PREFIX_END + VALID_END_DATE_EUROPE;
    public static final String END_DATE_DESC_NYC = " " + PREFIX_END + VALID_END_DATE_NYC;

    //With Prefix Activity
    public static final String NAME_DESC_ZOO = " " + PREFIX_NAME + VALID_NAME_ZOO;
    public static final String NAME_DESC_SKI = " " + PREFIX_NAME + VALID_NAME_SKI;
    public static final String COST_DESC_ZOO = " " + PREFIX_COST + VALID_COST_ZOO;
    public static final String COST_DESC_SKI = " " + PREFIX_COST + VALID_COST_SKI;
    public static final String LOCATION_DESC_ZOO = " " + PREFIX_LOCATION + VALID_LOCATION_ZOO;
    public static final String LOCATION_DESC_SKI = " " + PREFIX_LOCATION + VALID_LOCATION_SKI;
    public static final String IMPORTANCE_DESC_ZOO = " " + PREFIX_IMPORTANCE + VALID_LEVELOFIMPORTANCE_ZOO;
    public static final String IMPORTANCE_DESC_SKI = " " + PREFIX_IMPORTANCE + VALID_LEVELOFIMPORTANCE_SKI;
    public static final String DATETIME_DESC_ZOO = " " + PREFIX_DATETIME + VALID_ACTIVITYDATETIME_ZOO;
    public static final String DATETIME_DESC_SKI = " " + PREFIX_DATETIME + VALID_ACTIVITYDATETIME_SKI;

    //With Prefix Accommodation
    public static final String NAME_DESC_HOME = " " + PREFIX_NAME + VALID_NAME_HOME;
    public static final String NAME_DESC_INN = " " + PREFIX_NAME + VALID_NAME_INN;
    public static final String COST_DESC_HOME = " " + PREFIX_COST + VALID_COST_HOME;
    public static final String COST_DESC_INN = " " + PREFIX_COST + VALID_COST_INN;
    public static final String LOCATION_DESC_HOME = " " + PREFIX_LOCATION + VALID_LOCATION_HOME;
    public static final String LOCATION_DESC_INN = " " + PREFIX_LOCATION + VALID_LOCATION_INN;
    public static final String START_DATE_DESC_HOME = " " + PREFIX_START + VALID_START_DATE_HOME;
    public static final String START_DATE_DESC_INN = " " + PREFIX_START + VALID_START_DATE_INN;
    public static final String END_DATE_DESC_HOME = " " + PREFIX_END + VALID_END_DATE_HOME;
    public static final String END_DATE_DESC_INN = " " + PREFIX_END + VALID_END_DATE_INN;

    //With Prefix Friend
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MOBILE_DESC_AMY = " " + PREFIX_MOBILE + VALID_MOBILE_AMY;
    public static final String MOBILE_DESC_BOB = " " + PREFIX_MOBILE + VALID_MOBILE_BOB;
    public static final String PASSPORT_DESC_AMY = " " + PREFIX_PASSPORT + VALID_PASSPORT_AMY;
    public static final String PASSPORT_DESC_BOB = " " + PREFIX_PASSPORT + VALID_PASSPORT_BOB;

    //Invalid string
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_START_DATE_DESC = " " + PREFIX_START + "911a"; // letters not allowed in date
    public static final String INVALID_END_DATE_DESC = " " + PREFIX_END + "2020-!10-10"; // no special character
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION + "James&"; // '&' not allowed in location
    public static final String INVALID_COST_DESC = " " + PREFIX_COST + "911a"; // letters not allowed in date
    public static final String INVALID_IMPORTANCE_DESC = " " + PREFIX_IMPORTANCE + "10"; // exceeds range
    public static final String INVALID_ACTIVITYDATETIME_DESC = " " + PREFIX_DATETIME + "2020-12-12 2400"; // Must have :
    public static final String INVALID_MOBILE_DESC = " " + PREFIX_MOBILE + "8123456!"; // No special character
    public static final String INVALID_PASSPORT_DESC = " " + PREFIX_PASSPORT + "a12345678"; // exceeds length

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String COMMAND_PREFIX_DESC = " -";

    public static final EditDescriptor DESC_EUROPE;
    public static final EditDescriptor DESC_NYC;

    static {
        DESC_EUROPE = new EditTravelPlanDescriptorBuilder().withName(VALID_NAME_EUROPE)
                .withStartDate(VALID_START_DATE_EUROPE).withEndDate(VALID_END_DATE_EUROPE).build();
        DESC_NYC = new EditTravelPlanDescriptorBuilder().withName(VALID_NAME_NYC)
                .withStartDate(VALID_START_DATE_NYC).withEndDate(VALID_END_DATE_NYC).build();
    }

    public static final EditDescriptor DESC_AMY;
    public static final EditDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditFriendDescriptorBuilder().withName(VALID_NAME_AMY).withMobile(VALID_MOBILE_AMY)
                .withPassport(VALID_PASSPORT_AMY).build();
        DESC_BOB = new EditFriendDescriptorBuilder().withName(VALID_MOBILE_BOB).withMobile(VALID_MOBILE_BOB)
                .withPassport(VALID_PASSPORT_BOB).build();
    }

    public static final EditDescriptor DESC_HOME;
    public static final EditDescriptor DESC_INN;

    static {
        DESC_HOME = new EditAccommodationDescriptorBuilder().withName(VALID_NAME_HOME).withCost(VALID_COST_HOME)
                .withLocation(VALID_LOCATION_HOME).withStartDate(VALID_START_DATE_HOME)
                .withEndDate(VALID_END_DATE_HOME).build();
        DESC_INN = new EditAccommodationDescriptorBuilder().withName(VALID_NAME_INN).withCost(VALID_COST_INN)
                .withLocation(VALID_LOCATION_INN).withStartDate(VALID_START_DATE_INN)
                .withEndDate(VALID_END_DATE_INN).build();
    }

    public static final EditDescriptor DESC_ZOO;
    public static final EditDescriptor DESC_SKI;

    static {
        DESC_ZOO = new EditActivityDescriptorBuilder().withName(VALID_NAME_ZOO).withCost(VALID_COST_ZOO)
                .withLocation(VALID_LOCATION_ZOO).withImportance(VALID_LEVELOFIMPORTANCE_ZOO)
                .withDateTime(VALID_ACTIVITYDATETIME_SKI).build();

        DESC_SKI = new EditActivityDescriptorBuilder().withName(VALID_NAME_SKI).withCost(VALID_COST_SKI)
                .withLocation(VALID_LOCATION_SKI).withImportance(VALID_LEVELOFIMPORTANCE_SKI)
                .withDateTime(VALID_ACTIVITYDATETIME_SKI).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage} and integer {@code tpoType}. Used when command results in
     * change in tabs (between activity, accommodation and friend tabs).
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel, int tpoType) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, tpoType);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TravelPlanner expectedTravelPlanner = new TravelPlanner(actualModel.getTravelPlanner());
        List<TravelPlan> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTravelPlanList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTravelPlanner, actualModel.getTravelPlanner());
        assertEquals(expectedFilteredList, actualModel.getFilteredTravelPlanList());
    }

    /**
     * Updates {@code model}'s filtered travelplan list to show only the travel plan at the given {@code targetIndex} in
     * the {@code model}'s travel planner.
     */
    public static void showTravelPlanAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTravelPlanList().size());

        TravelPlan tp = model.getFilteredTravelPlanList().get(targetIndex.getZeroBased());
        final String[] splitName = tp.getName().getValue().split("\\s+");
        model.updateFilteredTravelPlanList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[2])));

        assertEquals(1, model.getFilteredTravelPlanList().size());
    }

    /**
     * Updates {@code model}'s filtered travel plan object list to show only the travel plan objects at the given
     * {@code targetIndex} in the {@code model}'s respective filtered list.
     */
    public static void showTpoAtIndex(Model model, Index targetIndex, String tpoType) {
        ObservableList<? extends TravelPlanObject> filteredList;

        switch (tpoType) {
        case "Activity":
            filteredList = model.getFilteredActivityList();
            break;
        case "Accommodation":
            filteredList = model.getFilteredAccommodationList();
            break;
        case "Friend":
            filteredList = model.getFilteredFriendList();
            break;
        default:
            throw new IllegalStateException("Unexpected tpoType value: " + tpoType);
        }

        assertTrue(targetIndex.getZeroBased() < filteredList.size());

        TravelPlanObject travelPlanObject = filteredList.get(targetIndex.getZeroBased());
        final String[] splitName = travelPlanObject.getName().getValue().split("\\s+");

        switch (tpoType) {
        case "Activity":
            model.updateFilteredActivityList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
            break;
        case "Accommodation":
            model.updateFilteredAccommodationList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
            break;
        case "Friend":
            model.updateFilteredFriendList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
            break;
        default:
            throw new IllegalStateException("Unexpected tpoType value: " + tpoType);
        }

        assertEquals(1, filteredList.size());
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * Integer index @code{index} indicates the type of Travel Plan Object to find in the model
     */
    public static void assertFindCommandSuccess(Command command, int index, Model actualModel, String expectedMessage,
                                                Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, index);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

}
