package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterRoomCommand.
 */
public class FilterRoomCommandTest {
    private Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_success_single() throws CommandException {
        LocalDate startDate = LocalDate.of(2020, 10, 15);
        LocalDate endDate = LocalDate.of(2020, 10, 22);

        // Single Rooms
        int roomType1 = 1;
        FilterRoomCommand command1 = new FilterRoomCommand(startDate, endDate, roomType1);
        ArrayList<Integer> expectedRoomId1 = new ArrayList<>();
        expectedRoomId1.add(2103);
        expectedRoomId1.add(2104);
        expectedRoomId1.add(2105);
        expectedRoomId1.add(2106);
        expectedRoomId1.add(2107);
        expectedRoomId1.add(2108);
        expectedRoomId1.add(2110);
        expectedRoomId1.add(2112);
        String expectedOutput1 = model.displaySingleRooms(FXCollections.observableArrayList(expectedRoomId1));

        assertEquals(String.format(FilterRoomCommand.MESSAGE_SUCCESS, expectedOutput1),
                command1.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_success_double() throws CommandException {
        LocalDate startDate = LocalDate.of(2020, 10, 15);
        LocalDate endDate = LocalDate.of(2020, 10, 22);

        // Double Rooms
        int roomType2 = 2;
        FilterRoomCommand command2 = new FilterRoomCommand(startDate, endDate, roomType2);
        ArrayList<Integer> expectedRoomId2 = new ArrayList<>();
        expectedRoomId2.add(2113);
        expectedRoomId2.add(2114);
        expectedRoomId2.add(2115);
        expectedRoomId2.add(2116);
        expectedRoomId2.add(2117);
        expectedRoomId2.add(2120);
        expectedRoomId2.add(2122);
        String expectedOutput2 = model.displayDoubleRooms(FXCollections.observableArrayList(expectedRoomId2));
        assertEquals(String.format(FilterRoomCommand.MESSAGE_SUCCESS, expectedOutput2),
                command2.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_success_suite() throws CommandException {
        LocalDate startDate = LocalDate.of(2020, 10, 15);
        LocalDate endDate = LocalDate.of(2020, 10, 22);

        // Suite Rooms
        int roomType1 = 3;
        FilterRoomCommand command3 = new FilterRoomCommand(startDate, endDate, roomType1);
        ArrayList<Integer> expectedRoomId1 = new ArrayList<>();
        expectedRoomId1.add(2123);
        expectedRoomId1.add(2124);
        expectedRoomId1.add(2125);
        expectedRoomId1.add(2126);
        expectedRoomId1.add(2127);
        expectedRoomId1.add(2129);
        expectedRoomId1.add(2132);
        String expectedOutput3 = model.displaySuiteRooms(FXCollections.observableArrayList(expectedRoomId1));

        assertEquals(String.format(FilterRoomCommand.MESSAGE_SUCCESS, expectedOutput3),
                command3.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_success_all() throws CommandException {
        LocalDate startDate = LocalDate.of(2020, 10, 15);
        LocalDate endDate = LocalDate.of(2020, 10, 22);

        // All room types
        int roomType = 0;
        FilterRoomCommand command4 = new FilterRoomCommand(startDate, endDate, roomType);
        ArrayList<Integer> expectedRoomId4 = new ArrayList<>();
        expectedRoomId4.add(2103);
        expectedRoomId4.add(2104);
        expectedRoomId4.add(2105);
        expectedRoomId4.add(2106);
        expectedRoomId4.add(2107);
        expectedRoomId4.add(2108);
        expectedRoomId4.add(2110);
        expectedRoomId4.add(2112);
        expectedRoomId4.add(2113);
        expectedRoomId4.add(2114);
        expectedRoomId4.add(2115);
        expectedRoomId4.add(2116);
        expectedRoomId4.add(2117);
        expectedRoomId4.add(2120);
        expectedRoomId4.add(2122);
        expectedRoomId4.add(2123);
        expectedRoomId4.add(2124);
        expectedRoomId4.add(2125);
        expectedRoomId4.add(2126);
        expectedRoomId4.add(2127);
        expectedRoomId4.add(2129);
        expectedRoomId4.add(2132);
        String expectedOutput3 = model.displayRooms(FXCollections.observableArrayList(expectedRoomId4));

        assertEquals(String.format(FilterRoomCommand.MESSAGE_SUCCESS, expectedOutput3),
                command4.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        LocalDate startDate = LocalDate.of(2020, 10, 15);
        LocalDate endDate = LocalDate.of(2020, 10, 22);
        int roomType = 1;

        LocalDate wrongStart = LocalDate.of(2020, 10, 20);
        LocalDate wrongEnd = LocalDate.of(2020, 10, 15);
        int wrongType = 2;
        FilterRoomCommand command = new FilterRoomCommand(startDate, endDate, roomType);

        // same values -> returns true
        FilterRoomCommand commandWithSameValues = new FilterRoomCommand(startDate, endDate, roomType);
        assertTrue(command.equals(commandWithSameValues));

        // same object -> return true
        assertTrue(command.equals(command));

        // null -> return false
        assertFalse(command.equals(null));

        // different types -> return false
        assertFalse(command.equals(new ClearCommand()));

        // different start Date -> return false
        assertFalse(command.equals(new FilterRoomCommand(wrongStart, endDate, roomType)));

        // different end Date -> return false
        assertFalse(command.equals(new FilterRoomCommand(startDate, wrongEnd, roomType)));

        // different roomType -> return false
        assertFalse(command.equals(new FilterRoomCommand(startDate, endDate, wrongType)));
    }

}
