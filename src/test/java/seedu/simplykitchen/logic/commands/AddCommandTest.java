package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;
import seedu.simplykitchen.model.ReadOnlyUserPrefs;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.testutil.FoodBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_foodAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFoodAdded modelStub = new ModelStubAcceptingFoodAdded();
        Food validFood = new FoodBuilder().build();

        CommandResult commandResult = new AddCommand(validFood).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFood), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFood), modelStub.foodAdded);
    }

    @Test
    public void execute_duplicateFood_throwsCommandException() {
        Food validFood = new FoodBuilder().build();
        AddCommand addCommand = new AddCommand(validFood);
        ModelStub modelStub = new ModelStubWithFood(validFood);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_FOOD, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Food applePie = new FoodBuilder().withDescription("Apple Pie").build();
        Food bread = new FoodBuilder().withDescription("Bread").build();
        AddCommand addApplePieCommand = new AddCommand(applePie);
        AddCommand addBreadCommand = new AddCommand(bread);

        // same object -> returns true
        assertTrue(addApplePieCommand.equals(addApplePieCommand));

        // same values -> returns true
        AddCommand addApplePieCommandCopy = new AddCommand(applePie);
        assertTrue(addApplePieCommand.equals(addApplePieCommandCopy));

        // different types -> returns false
        assertFalse(addApplePieCommand.equals(1));

        // null -> returns false
        assertFalse(addApplePieCommand.equals(null));

        // different food -> returns false
        assertFalse(addApplePieCommand.equals(addBreadCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFoodInventoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodInventoryFilePath(Path foodInventoryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFood(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodInventory(ReadOnlyFoodInventory foodInventory) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFoodInventory getFoodInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSortingComparators(Comparator<Food>[] sortingComparators) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFood(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFood(Food target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFood(Food target, Food editedFood) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFoodInventory(Comparator<Food>... comparators) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFoodInventoryBySortingComparators() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getFilteredFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        public ObservableList<Food> getFilteredExpiringFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getFilteredExpiredFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFoodList(Predicate<Food> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateExpiringFilteredFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoFoodInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoFoodInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoFoodInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoFoodInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitFoodInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Food> getExpiringPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateExpiringSortedFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Food> getExpiredPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isDataFileInvalid() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single food.
     */
    private class ModelStubWithFood extends ModelStub {
        private final Food food;

        ModelStubWithFood(Food food) {
            requireNonNull(food);
            this.food = food;
        }

        @Override
        public boolean hasFood(Food food) {
            requireNonNull(food);
            return this.food.isSameFood(food);
        }
    }

    /**
     * A Model stub that always accept the food being added.
     */
    private class ModelStubAcceptingFoodAdded extends ModelStub {
        final ArrayList<Food> foodAdded = new ArrayList<>();

        @Override
        public boolean hasFood(Food food) {
            requireNonNull(food);
            return foodAdded.stream().anyMatch(food::isSameFood);
        }

        @Override
        public void addFood(Food food) {
            requireNonNull(food);
            foodAdded.add(food);
        }

        @Override
        public void commitFoodInventory() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyFoodInventory getFoodInventory() {
            return new FoodInventory();
        }
    }

}
