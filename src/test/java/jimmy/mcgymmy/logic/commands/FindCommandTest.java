package jimmy.mcgymmy.logic.commands;

import static jimmy.mcgymmy.testutil.TypicalFoods.CHICKEN_RICE;
import static jimmy.mcgymmy.testutil.TypicalFoods.CRISPY_FRIED_FISH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.logic.parser.CommandParserTestUtil;
import jimmy.mcgymmy.logic.predicate.FoodContainsKeywordsPredicate;
import jimmy.mcgymmy.logic.predicate.NameContainsKeywordsPredicate;
import jimmy.mcgymmy.logic.predicate.TagContainsKeywordsPredicate;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.testutil.TypicalFoods;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(TypicalFoods.getTypicalMcGymmy(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalFoods.getTypicalMcGymmy(), new UserPrefs());

    @Test
    public void execute_zeroKeywords_noFoodFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FOOD_LISTED_OVERVIEW, 0);
        FoodContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand();
        command.setParameters(
                new CommandParserTestUtil.OptionalParameterStub<>("", predicate),
                new CommandParserTestUtil.OptionalParameterStub<>("n"),
                new CommandParserTestUtil.OptionalParameterStub<>("t"),
                new CommandParserTestUtil.OptionalParameterStub<>("d"));
        expectedModel.updateFilteredFoodList(predicate);
        ObservableList<Food> curr = expectedModel.getFilteredFoodList();
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFoodList());
    }

    @Test
    public void execute_multipleKeywords_multipleFoodsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FOOD_LISTED_OVERVIEW, 2);
        FoodContainsKeywordsPredicate predicate = preparePredicate("CHICKEN fish");
        FindCommand command = new FindCommand();
        command.setParameters(
                new CommandParserTestUtil.OptionalParameterStub<>("", predicate),
                new CommandParserTestUtil.OptionalParameterStub<>("n"),
                new CommandParserTestUtil.OptionalParameterStub<>("t"),
                new CommandParserTestUtil.OptionalParameterStub<>("d"));
        expectedModel.updateFilteredFoodList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHICKEN_RICE, CRISPY_FRIED_FISH),
                model.getFilteredFoodList());
    }

    /**
     * Parses {@code userInput} into a {@code FoodContainsKeywordsPredicate}.
     */
    private FoodContainsKeywordsPredicate preparePredicate(String userInput) {
        return new FoodContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
