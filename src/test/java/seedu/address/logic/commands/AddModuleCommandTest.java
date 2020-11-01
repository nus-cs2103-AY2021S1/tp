package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class AddModuleCommandTest {

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddModuleCommand(null, null));
    }

    @Test
    public void equals() {
        Set<Person> dummyMembers = new HashSet<>();
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        dummyMembers.add(alice);
        dummyMembers.add(bob);
        Module cs2103 = new ModuleBuilder().withName("CS2103").withMembers(dummyMembers).build();
        Module cs3234 = new ModuleBuilder().withName("CS3234").withMembers(dummyMembers).build();
        AddModuleCommand moduleCommand1 = new AddModuleCommand(cs2103);
        AddModuleCommand moduleCommand2 = new AddModuleCommand(cs3234);

        // same object -> returns true
        assertTrue(moduleCommand1.equals(moduleCommand1));

        // same values -> returns true
        AddModuleCommand moduleCommand1Copy = new AddModuleCommand(cs2103);
        assertTrue(moduleCommand1.equals(moduleCommand1Copy));

        // different types -> returns false
        assertFalse(moduleCommand1.equals(1));

        // null -> returns false
        assertFalse(moduleCommand1.equals(null));

        // different meeting -> returns false
        assertFalse(moduleCommand1.equals(moduleCommand2));
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
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonName(Name name) {
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
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getUpdatedFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getUpdatedFilteredPersonList(Predicate<Person> predicate,
                                                                   List<ModuleName> modules) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBook(ReadOnlyMeetingBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Meeting getSelectedMeeting() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonInMeetingBook(Person ...persons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeetingName(MeetingName meetingName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting targetMeeting) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public Path getMeetingBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBookFilePath(Path meetingBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleName(ModuleName moduleName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModuleBook getModuleBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleBook(ReadOnlyModuleBook moduleBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getModuleBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleBookFilePath(Path moduleBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getPersonsInModule(ModuleName moduleName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonInModuleBook(Person ...persons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleInMeetingBook(Module... modules) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
