package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.food.PriceWithinRangePredicate;
import seedu.address.testutil.TypicalModel;

public class PriceCommandTest {

    @Test
    public void execute_validInequalityAndPrice_success() throws ParseException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Inequality inequality = ParserUtil.parseInequality("<");
        PriceWithinRangePredicate predicate = new PriceWithinRangePredicate(inequality, 2.2);
        PriceCommand priceCommand = new PriceCommand(predicate);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.updateFilteredMenuItemList(x -> x.getPrice() < 2.2);
        String expectedMessage = String.format(Messages.MESSAGE_FOOD_LISTED_PRICE_CONTEXT,
               2 , predicate);
        assertCommandSuccess(priceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_vendorNotSelected_throwsException() throws ParseException {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        Inequality inequality = ParserUtil.parseInequality("<");
        PriceWithinRangePredicate predicate = new PriceWithinRangePredicate(inequality, 2.2);
        PriceCommand priceCommand = new PriceCommand(predicate);
        assertCommandFailure(priceCommand, model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }
}
