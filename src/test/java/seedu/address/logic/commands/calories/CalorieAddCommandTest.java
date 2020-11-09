package seedu.address.logic.commands.calories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.calorie.CalorieAddCommand;
import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.calorie.Calorie;

public class CalorieAddCommandTest {

    @Test
    public void execute_dailyCalorieAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingDailyCalorie modelStub = new ModelStubAcceptingDailyCalorie();

        Calorie calorie = new Calorie(1000);
        CommandResult commandResult = new CalorieAddCommand(calorie).execute(modelStub);

        assertEquals(String.format(CalorieAddCommand.MESSAGE_SUCCESS, calorie) + modelStub.getCalories(),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        CalorieAddCommand firstCommand = new CalorieAddCommand(new Calorie(10));
        CalorieAddCommand secondCommand = new CalorieAddCommand(new Calorie(20));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        CalorieAddCommand firstCommandCopy = new CalorieAddCommand(new Calorie(10));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different DailyCalorie -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Stub that accepts any DailyCalorie added to it.
     */
    private class ModelStubAcceptingDailyCalorie extends ModelStub {
        private int calories = 0;

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }

        @Override
        public int getCalories() {
            return calories;
        }

        @Override
        public void addCalories(Calorie calorie) {
            calories += calorie.getCalorie();
        }
    }
}
