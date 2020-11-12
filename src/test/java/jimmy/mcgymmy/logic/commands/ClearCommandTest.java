package jimmy.mcgymmy.logic.commands;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.food.Name;
import jimmy.mcgymmy.testutil.FoodBuilder;
import jimmy.mcgymmy.testutil.TypicalFoods;


public class ClearCommandTest {

    @Test
    public void execute_emptyMcGymmy_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_oneFoodMcGymmy_success() throws IllegalValueException {
        Model model = new ModelManager();
        model.addFood(new FoodBuilder().withName(new Name("Pizza")).build());
        Model expectedModel = new ModelManager();
        ClearCommand clearCommand = new ClearCommand();
        CommandTestUtil.assertCommandSuccess(clearCommand, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_partialClearMcGymmy_success() {
        Model model = new ModelManager();
        model.addFood(TypicalFoods.getApple());
        model.addFood(TypicalFoods.getCrispyFriedFish());
        model.updateFilteredFoodList((food) -> food
                .getName()
                .fullName
                .contains(TypicalFoods.getApple().getName().fullName));

        Model expectedModel = new ModelManager();
        expectedModel.addFood(TypicalFoods.getCrispyFriedFish());
        expectedModel.updateFilteredFoodList((food) -> food.getName()
                .fullName.contains(TypicalFoods.getApple().getName().fullName));


        ClearCommand clearCommand = new ClearCommand();
        CommandTestUtil.assertCommandSuccess(clearCommand, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }


}
