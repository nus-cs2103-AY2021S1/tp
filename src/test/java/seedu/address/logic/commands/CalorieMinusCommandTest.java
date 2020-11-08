package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.DailyCalorie;

public class CalorieMinusCommandTest {

    @Test
    public void execute_dailyCalorieAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingDailyCalorie modelStub = new ModelStubAcceptingDailyCalorie();

        Calorie calorie = new Calorie(1000);
        CommandResult commandResult = new CalorieMinusCommand(calorie).execute(modelStub);

        assertEquals(String.format(CalorieMinusCommand.MESSAGE_SUCCESS, calorie) + modelStub.getCalories(),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_twoDailyCalorieWithSameDate_throwsCommandException() {
        CalorieMinusCommand command = new CalorieMinusCommand(new Calorie(1000));
        ModelStub modelStub = new ModelStubWithDailyCalorie(
                new DailyCalorie(LocalDate.now()));

        modelStub.addCalories(new Calorie(1001));

        //Assertion Error is thrown because there are 2 conflicting DailyCalorie entries with the same LocalDate.
        //Under normal circumstances, this will never occur.
        assertThrows(AssertionError.class, () -> command.execute(modelStub));
    }

    @Test
    public void execute_invalidCalorieInput_throwsCommandException() {
        CalorieMinusCommand command = new CalorieMinusCommand(new Calorie(1));
        ModelStub modelStub = new ModelStubWithDailyCalorie(
                new DailyCalorie(LocalDate.of(2020, 11, 3)));

        assertThrows(CommandException.class,
                CalorieMinusCommand.MESSAGE_FAILURE, () -> command.execute(modelStub));
    }
    @Test
    public void equals() {
        CalorieMinusCommand firstCommand = new CalorieMinusCommand(new Calorie(10));
        CalorieMinusCommand secondCommand = new CalorieMinusCommand(new Calorie(20));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        CalorieMinusCommand firstCommandCopy = new CalorieMinusCommand(new Calorie(10));
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
        private int calories = 1001;

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }

        @Override
        public int getCalories() {
            return calories;
        }

        @Override
        public void minusCalories(Calorie calorie) {
            calories -= calorie.getCalorie();
        }
    }
}
