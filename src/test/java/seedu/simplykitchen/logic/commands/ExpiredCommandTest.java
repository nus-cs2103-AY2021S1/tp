package seedu.simplykitchen.logic.commands;

import static seedu.simplykitchen.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.simplykitchen.logic.commands.ExpiredCommand.NO_EXPIRED_ITEMS;
import static seedu.simplykitchen.logic.commands.ExpiredCommand.SHOWING_EXPIRED_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.ModelManager;
import seedu.simplykitchen.testutil.FoodBuilder;

public class ExpiredCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_expired_success() {
        CommandResult expectedCommandResultWithNoExpiredItems =
                new CommandResult(NO_EXPIRED_ITEMS, false, false, false);
        assertCommandSuccess(new ExpiredCommand(), model, expectedCommandResultWithNoExpiredItems, expectedModel);

        model.addFood(new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate("1-1-2020").withQuantity("1.5").withTags("cheese").build());
        expectedModel.addFood(new FoodBuilder().withDescription("Pizza").withPriority("low")
                .withExpiryDate("1-1-2020").withQuantity("1.5").withTags("cheese").build());
        CommandResult expectedCommandResultWithExpiredItems =
                new CommandResult(SHOWING_EXPIRED_MESSAGE, true, false, false);
        assertCommandSuccess(new ExpiredCommand(), model, expectedCommandResultWithExpiredItems, expectedModel);
    }
}
