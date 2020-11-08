package seedu.flashcard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashcard.logic.commands.FilterCommand.MESSAGE_NO_FLASHCARDS_MATCHING_FIELDS;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_2;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_3;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_6;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.CategoryEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.FavouriteEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.MultipleFieldsEqualKeywordsPredicate;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.flashcard.RatingEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.TagsEqualKeywordsPredicate;
import seedu.flashcard.testutil.TypicalFlashcards;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(TypicalFlashcards.getTypicalFlashcardDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalFlashcards.getTypicalFlashcardDeck(), new UserPrefs());

    private CategoryEqualsKeywordsPredicate nullCategoryPredicate =
            new CategoryEqualsKeywordsPredicate(null);
    private RatingEqualsKeywordsPredicate nullRatingPredicate =
            new RatingEqualsKeywordsPredicate(null);
    private FavouriteEqualsKeywordsPredicate nullFavouritePredicate =
            new FavouriteEqualsKeywordsPredicate(null);
    private TagsEqualKeywordsPredicate nullTagsPredicate =
            new TagsEqualKeywordsPredicate(null);

    @Test
    public void equals() {
        CategoryEqualsKeywordsPredicate firstCategoryPredicate =
                new CategoryEqualsKeywordsPredicate(new Category("first"));
        CategoryEqualsKeywordsPredicate secondCategoryPredicate =
                new CategoryEqualsKeywordsPredicate(new Category("second"));
        RatingEqualsKeywordsPredicate firstRatingPredicate =
                new RatingEqualsKeywordsPredicate(new Rating("2"));
        RatingEqualsKeywordsPredicate secondRatingPredicate =
                new RatingEqualsKeywordsPredicate(new Rating("3"));
        FavouriteEqualsKeywordsPredicate firstFavouritePredicate =
                new FavouriteEqualsKeywordsPredicate(true);
        FavouriteEqualsKeywordsPredicate secondFavouritePredicate =
                new FavouriteEqualsKeywordsPredicate(false);
        TagsEqualKeywordsPredicate firstTagsPredicate =
                new TagsEqualKeywordsPredicate(null);
        TagsEqualKeywordsPredicate secondTagsPredicate =
                new TagsEqualKeywordsPredicate(null);


        FilterCommand filterFirstCommand = new FilterCommand(firstCategoryPredicate, firstRatingPredicate,
                firstFavouritePredicate, firstTagsPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondCategoryPredicate, secondRatingPredicate,
                secondFavouritePredicate, secondTagsPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstCategoryPredicate, firstRatingPredicate,
                firstFavouritePredicate, firstTagsPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_oneKeyword_noFlashcardFound() {
        CategoryEqualsKeywordsPredicate categoryPredicate =
                new CategoryEqualsKeywordsPredicate(new Category("nonexistent"));
        MultipleFieldsEqualKeywordsPredicate combinedPredicate =
                new MultipleFieldsEqualKeywordsPredicate(categoryPredicate, nullRatingPredicate,
                        nullFavouritePredicate, nullTagsPredicate);
        String expectedMessage = MESSAGE_NO_FLASHCARDS_MATCHING_FIELDS;
        FilterCommand command = new FilterCommand(categoryPredicate, nullRatingPredicate, nullFavouritePredicate,
                nullTagsPredicate);
        expectedModel.updateFilteredFlashcardList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_flashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 1);
        CategoryEqualsKeywordsPredicate categoryPredicate = new CategoryEqualsKeywordsPredicate(new Category("SDLC"));
        RatingEqualsKeywordsPredicate ratingPredicate = new RatingEqualsKeywordsPredicate(new Rating("2"));
        MultipleFieldsEqualKeywordsPredicate combinedPredicate =
                new MultipleFieldsEqualKeywordsPredicate(categoryPredicate, nullRatingPredicate,
                        nullFavouritePredicate, nullTagsPredicate);
        FilterCommand command = new FilterCommand(categoryPredicate, ratingPredicate,
                nullFavouritePredicate, nullTagsPredicate);
        expectedModel.updateFilteredFlashcardList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_oneKeyword_multipleFlashcardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        CategoryEqualsKeywordsPredicate categoryPredicate =
                new CategoryEqualsKeywordsPredicate(new Category("general"));
        MultipleFieldsEqualKeywordsPredicate combinedPredicate =
                new MultipleFieldsEqualKeywordsPredicate(categoryPredicate, nullRatingPredicate,
                        nullFavouritePredicate, nullTagsPredicate);
        FilterCommand command = new FilterCommand(categoryPredicate, nullRatingPredicate,
                nullFavouritePredicate, nullTagsPredicate);
        expectedModel.updateFilteredFlashcardList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_3, FLASHCARD_6), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_lowercaseKeyword_success() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 1);
        CategoryEqualsKeywordsPredicate categoryPredicate = new CategoryEqualsKeywordsPredicate(new Category("sdlc"));
        FilterCommand command = new FilterCommand(categoryPredicate, nullRatingPredicate,
                nullFavouritePredicate, nullTagsPredicate);
        MultipleFieldsEqualKeywordsPredicate combinedPredicate =
                new MultipleFieldsEqualKeywordsPredicate(categoryPredicate, nullRatingPredicate,
                        nullFavouritePredicate, nullTagsPredicate);
        expectedModel.updateFilteredFlashcardList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywordsUppercaseKeyword_success() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 1);
        CategoryEqualsKeywordsPredicate categoryPredicate =
                new CategoryEqualsKeywordsPredicate(new Category("REVISION HISTORY"));
        FavouriteEqualsKeywordsPredicate favouritePredicate =
                new FavouriteEqualsKeywordsPredicate(true);
        FilterCommand command = new FilterCommand(categoryPredicate, nullRatingPredicate,
                favouritePredicate, nullTagsPredicate);
        MultipleFieldsEqualKeywordsPredicate combinedPredicate =
                new MultipleFieldsEqualKeywordsPredicate(categoryPredicate, nullRatingPredicate,
                        favouritePredicate, nullTagsPredicate);
        expectedModel.updateFilteredFlashcardList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_2), model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code CategoryEqualsKeywordsPredicate}.
     */
    private CategoryEqualsKeywordsPredicate preparePredicate(String userInput) {
        Category category = new Category(userInput);
        return new CategoryEqualsKeywordsPredicate(category);
    }
}
