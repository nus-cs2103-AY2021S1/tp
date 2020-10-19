package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNDO_LIMIT_REACHED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_ACKNOWLEDGEMENT;
import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

import org.junit.jupiter.api.Test;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.deliverymodel.DeliveryModelManager;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.InventoryModelManager;

class UndoCommandTest {

    @Test
    void execute_emptyInventoryModel_success() {
        InventoryModel actualInventoryModel = new InventoryModelManager();
        InventoryModel expectedInventoryModel = new InventoryModelManager();
        assertCommandSuccess(
                new UndoCommand(), actualInventoryModel, MESSAGE_UNDO_LIMIT_REACHED, expectedInventoryModel);
    }

    @Test
    void execute_nothingToUndo_success() {
        InventoryModel inventoryModel =
                new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());
        assertCommandSuccess(
                new UndoCommand(), inventoryModel, MESSAGE_UNDO_LIMIT_REACHED, inventoryModel);
    }

    @Test
    void execute_hasPreviousState_success() {
        InventoryModel inventoryModel = new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());
        DeliveryModel deliveryModel = new DeliveryModelManager();

        inventoryModel.commit();
        inventoryModel.commit();
        deliveryModel.commit();
        deliveryModel.commit();

        assertCommandSuccess(
                new UndoCommand(), inventoryModel, MESSAGE_UNDO_ACKNOWLEDGEMENT, inventoryModel);
    }

}