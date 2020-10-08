package seedu.stock.logic.commands;

//import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.stock.testutil.TypicalStocks.getTypicalAddressBook;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

//import seedu.stock.model.Model;
//import seedu.stock.model.ModelManager;
//import seedu.stock.model.SerialNumberSetsBook;
//import seedu.stock.model.UserPrefs;
//import seedu.stock.model.stock.Stock;
//import seedu.stock.testutil.StockBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

//    private Model model;

//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(getTypicalStockBook(), new UserPrefs());
//    }

//    @Test
//    public void execute_newStock_success() {
//        Stock validStock = new StockBuilder().build();
//
//        Model expectedModel = new ModelManager(model.getStockBook(), new UserPrefs(), new SerialNumberSetsBook());
//        expectedModel.addStock(validStock);
//
//        assertCommandSuccess(new AddCommand(validStock), model,
//                String.format(AddCommand.MESSAGE_SUCCESS, validStock), expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Stock personInList = model.getStockBook().getStockList().get(0);
//        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_STOCK);
//    }
}
