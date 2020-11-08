package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalVendors.getTypicalVendorManager;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderManager;
import seedu.address.model.vendor.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalVendors;

public class MenuCommandTest {

    private Model initialiseModel() {
        Model model = new ModelManager(getTypicalVendorManager(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        model.selectVendor(0);
        return model;
    }

    @Test
    public void menuCommandExecution_success() {
        Model model = initialiseModel();
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Milo"));
        model.updateFilteredMenuItemList(predicate);
        Model expectedModel = initialiseModel();
        assertCommandSuccess(new MenuCommand(), model, "All food listed!", expectedModel);
    }

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = initialiseModel();
        model.selectVendor(-1);
        assertCommandFailure(new MenuCommand(), model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }
}
