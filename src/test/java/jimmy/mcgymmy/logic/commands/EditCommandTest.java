package jimmy.mcgymmy.logic.commands;

import static jimmy.mcgymmy.logic.commands.CommandTestUtil.*;
import static jimmy.mcgymmy.testutil.TypicalFoods.getTypicalMcGymmy;
import static jimmy.mcgymmy.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static jimmy.mcgymmy.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.logic.parser.CommandParserTestUtil;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.testutil.FoodBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final String VALID_NAME_BOB = "Robert Donald";
    private static final String VALID_PHONE_BOB = "99999999";
    private Model model = new ModelManager(getTypicalMcGymmy(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Food editedFood = new FoodBuilder().withTags("friends").build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n", editedFood.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p", editedFood.getProtein()),
            new CommandParserTestUtil.OptionalParameterStub<>("f", editedFood.getFat()),
            new CommandParserTestUtil.OptionalParameterStub<>("c")
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new McGymmy(model.getMcGymmy()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastPerson.getZeroBased());

        FoodBuilder personInList = new FoodBuilder(lastFood);
        Food editedFood = personInList.withName(VALID_NAME_BOB).withProtein(VALID_PHONE_BOB).build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", indexLastPerson),
            new CommandParserTestUtil.OptionalParameterStub<>("n", editedFood.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p", editedFood.getProtein()),
            new CommandParserTestUtil.OptionalParameterStub<>("f"),
            new CommandParserTestUtil.OptionalParameterStub<>("c")
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new McGymmy(model.getMcGymmy()), new UserPrefs());
        expectedModel.setFood(lastFood, editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n"),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("f"),
            new CommandParserTestUtil.OptionalParameterStub<>("c")
        );

        Food editedFood = model.getFilteredFoodList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new McGymmy(model.getMcGymmy()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Food foodInFilteredList = model.getFilteredFoodList().get(INDEX_FIRST_PERSON.getZeroBased());
        Food editedFood = new FoodBuilder(foodInFilteredList).withName(VALID_NAME_BOB).build();

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n", editedFood.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("f"),
            new CommandParserTestUtil.OptionalParameterStub<>("c")
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new McGymmy(model.getMcGymmy()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Food firstFood = model.getFilteredFoodList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_SECOND_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<>("n", firstFood.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p", firstFood.getProtein()),
            new CommandParserTestUtil.OptionalParameterStub<>("c"),
            new CommandParserTestUtil.OptionalParameterStub<>("f")
        );


        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit food in filtered list into a duplicate in address book
        Food foodInList =
            model.getMcGymmy().getFoodList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", INDEX_FIRST_PERSON),
            new CommandParserTestUtil.OptionalParameterStub<String>("n", foodInList.getName()),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("c"),
            new CommandParserTestUtil.OptionalParameterStub<>("f")
        );

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", outOfBoundIndex),
            new CommandParserTestUtil.OptionalParameterStub<String>("n", VALID_NAME_BOB),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("c"),
            new CommandParserTestUtil.OptionalParameterStub<>("f")
        );

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMcGymmy().getFoodList().size());

        EditCommand editCommand = new EditCommand();
        editCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", outOfBoundIndex),
            new CommandParserTestUtil.OptionalParameterStub<String>("n", VALID_NAME_BOB),
            new CommandParserTestUtil.OptionalParameterStub<>("p"),
            new CommandParserTestUtil.OptionalParameterStub<>("c"),
            new CommandParserTestUtil.OptionalParameterStub<>("f")
        );

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


}
