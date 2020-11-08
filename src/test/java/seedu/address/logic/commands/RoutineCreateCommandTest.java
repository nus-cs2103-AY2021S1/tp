package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.routines.RoutineCreateCommand;
import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.routine.Routine;

public class RoutineCreateCommandTest {
    @Test
    public void constructor_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoutineCreateCommand(null));
    }

    @Test
    public void execute_routineAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRoutines modelStub = new ModelStubAcceptingRoutines();

        CommandResult commandResult = new RoutineCreateCommand(LEG_DAY).execute(modelStub);

        assertEquals(String.format(RoutineCreateCommand.MESSAGE_SUCCESS, LEG_DAY), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(LEG_DAY), modelStub.routinesAdded);
    }

    @Test
    public void execute_duplicateRoutine_throwsCommandException() {
        RoutineCreateCommand command = new RoutineCreateCommand(LEG_DAY);
        ModelStub modelStub = new ModelStubWithRoutine(LEG_DAY);

        assertThrows(CommandException.class,
                RoutineCreateCommand.MESSAGE_DUPLICATE_ROUTINE, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        RoutineCreateCommand addLegDayCommand = new RoutineCreateCommand(LEG_DAY);
        RoutineCreateCommand addUpperBodyCommand = new RoutineCreateCommand(UPPER_BODY);

        // same object -> returns true
        assertTrue(addLegDayCommand.equals(addLegDayCommand));

        // same values -> returns true
        RoutineCreateCommand addLegDayCommandCopy = new RoutineCreateCommand(LEG_DAY);
        assertTrue(addLegDayCommand.equals(addLegDayCommandCopy));

        // different types -> returns false
        assertFalse(addLegDayCommand.equals(1));

        // null -> returns false
        assertFalse(addLegDayCommand.equals(null));

        // different routines -> returns false
        assertFalse(addLegDayCommand.equals(addUpperBodyCommand));
    }

    /**
     * Stub used to contain a single Routine.
     */
    private class ModelStubWithRoutine extends ModelStub {
        private final Routine routine;

        ModelStubWithRoutine(Routine routine) {
            requireNonNull(routine);
            this.routine = routine;
        }

        @Override
        public boolean hasRoutine(Routine routine) {
            requireNonNull(routine);
            return this.routine.isSameActivity(routine);
        }
    }

    /**
     * Stub that accepts any Routine added to it.
     */
    private class ModelStubAcceptingRoutines extends ModelStub {
        final ArrayList<Routine> routinesAdded = new ArrayList<>();

        @Override
        public boolean hasRoutine(Routine routine) {
            requireNonNull(routine);
            return routinesAdded.stream().anyMatch(routine::isSameActivity);
        }

        @Override
        public void addRoutine(Routine routine) {
            requireNonNull(routine);
            routinesAdded.add(routine);
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }
    }

}
