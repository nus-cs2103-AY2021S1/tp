package seedu.address.logic.commands.consumption;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showConsumptionAtIndex;
import static seedu.address.testutil.TypicalConsumption.getTypicalWishfulShrinking;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONSUMPTION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.consumption.Consumption;

public class ListConsumptionCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ObservableList<Consumption> consump = model.getFilteredConsumptionList();
        StringBuilder builder = new StringBuilder();
        int totalCalories = 0;
        for (int i = 0; i < consump.size(); i++) {
            builder.append((i + 1) + ". " + consump.get(i).toString() + "\n");
            totalCalories += consump.get(i).getRecipe().getCalories().value;
        }
        builder.append("Total Calories: ");
        builder.append(totalCalories + " cal");
        assertCommandSuccess(new ListConsumptionCommand(), model,
                ListConsumptionCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showConsumptionAtIndex(model, INDEX_FIRST_CONSUMPTION);
        ObservableList<Consumption> consump = expectedModel.getFilteredConsumptionList();
        StringBuilder builder = new StringBuilder();
        int totalCalories = 0;
        for (int i = 0; i < consump.size(); i++) {
            builder.append((i + 1) + ". " + consump.get(i).toString() + "\n");
            totalCalories += consump.get(i).getRecipe().getCalories().value;
        }
        builder.append("Total Calories: ");
        builder.append(totalCalories + " cal");
        assertCommandSuccess(new ListConsumptionCommand(), model,
                ListConsumptionCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }
}
