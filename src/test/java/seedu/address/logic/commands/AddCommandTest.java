package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.BidBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBidBook;
import seedu.address.model.person.Person;
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBid(Bid bid) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBidBook getBidBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Bid> getFilteredBidList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBidList(Predicate<Bid> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // ================= PROPERTY =================

        @Override
        public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPropertyBook getPropertyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProperty(Property target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePropertyByPropertyId(Id id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Property getPropertyById(Id id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(Property target, Property editedProperty) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Property> getFilteredPropertyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPropertyList(Predicate<Property> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // ================= BIDDER =================

        @Override
        public Path getBidderAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBidderAddressBookFilePath(Path bidderAddressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBidderAddressBook getBidderAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBidder(Bidder bidder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBidder(Bidder target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBidder(Bidder bidder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBidder(Bidder target, Bidder editedBidder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Bidder> getFilteredBidderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBidderList(Predicate<Bidder> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // ================= SELLER =================

        @Override
        public Path getSellerAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSellerAddressBookFilePath(Path sellerAddressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySellerAddressBook getSellerAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSeller(Seller seller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSeller(Seller target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSeller(Seller seller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSeller(Seller target, Seller editedSeller) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Seller> getFilteredSellerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSellerList(Predicate<Seller> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        // ================= MEETING =================

        @Override
        public void setMeetingManager(ReadOnlyMeetingManager meetingManager) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMeetingManager getMeetingManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(CalendarMeeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(CalendarMeeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(CalendarMeeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(CalendarMeeting target, CalendarMeeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<CalendarMeeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<CalendarMeeting> predicate) {
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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ReadOnlyBidBook getBidBook() {
            return new BidBook();
        }


    }

}
