package seedu.address.logic.commands.deliverycommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DELIVERIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.delivery.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.delivery.predicate.DeliveryNameContainsKeywordsPredicate;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.deliverymodel.DeliveryModelManager;
import seedu.address.testutil.DeliveryBuilder;

class DeliveryFindCommandTest {

    private static final Delivery KELVIN = new DeliveryBuilder().withName("Kelvin Tan")
            .withPhone("91234332")
            .withAddress("Clementi Blk 235 #11-111")
            .withOrder("Char Kway Teow")
            .build();
    private static final Delivery MARCUS = new DeliveryBuilder().withName("Marcus Phua")
            .withPhone("8198264")
            .withAddress("Jurong Blk 231 #15-123")
            .withOrder("Seafood Hor Fun x5")
            .build();
    private DeliveryModel deliveryModel = new DeliveryModelManager(getTypicalDeliveryBook(), new UserPrefs());
    private DeliveryModel expectedDeliveryModel =
            new DeliveryModelManager(getTypicalDeliveryBook(), new UserPrefs());
    private DeliveryModel testModel;
    private DeliveryModel expectedTestModel;

    @BeforeEach
    public void setUp() {
        testModel = new DeliveryModelManager();
        expectedTestModel = new DeliveryModelManager();
        testModel.addDelivery(KELVIN);
        testModel.addDelivery(MARCUS);
        expectedTestModel.addDelivery(KELVIN);
        expectedTestModel.addDelivery(MARCUS);
    }

    @Test
    public void execute_zeroKeywords_noItemFound() {
        executeNameTest(0, " ",
                deliveryModel, expectedDeliveryModel, Collections.emptyList());
    }

    @Test
    public void execute_matchingSubstring_itemsFound() {
        executeAddressTest(2, "blk 23", testModel, expectedTestModel,
                testModel.getFilteredDeliveryList());
    }

    @Test
    public void execute_matchingKeywordsNotInOrder_itemsFound() {
        executeAddressTest(2, "23 blk", testModel, expectedTestModel,
                testModel.getFilteredDeliveryList());
    }

    @Test
    void equals() {
        DeliveryNameContainsKeywordsPredicate firstPredicate =
                new DeliveryNameContainsKeywordsPredicate(Collections.singletonList("first"));
        DeliveryNameContainsKeywordsPredicate secondPredicate =
                new DeliveryNameContainsKeywordsPredicate(Collections.singletonList("second"));
        DeliveryFindCommand findFirstCommand = new DeliveryFindCommand(firstPredicate);
        DeliveryFindCommand findSecondCommand = new DeliveryFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        DeliveryFindCommand findFirstCommandCopy = new DeliveryFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different delivery -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code DeliveryNameContainsKeywordsPredicate}.
     */
    private DeliveryNameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new DeliveryNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s*")));
    }

    /**
     * Parses {@code userInput} into a {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s*")));
    }

    /**
     * Carries out an {@code execute} test with relevant arguments.
     */
    private void executeNameTest(int numItemsMatched, String userInput,
                             DeliveryModel actualModel, DeliveryModel expectedModel,
                             List<Delivery> expectedFilteredDeliveries) {

        String expectedMessage = String.format(MESSAGE_DELIVERIES_LISTED_OVERVIEW, numItemsMatched);
        DeliveryNameContainsKeywordsPredicate predicate = prepareNamePredicate(userInput);
        DeliveryFindCommand command = new DeliveryFindCommand(predicate);
        expectedModel.updateFilteredDeliveryList(predicate);

        assertCommandSuccess(command, actualModel, expectedMessage, expectedModel);
        assertEquals(expectedFilteredDeliveries, actualModel.getFilteredDeliveryList());
    }

    /**
     * Carries out an {@code execute} test with relevant arguments.
     */
    private void executeAddressTest(int numItemsMatched, String userInput,
                                 DeliveryModel actualModel, DeliveryModel expectedModel,
                                 List<Delivery> expectedFilteredDeliveries) {

        String expectedMessage = String.format(MESSAGE_DELIVERIES_LISTED_OVERVIEW, numItemsMatched);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate(userInput);
        DeliveryFindCommand command = new DeliveryFindCommand(predicate);
        expectedModel.updateFilteredDeliveryList(predicate);

        assertCommandSuccess(command, actualModel, expectedMessage, expectedModel);
        assertEquals(expectedFilteredDeliveries, actualModel.getFilteredDeliveryList());
    }
}
