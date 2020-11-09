package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ValidCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAliasMap;
import seedu.address.model.ReadOnlyJournal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.journal.Entry;
import seedu.address.model.person.Person;

public class AddAliasCommandTest {

    @Nested
    @DisplayName("constructor")
    class AddAliasCommandConstructor {

        @Test
        @DisplayName("Should throw null pointer exception if pass in a null object")
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new AddAliasCommand(null, null));
        }
    }


    @Nested
    @DisplayName("execute method")
    class Execute {

        @Test
        @DisplayName("Should throw CommandException if the target is not a valid command/alias")
        public void execute_invalidTarget_throwsCommandException() {
            Command c = new AddAliasCommand("dummy", "j");
            Model model = new ModelStub();
            assertThrows(CommandException.class, () -> c.execute(model));
        }

        @Test
        @DisplayName("Should throw CommandException if the alias already defined")
        public void execute_existAlias_throwsCommandException() {
            Command c = new AddAliasCommand("addj", "switch");
            Model model = new ModelStub();
            assertThrows(CommandException.class, () -> c.execute(model));
        }

        @Test
        @DisplayName("Add successfully")
        public void execute_validAlias_success() {
            Command c = new AddAliasCommand("switch", "si");
            Model model = new ModelStub();

            try {
                CommandResult result = c.execute(model);
                assertEquals(
                        "Added si as an alias for switch.",
                        result.getFeedbackToUser()
                );
                assertEquals(ValidCommand.SWITCH, ValidCommand.commandTypeOf("si"));
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    @DisplayName("Equals method")
    class Equal {

        @Test
        @DisplayName("Should be true if they are the same object")
        public void equals_sameObj_true() {
            Command c = new AddAliasCommand("switch", "si");
            assertEquals(c, c);
        }

        @Test
        @DisplayName("Should be false if different target")
        public void equals_diffTarget_false() {
            Command c1 = new AddAliasCommand("switch", "si");
            Command c2 = new AddAliasCommand("addj", "si");
            assertNotEquals(c1, c2);
        }

        @Test
        @DisplayName("Should be false if different alias")
        public void equals_diffAlias_false() {
            Command c1 = new AddAliasCommand("addj", "j");
            Command c2 = new AddAliasCommand("addj", "dj");
            assertNotEquals(c1, c2);
        }

        @Test
        @DisplayName("Should be true if same target and alias")
        public void equals_sameTargetAlias_true() {
            Command c1 = new AddAliasCommand("delete", "del");
            Command c2 = new AddAliasCommand("delete", "del");
            assertEquals(c1, c2);
        }
    }
    /**
     * A default model stub that have all of the methods failing except update alias map.
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
        public void clearJournalContacts() {
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
        public void setJournal(ReadOnlyJournal newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateJournalContacts(
                Person originalPerson, Person updatedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyJournal getJournal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasName(Person person) {
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
        public void deleteEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAlias(Map<String, ValidCommand> map) {

        }

        @Override
        public ReadOnlyAliasMap getAliasMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEntry(Entry target, Entry editedEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entry> getFilteredEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntryList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFrequentPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getRecentPersonList() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
