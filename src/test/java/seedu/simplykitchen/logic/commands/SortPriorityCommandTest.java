package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.SortPriorityCommand.MESSAGE_SUCCESS;
import static seedu.simplykitchen.logic.commands.SortPriorityCommand.SORT_PRIORITY_COMPARATORS;
import static seedu.simplykitchen.testutil.TypicalFood.ANCHOVIES;
import static seedu.simplykitchen.testutil.TypicalFood.BAGEL;
import static seedu.simplykitchen.testutil.TypicalFood.CARROT_CAKE;
import static seedu.simplykitchen.testutil.TypicalFood.DARK_CHOCOLATE;
import static seedu.simplykitchen.testutil.TypicalFood.EGGS;
import static seedu.simplykitchen.testutil.TypicalFood.FRENCH_FRIES;
import static seedu.simplykitchen.testutil.TypicalFood.GINGER;
import static seedu.simplykitchen.testutil.TypicalFood.WHITE_CHOCOLATE;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.Food;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortPriorityCommand.
 */
public class SortPriorityCommandTest {

    private Model model;
    private Model expectedModel;

    public void setUp(Food... foodsToAdd) {
        FoodInventory unsortedFoodInventory = getTypicalFoodInventory();
        for (Food food : foodsToAdd) {
            unsortedFoodInventory.addFood(food);
        }
        model = new ModelManager(unsortedFoodInventory, new UserPrefs());
        expectedModel = new ModelManager(model.getFoodInventory(), new UserPrefs());
    }

    @Test
    public void execute_sortByPriorityDifferentPriority_success() {
        setUp();
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortPriorityCommand command = new SortPriorityCommand();
        expectedModel.sortFoodInventory(SORT_PRIORITY_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DARK_CHOCOLATE, EGGS, ANCHOVIES, FRENCH_FRIES, BAGEL,
                CARROT_CAKE, GINGER), model.getFilteredFoodList());
    }

    @Test
    public void execute_sortByPrioritySamePrioritySameExpiryDifferentDescription_success() {
        setUp(WHITE_CHOCOLATE);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortPriorityCommand command = new SortPriorityCommand();
        expectedModel.sortFoodInventory(SORT_PRIORITY_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DARK_CHOCOLATE, WHITE_CHOCOLATE, EGGS, ANCHOVIES, FRENCH_FRIES, BAGEL,
                CARROT_CAKE, GINGER), model.getFilteredFoodList());
    }
}
