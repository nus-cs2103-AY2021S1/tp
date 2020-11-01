package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalVendors.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.MenuItem;
import seedu.address.model.food.PriceWithinRangePredicate;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.order.OrderManager;
import seedu.address.testutil.TypicalVendors;

public class PriceCommandTest {
    private List<MenuManager> menuManagers = TypicalVendors.getManagers();

    private Model initialiseModel() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), menuManagers, new OrderManager());
        model.selectVendor(0);
        return model;
    }

    @Test
    public void execute_validInequalityAndPrice_success() throws ParseException {
        Model model = initialiseModel();
        Inequality inequality = ParserUtil.parseInequality("<");
        PriceWithinRangePredicate predicate = new PriceWithinRangePredicate(inequality, 2.2);
        PriceCommand priceCommand = new PriceCommand(predicate);
        List<MenuItem> list = model.getFilteredMenuItemList();
        //List<Food> filteredList = list.stream().filter(x -> x.getPrice() < 2.2).collect(Collectors.toList());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), menuManagers,
                new OrderManager());
        expectedModel.selectVendor(0);
        String expectedMessage = String.format(Messages.MESSAGE_FOOD_LISTED_PRICE_CONTEXT,
               2 , predicate);
        assertCommandSuccess(priceCommand, model, expectedMessage, expectedModel);
    }
}
