package seedu.address.logic.commands.calories;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CalorieAddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.DailyCalorie;

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
    public void execute_invalidCalorieInput_throwsCommandException() {
        CalorieAddCommand command = new CalorieAddCommand(new Calorie(2147483647));
        ModelStub modelStub = new ModelStubWithDailyCalorie(
                new DailyCalorie(LocalDate.of(2020, 11, 3)));

        //To tip the integer value to be too large
        modelStub.addCalories(new Calorie(1));

        assertThrows(CommandException.class,
                CalorieAddCommand.MESSAGE_FAILURE, () -> command.execute(modelStub));
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
     * Stub used to contain a single DailyCalorie.
     */
    private class ModelStubWithDailyCalorie extends ModelStub {
        private final DailyCalorie dailyCalorie;

        ModelStubWithDailyCalorie(DailyCalorie dailyCalorie) {
            requireNonNull(dailyCalorie);
            this.dailyCalorie = dailyCalorie;
        }

        @Override
        public int getCalories() {
            return dailyCalorie.getCalories();
        }

        @Override
        public void addCalories(Calorie calorie) {
            dailyCalorie.addCalories(calorie.getCalorie());
        }
    }

    /**
     * Stub that accepts any DailyCalorie added to it.
     */
    private class ModelStubAcceptingDailyCalorie extends ModelStub {
        final ArrayList<DailyCalorie> dailyCalories = new ArrayList<>();
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
