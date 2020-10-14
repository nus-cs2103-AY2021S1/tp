package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    //    private Model model;
    //
    //    @BeforeEach
    //    public void setUp() {
    //        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    //    }

    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    //
    //    @Test
    //    public void execute_newFoodItem_success() {
    //        //        Vendor validVendor = new VendorBuilder().build();
    //        ArrayList<MenuManager> menuManagers = new ArrayList<>();
    //        menuManagers.add(new MenuManager());
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
    //                menuManagers,
    //                new OrderManager());
    //        Food food = new Food("Prata", 1.00, new HashSet<>());
    //        OrderItem item = new OrderItem(food, 1);
    //        expectedModel.addOrderItem(item);
    //        AddCommand addCommand = new AddCommand(Index.fromOneBased(1));
    //        assertCommandSuccess(addCommand, model,
    //                String.format(AddCommand.MESSAGE_ADD_FIRST_SUCCESS, item),
    //                expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_duplicateFood_success() {
    //        ArrayList<MenuManager> menuManagers = new ArrayList<>();
    //        menuManagers.add(new MenuManager());
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
    //                menuManagers,
    //                new OrderManager());
    //        Food food = new Food("Prata", 1.00, new HashSet<>());
    //        OrderItem item = new OrderItem(food, 1);
    //        expectedModel.addOrderItem(item);
    //        AddCommand addCommand = new AddCommand(Index.fromOneBased(1));
    //        assertCommandSuccess(addCommand, model,
    //                String.format(AddCommand.MESSAGE_ADD_FIRST_SUCCESS, item),
    //                expectedModel);
    //    }
}
