package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_FOODS_LISTED_OVERVIEW;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.testutil.TypicalFood.ANCHOVIES;
import static seedu.simplykitchen.testutil.TypicalFood.BAGEL;
import static seedu.simplykitchen.testutil.TypicalFood.CARROT_CAKE;
import static seedu.simplykitchen.testutil.TypicalFood.DARK_CHOCOLATE;
import static seedu.simplykitchen.testutil.TypicalFood.EGGS;
import static seedu.simplykitchen.testutil.TypicalFood.FRENCH_FRIES;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.model.UserPrefs;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.ExpiryDateSearchPredicate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.PrioritySearchPredicate;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.model.tag.TagSearchPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalFoodInventory(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFoodInventory(), new UserPrefs());

    @Test
    public void equals() {
        Optional<DescriptionContainsKeywordsPredicate> firstDescriptionPredicate =
                Optional.of(new DescriptionContainsKeywordsPredicate(Collections.singletonList("first")));
        Optional<ExpiryDateSearchPredicate> firstExpiryDatePredicate =
                Optional.of(new ExpiryDateSearchPredicate("01-01-2021"));
        Optional<PrioritySearchPredicate> firstPriorityPredicate =
                Optional.of(new PrioritySearchPredicate(Priority.Level.LOW));
        HashSet<Tag> firstSetOfTags = new HashSet<>();
        firstSetOfTags.add(new Tag("first tag"));
        Optional<TagSearchPredicate> firstTagPredicate =
                Optional.of(new TagSearchPredicate(firstSetOfTags));

        Optional<DescriptionContainsKeywordsPredicate> secondDescriptionPredicate =
                Optional.of(new DescriptionContainsKeywordsPredicate(Collections.singletonList("second")));
        Optional<ExpiryDateSearchPredicate> secondExpiryDatePredicate =
                Optional.of(new ExpiryDateSearchPredicate("01-02-2021"));
        Optional<PrioritySearchPredicate> secondPriorityPredicate =
                Optional.of(new PrioritySearchPredicate(Priority.Level.MEDIUM));
        HashSet<Tag> secondSetOfTags = new HashSet<>();
        secondSetOfTags.add(new Tag("second tag"));
        Optional<TagSearchPredicate> secondTagPredicate =
                Optional.of(new TagSearchPredicate(secondSetOfTags));

        FindCommand findFirstCommand = new FindCommand(firstDescriptionPredicate, firstPriorityPredicate,
                firstExpiryDatePredicate, firstTagPredicate);
        FindCommand findSecondCommand = new FindCommand(secondDescriptionPredicate, secondPriorityPredicate,
                secondExpiryDatePredicate, secondTagPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstDescriptionPredicate, firstPriorityPredicate,
                firstExpiryDatePredicate, firstTagPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different food -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroDescriptionKeywords_noFoodFound() {
        String expectedMessage = String.format(MESSAGE_FOODS_LISTED_OVERVIEW, 0);
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate =
                Optional.of(preparePredicate(" "));
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();

        FindCommand command = new FindCommand(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate);
        expectedModel.updateFilteredFoodList(combinePredicates(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFoodList());
    }

    @Test
    public void execute_multipleDescriptionKeywords_multipleFoodsFound() {
        String expectedMessage = String.format(MESSAGE_FOODS_LISTED_OVERVIEW, 3);
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate =
                Optional.of(preparePredicate("Cake Dark Eggs"));
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();

        FindCommand command = new FindCommand(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate);
        expectedModel.updateFilteredFoodList(combinePredicates(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARROT_CAKE, DARK_CHOCOLATE, EGGS), model.getFilteredFoodList());
    }

    @Test
    public void execute_prioritySearch_multipleFoodsFound() {
        String expectedMessage = String.format(MESSAGE_FOODS_LISTED_OVERVIEW, 2);
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate = Optional.empty();
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate =
                Optional.of(new PrioritySearchPredicate(Priority.Level.HIGH));
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();

        FindCommand command = new FindCommand(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate);
        expectedModel.updateFilteredFoodList(combinePredicates(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DARK_CHOCOLATE, EGGS), model.getFilteredFoodList());
    }

    @Test
    public void execute_expiryDateSearch_oneFoodItemFound() {
        String expectedMessage = String.format(MESSAGE_FOODS_LISTED_OVERVIEW, 1);
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate = Optional.empty();
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate =
                Optional.of(new ExpiryDateSearchPredicate("31-01-2020"));
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        Optional<TagSearchPredicate> tagPredicate = Optional.empty();

        FindCommand command = new FindCommand(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate);
        expectedModel.updateFilteredFoodList(combinePredicates(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FRENCH_FRIES), model.getFilteredFoodList());
    }

    @Test
    public void execute_multipleTagsSearch_multipleFoodsFound() {
        String expectedMessage = String.format(MESSAGE_FOODS_LISTED_OVERVIEW, 2);
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate = Optional.empty();
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate = Optional.empty();
        Optional<PrioritySearchPredicate> priorityPredicate = Optional.empty();
        HashSet<Tag> setOfTags = new HashSet<>();
        setOfTags.add(new Tag("sugar-free"));
        setOfTags.add(new Tag("fresh water"));
        Optional<TagSearchPredicate> tagPredicate = Optional.of(new TagSearchPredicate(setOfTags));

        FindCommand command = new FindCommand(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate);
        expectedModel.updateFilteredFoodList(combinePredicates(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ANCHOVIES, BAGEL), model.getFilteredFoodList());
    }

    @Test
    public void execute_multipleSearchQueries_oneFoodItemFound() {
        String expectedMessage = String.format(MESSAGE_FOODS_LISTED_OVERVIEW, 1);
        Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate =
                Optional.of(preparePredicate("Bagel"));
        Optional<ExpiryDateSearchPredicate> expiryDatePredicate =
                Optional.of(new ExpiryDateSearchPredicate("01-02-2020"));
        Optional<PrioritySearchPredicate> priorityPredicate =
                Optional.of(new PrioritySearchPredicate(Priority.Level.LOW));
        HashSet<Tag> setOfTags = new HashSet<>();
        setOfTags.add(new Tag("cheese"));
        Optional<TagSearchPredicate> tagPredicate = Optional.of(new TagSearchPredicate(setOfTags));

        FindCommand command = new FindCommand(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate);
        expectedModel.updateFilteredFoodList(combinePredicates(descriptionPredicate, priorityPredicate,
                expiryDatePredicate, tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BAGEL), model.getFilteredFoodList());
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Combines all of the predicate into one.
     */
    private Predicate<Food> combinePredicates(Optional<DescriptionContainsKeywordsPredicate> descriptionPredicate,
            Optional<PrioritySearchPredicate> priorityPredicate,
            Optional<ExpiryDateSearchPredicate> expiryDatePredicate, Optional<TagSearchPredicate> tagsPredicate) {

        Predicate<Food> predicate = food -> true;
        if (descriptionPredicate.isPresent()) {
            predicate = predicate.and(descriptionPredicate.get());
        }

        if (priorityPredicate.isPresent()) {
            predicate = predicate.and(priorityPredicate.get());
        }

        if (expiryDatePredicate.isPresent()) {
            predicate = predicate.and(expiryDatePredicate.get());
        }

        if (tagsPredicate.isPresent()) {
            predicate = predicate.and(tagsPredicate.get());
        }

        return predicate;
    }
}
