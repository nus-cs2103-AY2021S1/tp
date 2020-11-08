package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.VendorCommand.MESSAGE_RESET_VENDOR_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.vendor.Vendor;
import seedu.address.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code VendorCommand}.
 */
public class VendorCommandTest {

    @Test
    public void execute_validDefaultIndex_success() {
        Model model = TypicalModel.getModelManagerWithMenu();
        Index second = Index.fromOneBased(2);
        VendorCommand vendorCommand = new SwitchVendorCommand(second);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.selectVendor(second.getZeroBased());
        Vendor expectedVendor = expectedModel.getObservableVendorList().get(second.getZeroBased());
        String expectedMessage = String.format(
                SwitchVendorCommand.MESSAGE_SELECT_VENDOR_SUCCESS,
                expectedVendor.getName()
        );
        assertCommandSuccess(vendorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndex_success() {
        Model model = TypicalModel.getModelManagerWithMenu();
        Index third = Index.fromOneBased(3);
        VendorCommand vendorCommand = new SwitchVendorCommand(third);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.selectVendor(third.getZeroBased());
        Vendor expectedVendor = expectedModel.getObservableVendorList().get(third.getZeroBased());
        String expectedMessage = String.format(
                SwitchVendorCommand.MESSAGE_SELECT_VENDOR_SUCCESS,
                expectedVendor.getName()
        );
        assertCommandSuccess(vendorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sameIndex_success() {
        Model model = TypicalModel.getModelManagerWithMenu();
        Index first = Index.fromOneBased(1);
        VendorCommand vendorCommand = new SwitchVendorCommand(first);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.selectVendor(first.getZeroBased());
        Vendor expectedVendor = expectedModel.getObservableVendorList().get(first.getZeroBased());
        String expectedMessage = String.format(
                SwitchVendorCommand.MESSAGE_SELECT_VENDOR_SAME,
                expectedVendor.getName()
        );
        assertCommandSuccess(vendorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        Index outOfBoundIndex = Index.fromOneBased(model.getObservableVendorList().size() + 1);
        VendorCommand vendorCommand = new SwitchVendorCommand(outOfBoundIndex);

        assertCommandFailure(vendorCommand, model, Messages.MESSAGE_INVALID_VENDOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        VendorCommand switchFirstCommand = new SwitchVendorCommand(Index.fromOneBased(1));
        VendorCommand switchSecondCommand = new SwitchVendorCommand(Index.fromOneBased(2));

        VendorCommand firstVendorCommand = new VendorCommand();
        VendorCommand secondVendorCommand = new VendorCommand();

        // same object -> returns true
        assertTrue(switchFirstCommand.equals(switchFirstCommand));
        assertTrue(firstVendorCommand.equals(firstVendorCommand));

        // same values -> returns true
        VendorCommand removeFirstCommandCopy = new SwitchVendorCommand(Index.fromOneBased(1));
        assertTrue(switchFirstCommand.equals(removeFirstCommandCopy));
        assertTrue(firstVendorCommand.equals(secondVendorCommand));

        // different types -> returns false
        assertFalse(switchFirstCommand.equals(1));
        assertFalse(firstVendorCommand.equals(1));

        // null -> returns false
        assertFalse(switchFirstCommand.equals(null));
        assertFalse(firstVendorCommand.equals(null));

        // different vendor -> returns false
        assertFalse(switchFirstCommand.equals(switchSecondCommand));


    }

    @Test
    public void execute_noIndexProvided_success() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(3);

        VendorCommand vendorCommand = new VendorCommand();
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.selectVendor(-1);
        assertCommandSuccess(vendorCommand, model, MESSAGE_RESET_VENDOR_SUCCESS, expectedModel);
    }

}
