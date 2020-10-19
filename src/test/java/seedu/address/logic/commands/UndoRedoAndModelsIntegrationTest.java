package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.UndoRedoLimitReachedException;
import seedu.address.model.Models;
import seedu.address.model.ModelsManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.deliverymodel.DeliveryModelManager;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.InventoryModelManager;
import seedu.address.model.item.Item;
import seedu.address.testutil.DeliveryBuilder;
import seedu.address.testutil.ItemBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalDeliveries.getTypicalDeliveryBook;
import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

class UndoRedoAndModelsIntegrationTest {

    @Test
    void execute_statesLimitIs1_success() {
        Models models = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());
        models.setStatesLimit(1);
        models.commit();
        assertThrows(UndoRedoLimitReachedException.class, () -> models.undo());
        assertThrows(UndoRedoLimitReachedException.class, () -> models.redo());
    }

    @Test
    void execute_statesLimitIs2_success() {
        InventoryModel inventoryModel1 = makeTestInventoryModel();
        DeliveryModel deliveryModel1 = makeTestDeliveryModel();
        InventoryModel inventoryModel2 = makeTestInventoryModel();
        DeliveryModel deliveryModel2 = makeTestDeliveryModel();
        Models actualModels = new ModelsManager(inventoryModel1, deliveryModel1);
        Models expectedModels = new ModelsManager(inventoryModel2, deliveryModel2);
        actualModels.setStatesLimit(2);

        Item testItem = new ItemBuilder().build();
        Delivery testDelivery = new DeliveryBuilder().build();
        inventoryModel1.addItem(testItem);
        inventoryModel2.addItem(testItem);
        deliveryModel1.addDelivery(testDelivery);
        deliveryModel2.addDelivery(testDelivery);
        actualModels.commit();

        assertDoesNotThrow(() -> actualModels.undo());
        assertEquals(actualModels, new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel()));
        assertDoesNotThrow(() -> actualModels.redo());
        assertEquals(actualModels, expectedModels);
    }
    private InventoryModel makeTestInventoryModel() {
        return new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());
    }

    private DeliveryModel makeTestDeliveryModel() {
        return new DeliveryModelManager(getTypicalDeliveryBook(), new UserPrefs());
    }
}