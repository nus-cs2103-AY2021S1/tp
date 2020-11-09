package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.*;

public class ClearCommandTest {

    @Test
    public void execute_emptyPersonBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPersonBook_success() {
        Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(),
                getTypicalRoomBook(), getTypicalBookingBook(), getTypicalRoomServiceBook());
        Model expectedModel = new ModelManager(getTypicalPersonBook(), new UserPrefs(),
                model.getRoomBook(), model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.setPersonBook(new PersonBook());
        expectedModel.setBookingBook(new BookingBook());
        expectedModel.setRoomServiceBook(new RoomServiceBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
