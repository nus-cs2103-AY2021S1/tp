package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.SortDescCommand.MESSAGE_SUCCESS;
import static seedu.simplykitchen.logic.commands.SortDescCommand.SORT_DESC_COMPARATORS;
import static seedu.simplykitchen.testutil.TypicalFood.ANCHOVIES;
import static seedu.simplykitchen.testutil.TypicalFood.APPLE;
import static seedu.simplykitchen.testutil.TypicalFood.APRICOT;
import static seedu.simplykitchen.testutil.TypicalFood.BAGEL;
import static seedu.simplykitchen.testutil.TypicalFood.CARROT_CAKE;
import static seedu.simplykitchen.testutil.TypicalFood.CHEAP_EGGS;
import static seedu.simplykitchen.testutil.TypicalFood.DARK_CHOCOLATE;
import static seedu.simplykitchen.testutil.TypicalFood.EGGS;
import static seedu.simplykitchen.testutil.TypicalFood.FRENCH_FRIES;
import static seedu.simplykitchen.testutil.TypicalFood.GINGER;
import static seedu.simplykitchen.testutil.TypicalFood.OLD_EGGS;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.Food;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortDescCommand.
 */
public class SortDescCommandTest {

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
    public void execute_sortByDescriptionDifferentDescription_success() {
        setUp(APRICOT, APPLE);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortDescCommand command = new SortDescCommand();

        expectedModel.sortFoodInventory(SORT_DESC_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPLE, APRICOT, ANCHOVIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, FRENCH_FRIES, GINGER), model.getFilteredFoodList());
    }

    @Test
    public void execute_sortByDescriptionSameDescriptionDifferentExpiry_success() {
        setUp(OLD_EGGS);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortDescCommand command = new SortDescCommand();

        expectedModel.sortFoodInventory(SORT_DESC_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, OLD_EGGS, EGGS, FRENCH_FRIES, GINGER), model.getFilteredFoodList());
    }

    @Test
    public void execute_sortByDescriptionSameDescriptionSameExpiryDifferentPriority_success() {
        setUp(CHEAP_EGGS);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        SortDescCommand command = new SortDescCommand();

        expectedModel.sortFoodInventory(SORT_DESC_COMPARATORS);
        expectedModel.commitFoodInventory();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, CHEAP_EGGS, FRENCH_FRIES, GINGER), model.getFilteredFoodList());
    }
}
