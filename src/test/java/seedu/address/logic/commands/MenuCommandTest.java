package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalVendors.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderManager;
import seedu.address.model.vendor.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalVendors;

public class MenuCommandTest {

    private Model initialiseModel() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), TypicalVendors.getManagers(),
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
}
