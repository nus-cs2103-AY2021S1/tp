package seedu.simplykitchen.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.ANCHOVIES;
import static seedu.simplykitchen.testutil.TypicalFood.BAGEL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.testutil.FoodBuilder;
import seedu.simplykitchen.testutil.FoodInventoryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FoodInventory(), new FoodInventory(
                modelManager.getFoodInventory()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFoodInventoryFilePath(Paths.get("foodInventory/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFoodInventoryFilePath(Paths.get("new/foodInventory/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setFoodInventoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFoodInventoryFilePath(null));
    }

    @Test
    public void setFoodInventoryFilePath_validPath_setsFoodInventoryFilePath() {
        Path path = Paths.get("foodInventory/file/path");
        modelManager.setFoodInventoryFilePath(path);
        assertEquals(path, modelManager.getFoodInventoryFilePath());
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInFoodInventory_returnsFalse() {
        assertFalse(modelManager.hasFood(ANCHOVIES));
    }

    @Test
    public void hasFood_foodInFoodInventory_returnsTrue() {
        modelManager.addFood(ANCHOVIES);
        assertTrue(modelManager.hasFood(ANCHOVIES));
    }

    @Test
    public void getFilteredFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFoodList().remove(0));
    }

    @Test
    public void getFilteredExpiringFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpiringFoodList().remove(0));
    }

    @Test
    public void getFilteredExpiredFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpiredFoodList().remove(0));
    }

    @Test
    public void getExpiringPredicate_predicateTest_predicateReturnsTrue() {
        Predicate<Food> predicate = modelManager.getExpiringPredicate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-M-yyyy");

        LocalDate today = LocalDate.now();
        String dateToday = today.format(dateFormat);

        Food pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateToday).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expiring today

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String dateTomorrow = tomorrow.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateTomorrow).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expiring tomorrow

        LocalDate threeDaysLater = LocalDate.now().plusDays(3);
        String dateThreeDaysLater = threeDaysLater.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateThreeDaysLater).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expiring 3 days from today

        LocalDate sixDaysLater = LocalDate.now().plusDays(6);
        String dateSixDaysLater = sixDaysLater.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateSixDaysLater).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expiring 6 days from today

        LocalDate nextWeek = LocalDate.now().plusDays(7);
        String dateNextWeek = nextWeek.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateNextWeek).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expiring 7 days from today
    }

    @Test
    public void getExpiringPredicate_predicateTest_predicateReturnsFalse() {
        Predicate<Food> predicate = modelManager.getExpiringPredicate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-M-yyyy");

        LocalDate minusTenDays = LocalDate.now().minusDays(10);
        String dateMinusTenDays = minusTenDays.format(dateFormat);

        Food pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateMinusTenDays).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expired 10 days ago

        LocalDate yesterday = LocalDate.now().minusDays(1);
        String dateYesterday = yesterday.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateYesterday).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expired yesterday

        LocalDate eightDaysLater = LocalDate.now().plusDays(8);
        String dateEightDaysLater = eightDaysLater.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateEightDaysLater).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expiring 8 days from today

        LocalDate twoWeeksLater = LocalDate.now().plusDays(14);
        String dateTwoWeeksLater = twoWeeksLater.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateTwoWeeksLater).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expiring 2 weeks from today
    }

    @Test
    public void getExpiringPredicate_predicateTest_nullArgument() {
        Predicate<Food> predicate = modelManager.getExpiringPredicate();
        assertThrows(NullPointerException.class, () -> predicate.test(null)); // null argument
    }

    @Test
    public void getExpiredPredicate_predicateTest_predicateReturnsTrue() {
        Predicate<Food> predicate = modelManager.getExpiredPredicate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-M-yyyy");

        LocalDate yesterday = LocalDate.now().minusDays(1);
        String dateYesterday = yesterday.format(dateFormat);

        Food pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateYesterday).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expired yesterday

        LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
        String dateTwoDaysAgo = twoDaysAgo.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateTwoDaysAgo).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expired two days ago

        LocalDate today = LocalDate.now();
        String dateToday = today.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateToday).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expiring today

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String dateTomorrow = tomorrow.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateTomorrow).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expiring 2 days from today
    }

    @Test
    public void getExpiredPredicate_predicateTest_predicateReturnsFalse() {
        Predicate<Food> predicate = modelManager.getExpiredPredicate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-M-yyyy");

        LocalDate minusTenDays = LocalDate.now().minusDays(10);
        String dateminusTenDays = minusTenDays.format(dateFormat);

        Food pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateminusTenDays).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expired 10 days ago

        LocalDate yesterday = LocalDate.now().minusDays(1);
        String dateYesterday = yesterday.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateYesterday).withQuantity("1.5").withTags("cheese").build();
        assertTrue(predicate.test(pizza)); // expired yesterday

        LocalDate eightDaysLater = LocalDate.now().plusDays(8);
        String dateEightDaysLater = eightDaysLater.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(dateEightDaysLater).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expiring 8 days from today

        LocalDate twoWeeksLater = LocalDate.now().plusDays(14);
        String datetwoWeeksLater = twoWeeksLater.format(dateFormat);

        pizza = new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate(datetwoWeeksLater).withQuantity("1.5").withTags("cheese").build();
        assertFalse(predicate.test(pizza)); // expiring 2 weeks from today
    }

    @Test
    public void getExpiredPredicate_predicateTest_nullArgument() {
        Predicate<Food> predicate = modelManager.getExpiredPredicate();
        assertThrows(NullPointerException.class, () -> predicate.test(null)); // null argument
    }

    @Test
    public void equals() {
        FoodInventory foodInventory = new FoodInventoryBuilder()
                .withFood(ANCHOVIES).withFood(BAGEL).build();
        FoodInventory differentFoodInventory = new FoodInventory();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(foodInventory, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(foodInventory, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different foodInventory -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFoodInventory, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ANCHOVIES.getDescription().fullDescription.split("\\s+");
        modelManager.updateFilteredFoodList(new DescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(foodInventory, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFoodInventoryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(foodInventory, differentUserPrefs)));
    }
}
