package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;
import seedu.address.model.roomservice.RoomService;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPersonBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getBookingBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookingBookFilePath(Path bookingBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonBook(ReadOnlyPersonBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPersonBook getPersonBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithId(Integer id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonWithId(Integer id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoom(Room r) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRooms(List<Room> rooms) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Integer> getAvailableRooms(ObservableList<Integer> unavailableRooms) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void resetData(ReadOnlyRoomBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyBookingBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String displayRooms(ObservableList<Integer> rooms) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String displaySingleRooms(ObservableList<Integer> rooms) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String displayDoubleRooms(ObservableList<Integer> rooms) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String displaySuiteRooms(ObservableList<Integer> rooms) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoom(int roomId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Room getRoom(Integer roomId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRoomBook getRoomBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBooking(Booking b) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookings(List<Booking> bookings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBooking(Booking target, Booking editedBooking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Integer> getUnavailableRooms(LocalDate startDate, LocalDate endDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookingBook(ReadOnlyBookingBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBooking(Booking target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBookingByPersonId(Integer personId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBookingWithId(Integer roomId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Booking getBookingWithId(Integer id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBookingBook getBookingBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookingInactive(int roomId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookingActive(int bookingId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Booking> getFilteredBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBookingList(Predicate<Booking> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== RoomServiceBook ================================================================================
        @Override
        public void addRoomService(RoomService rs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<RoomService> getRoomServicesForBooking(Integer bookingId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRoomServiceBook getRoomServiceBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyPersonBook getPersonBook() {
            return new PersonBook();
        }
    }

}
