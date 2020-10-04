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
import seedu.address.model.ItemList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemPrecursorBuilder;

public class AddItemCommandTest {
    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItemCommand(null));
    }

    /**
     * Tests for successful adding of item.
     */
    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        ItemPrecursor validItemPrecursor = new ItemPrecursorBuilder().build();
        Item validItem = new ItemBuilder().build();

        CommandResult commandResult = new AddItemCommand(validItemPrecursor).execute(modelStub);
        assertEquals(String.format(AddItemCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validItem).size(), modelStub.itemsAdded.size());
        assertTrue(validItem.isSameItem(modelStub.itemsAdded.get(0)));
    }

    /**
     * Tests for detecting duplicate items.
     */
    @Test
    public void execute_duplicateItem_throwsCommandException() {
        ItemPrecursor validItemPrecursor = new ItemPrecursorBuilder().build();
        Item validItem = new ItemBuilder().build();

        AddItemCommand addItemCommand = new AddItemCommand(validItemPrecursor);
        ModelStub modelStub = new ModelStubWithItem(validItem);

        assertThrows(CommandException.class,
                AddItemCommand.MESSAGE_DUPLICATE_ITEM, () -> addItemCommand.execute(modelStub));
    }

    /**
     * Tests for equivalency.
     */
    @Test
    public void equals() {
        ItemPrecursor apple = new ItemPrecursorBuilder().withName("Apple").build();
        ItemPrecursor banana = new ItemPrecursorBuilder().withName("Banana").build();
        AddItemCommand addAppleCommand = new AddItemCommand(apple);
        AddItemCommand addBananaCommand = new AddItemCommand(banana);

        // same object -> returns true
        assertTrue(addAppleCommand.equals(addAppleCommand));

        // same values -> returns true
        AddItemCommand addAppleCommandCopy = new AddItemCommand(apple);
        assertTrue(addAppleCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addAppleCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleCommand.equals(null));

        // different item -> returns false
        assertFalse(addAppleCommand.equals(addBananaCommand));
    }

    /**
     * A default model stub that have all of the methods failing EXCEPT processPrecursors.
     * This is because we minimally need the processPrecursors method to handle AddItemCommand.
     */
    private class ModelStub extends ModelManager implements Model {
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
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
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
        public void setPerson(Person target, Person editedPerson) {
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
        public ReadOnlyAddressBook getAddressBook() {
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
        public boolean hasPerson(Person person) {
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
        public void deletePerson(Person target) {
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
        public void addPerson(Person person) {
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
        public ObservableList<Person> getFilteredPersonList() {
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
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
    }

    /**
     * A Model stub that contains a single item precursor.
     */
    private class ModelStubWithItem extends ModelStub {
        private final Item item;

        ModelStubWithItem(Item item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return this.item.isSameItem(item);
        }
    }

    /**
     * A Model stub that always accept the item precursor being added.
     */
    private class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public void addItem(Item item) {
            requireNonNull(item);
            itemsAdded.add(item);
        }

        @Override
        public ReadOnlyItemList getItemList() {
            return new ItemList();
        }
    }

}
