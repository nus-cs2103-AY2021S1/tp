package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipePrecursor;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    /**
     * tests for successful add
     */
    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validPerson), modelStub.personsAdded);
    }

    /**
     * tests for duplicate persons
     */
    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    /**
     * tests for equals
     */
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
        public Path getItemListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getLocationListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getRecipeListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItemListFilePath(Path itemListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipeListFilePath(Path recipeListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLocation(Location location) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItemList(ReadOnlyItemList itemList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipeList(ReadOnlyRecipeList recipeList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyItemList getItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLocationList getLocationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRecipeList getRecipeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLocation(Location location) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItem(Item target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecipe(Recipe target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItem(Item target, Item editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipe(Recipe target, Recipe editedRecipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Location> getFilteredLocationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Recipe> getFilteredRecipeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLocationList(Predicate<Location> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int findLocationID(Location toFind) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Recipe processPrecursor(RecipePrecursor recipePrecursor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Item processPrecursor(ItemPrecursor itemPrecursor) {
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
    }

}
