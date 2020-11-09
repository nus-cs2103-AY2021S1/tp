package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBookingWithId;
import static seedu.address.testutil.TypicalBookings.BOOKING_ID_1;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListBookingCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonBook(), new UserPrefs(), getTypicalRoomBook(),
                getTypicalBookingBook(), getTypicalRoomServiceBook());
        expectedModel = new ModelManager(model.getPersonBook(), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showBookingWithId(model, BOOKING_ID_1);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW,
                        expectedModel.getFilteredBookingList().size()),
                false, false, false, true);
        assertCommandSuccess(new ListBookingCommand(), model, expectedCommandResult, expectedModel);
    }
}
