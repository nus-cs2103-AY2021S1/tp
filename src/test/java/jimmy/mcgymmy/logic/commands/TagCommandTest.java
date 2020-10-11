package jimmy.mcgymmy.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.logic.parser.CommandParserTestUtil;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.tag.Tag;
import jimmy.mcgymmy.testutil.FoodBuilder;
import jimmy.mcgymmy.testutil.TypicalFoods;
import jimmy.mcgymmy.testutil.TypicalIndexes;

class TagCommandTest {

    private Model model = new ModelManager(TypicalFoods.getTypicalMcGymmy(), new UserPrefs());
    private final String tag_1 = "hello";
    private final String tag_2 = "goodbye";
    private final String tag_3 = "seeYouAgain";

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index index = TypicalIndexes.INDEX_FIRST_FOOD;
        Food foodToTag = model.getFilteredFoodList().get(index.getZeroBased());
        TagCommand tagCommand = new TagCommand();
        tagCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_FIRST_FOOD),
                new CommandParserTestUtil.ParameterStub<>("t", new Tag(tag_1))
        );

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, tag_1);

        ModelManager expectedModel = new ModelManager(model.getMcGymmy(), new UserPrefs());
        expectedModel.setFood(index, new FoodBuilder(foodToTag).withTags(tag_1).build());

        CommandTestUtil.assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        TagCommand tagCommand = new TagCommand();
        tagCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", outOfBoundIndex),
                new CommandParserTestUtil.ParameterStub<>("t", new Tag(tag_1))
        );
        CommandTestUtil.assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {

        CommandTestUtil.showFoodAtIndex(model, TypicalIndexes.INDEX_FIRST_FOOD);

        Food foodToTag = model.getFilteredFoodList().get(TypicalIndexes.INDEX_FIRST_FOOD.getZeroBased());
        TagCommand tagCommand = new TagCommand();
        tagCommand.setParameters(
                new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_FIRST_FOOD),
                new CommandParserTestUtil.ParameterStub<>("t", new Tag(tag_3))
        );

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, tag_3);
        ModelManager expectedModel = new ModelManager(model.getMcGymmy(), new UserPrefs());
        expectedModel.setFood(Index.fromZeroBased(0),
                new FoodBuilder(foodToTag).withTags(tag_3).build());

        CommandTestUtil.assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Index index = TypicalIndexes.INDEX_FIRST_FOOD;
        Food foodToTag = model.getFilteredFoodList().get(index.getZeroBased());
        TagCommand tagCommand = new TagCommand();
        tagCommand.setParameters(
            new CommandParserTestUtil.ParameterStub<>("", TypicalIndexes.INDEX_FIRST_FOOD),
            new CommandParserTestUtil.ParameterStub<>("t", new Tag(tag_2))
        );

        String expectedMessage = String.format(TagCommand.MESSAGE_SUCCESS, tag_2);

        ModelManager expectedModel = new ModelManager(model.getMcGymmy(), new UserPrefs());
        expectedModel.setFood(index, new FoodBuilder(foodToTag).withTags(tag_2).build());

        // add tag for the first time
        CommandTestUtil.assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
        CommandTestUtil.assertCommandFailure(tagCommand, model,
            String.format(TagCommand.MESSAGE_DUPLICATE_TAG, tag_2, foodToTag.getName().fullName));
    }
}
