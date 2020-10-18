package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVendors.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.Food;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.ReadOnlyOrderManager;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Email;
import seedu.address.model.vendor.Name;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.Vendor;

public class AddCommandTest {
    private Food food = new Food("Prata", 1.00, new HashSet<>());

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Test
    public void constructor_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    //    @Test
    //    public void execute_valid_index_throwsCommandException() {
    //        ModelStub modelStub = new ModelStub();
    //        Model model = modelStub.getEmptyModel();
    //        Index one = Index.fromOneBased(1);
    //        AddCommand addCommand = new AddCommand(one);
    //        List<Food> menu = new ArrayList<>();
    //        menu.add(new Food("Prata", 1.00, new HashSet<>()));
    //        OrderItem orderItem = new OrderItem(menu.get(one.getZeroBased()), 1);
    //        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_FIRST_SUCCESS, orderItem);
    //        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    //
    //        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void equals() {
        AddCommand addCommand1 = new AddCommand(Index.fromOneBased(1));
        AddCommand addCommand2 = new AddCommand(Index.fromOneBased(3));

        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));

        // same values -> returns true
        AddCommand addCommandCopy = new AddCommand(Index.fromOneBased(1));
        assertTrue(addCommandCopy.equals(addCommand1));

        // different types -> returns false
        assertFalse(addCommand1.equals(1));

        // null -> returns false
        assertFalse(addCommand1.equals(null));

        // different vendor -> returns false
        assertFalse(addCommand1.equals(addCommand2));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        public Model getEmptyModel() {
            return new ModelManager(getTypicalAddressBook(), new UserPrefs());
        }

        public Model getModelWithFood() {
            Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
            model.addOrderItem(new OrderItem("Prata", 1.00, new HashSet<>(), 2));
            return model;
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
        public void setMenuManager(ReadOnlyMenuManager menuManager, int index) {

        }

        @Override
        public ReadOnlyMenuManager getMenuManager(int index) {
            return null;
        }

        @Override
        public boolean hasFood(Food food, int index) {
            return false;
        }

        @Override
        public void deleteFood(Food target, int index) {

        }

        @Override
        public void addFood(Food food, int index) {

        }

        @Override
        public void setFood(Food target, Food editedFood, int index) {

        }

        @Override
        public ObservableList<Food> getFilteredFoodList(int index) {
            return null;
        }

        @Override
        public void clearOrder() {

        }

        @Override
        public void updateFilteredFoodList(Predicate<Food> predicate, int index) {

        }

        @Override
        public void setOrderManager(ReadOnlyOrderManager orderManager) {

        };

        @Override
        public ReadOnlyOrderManager getOrderManager() {
            return null;
        };

        @Override
        public boolean hasOrderItem(OrderItem orderItem) {
            return false;
        };

        @Override
        public void deleteOrderItem(OrderItem target){

        };

        @Override
        public void addOrderItem(OrderItem orderItem){

        };

        @Override
        public void setOrderItem(OrderItem target, OrderItem editedOrderItem){

        };


        @Override
        public ObservableList<OrderItem> getFilteredOrderItemList() {
            return null;
        }

        @Override
        public int getOrderSize() {
            return 0;
        }

        @Override
        public void updateFilteredOrderItemList(Predicate<OrderItem> predicate){

        };

        @Override
        public Path getMenuManagerFolderPath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMenuManagerFolderPath(Path menuManagerFolderPath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addVendor(Vendor vendor) {
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
        public boolean hasVendor(Vendor vendor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteVendor(Vendor target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVendor(Vendor target, Vendor editedVendor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Vendor> getFilteredVendorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVendorList(Predicate<Vendor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getOrderHistorySize() {
            return 0;
        }

        @Override
        public void undoOrder() {

        }
    }

    /**
     * A Model stub that contains a single vendor.
     */
    private class ModelStubWithVendor extends ModelStub {
        private Vendor vendor = new Vendor(new Name("hey"), new Phone("81999"), new Email("99address"),
                new Address("89w0q8w091"),
                new HashSet<>(), null);
        private Food food = new Food("Prata", 10, new HashSet<>());

        ModelStubWithVendor(Vendor vendor) {
            requireNonNull(vendor);
            this.vendor = vendor;
        }

        ModelStubWithVendor(Food food) {
            requireNonNull(food);
            this.food = food;
        }

        @Override
        public boolean hasVendor(Vendor vendor) {
            requireNonNull(vendor);
            return this.vendor.isSameVendor(vendor);
        }

        public boolean hasFood(Food food) {
            requireNonNull(food);
            return this.food.equals(food);
        }
    }

    /**
     * A Model stub that always accept the vendor being added.
     */
    private class ModelStubAcceptingVendorAdded extends ModelStub {
        final ArrayList<Vendor> vendorsAdded = new ArrayList<>();

        @Override
        public boolean hasVendor(Vendor vendor) {
            requireNonNull(vendor);
            return vendorsAdded.stream().anyMatch(vendor::isSameVendor);
        }

        @Override
        public void addVendor(Vendor vendor) {
            requireNonNull(vendor);
            vendorsAdded.add(vendor);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
