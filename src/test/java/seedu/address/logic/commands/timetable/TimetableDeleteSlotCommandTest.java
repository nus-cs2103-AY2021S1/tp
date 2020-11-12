package seedu.address.logic.commands.timetable;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1800;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.MA1101R;
import static seedu.address.testutil.TypicalSlots.getTypicalFitNus;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Slot;

public class TimetableDeleteSlotCommandTest {

    public static final String MESSAGE_DELETE_SLOT_SUCCESS = "Deleted Slot: %1$s";
    public static final String MESSAGE_MISSING_SLOT = "This slot does not exist in your timetable.";

    private static final Model typicalModel = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void constructor_nullSlot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimetableDeleteSlotCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        TimetableDeleteSlotCommand command = new TimetableDeleteSlotCommand(new Slot(Day.MONDAY, DURATION_1600_1800));
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_slotDoesNotExist_throwsCommandException() {
        TimetableDeleteSlotCommand command = new TimetableDeleteSlotCommand(new Slot(Day.MONDAY, DURATION_1600_1800));
        assertCommandFailure(command, typicalModel, MESSAGE_MISSING_SLOT);
    }

    @Test
    public void execute_validSlot_success() {
        Slot slot = new Slot(MA1101R, Day.THURSDAY, DURATION_1600_1800);
        TimetableDeleteSlotCommand command = new TimetableDeleteSlotCommand(slot);

        Model expectedModel = new ModelManager(typicalModel.getFitNus(), new UserPrefs());
        expectedModel.deleteSlotFromTimetable(slot);
        String expectedMessage = String.format(MESSAGE_DELETE_SLOT_SUCCESS, slot);

        assertCommandSuccess(command, typicalModel, expectedMessage, expectedModel);
    }
}
