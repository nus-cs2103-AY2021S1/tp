package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_REDO_LIMIT_REACHED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RedoCommand.MESSAGE_REDO_ACKNOWLEDGEMENT;
import static seedu.address.testutil.TypicalDeliveries.getTypicalDeliveryBook;
import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

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

    @Test
    void execute_undoTwiceThenRedoWithThreeStates_success() {
        Models actualModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());
        Models expectedModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());

        Item testItem = new ItemBuilder().build();
        Delivery testDelivery = new DeliveryBuilder().build();

        // add item and commit
        actualModels.getInventoryModel().addItem(testItem);
        expectedModels.getInventoryModel().addItem(testItem);
        actualModels.commit();
        expectedModels.commit();

        // add item to actualModels and commit
        actualModels.getDeliveryModel().addDelivery(testDelivery);
        actualModels.commit();

        // undo twice
        assertDoesNotThrow(() -> {
            new UndoCommand().execute(actualModels);
            new UndoCommand().execute(actualModels);
        });

        // redo and compare
        assertCommandSuccess(new RedoCommand(), actualModels, MESSAGE_REDO_ACKNOWLEDGEMENT, expectedModels);
    }

    @Test
    void execute_undoTwiceThenRedoWithTwoStates_success() {
        Models actualModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());
        Models expectedModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());

        Item testItem = new ItemBuilder().build();

        // add item and commit actualModels
        actualModels.getInventoryModel().addItem(testItem);
        expectedModels.getInventoryModel().addItem(testItem);
        actualModels.commit();

        // undo twice
        assertDoesNotThrow(() -> new UndoCommand().execute(actualModels));
        assertDoesNotThrow(() -> new UndoCommand().execute(actualModels));

        // redo and compare
        assertCommandSuccess(new RedoCommand(), actualModels, MESSAGE_REDO_ACKNOWLEDGEMENT, expectedModels);
    }

    @Test
    void execute_undoTwiceThenRedoTwiceWithThreeStates_success() {
        Models actualModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());
        Models expectedModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());

        Item testItem = new ItemBuilder().build();
        Delivery testDelivery = new DeliveryBuilder().build();

        // add item and commit actualModels
        actualModels.getInventoryModel().addItem(testItem);
        expectedModels.getInventoryModel().addItem(testItem);
        actualModels.commit();

        // add delivery and commit actualModels
        actualModels.getDeliveryModel().addDelivery(testDelivery);
        expectedModels.getDeliveryModel().addDelivery(testDelivery);
        actualModels.commit();

        // undo twice
        assertDoesNotThrow(() -> new UndoCommand().execute(actualModels));
        assertDoesNotThrow(() -> new UndoCommand().execute(actualModels));

        // redo twice and compare
        assertDoesNotThrow(() -> new RedoCommand().execute(actualModels));
        assertCommandSuccess(new RedoCommand(), actualModels, MESSAGE_REDO_ACKNOWLEDGEMENT, expectedModels);
    }

    @Test
    void execute_undoThenRedoTwiceWithThreeStates_success() {
        Models actualModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());
        Models expectedModels = new ModelsManager(makeTestInventoryModel(), makeTestDeliveryModel());

        Item testItem = new ItemBuilder().build();

        // add item and commit actualModels
        actualModels.getInventoryModel().addItem(testItem);
        expectedModels.getInventoryModel().addItem(testItem);
        actualModels.commit();

        // undo once
        assertDoesNotThrow(() -> new UndoCommand().execute(actualModels));

        // redo once and compare
        assertCommandSuccess(new RedoCommand(), actualModels, MESSAGE_REDO_ACKNOWLEDGEMENT, expectedModels);

        // redo again
        assertCommandSuccess(new RedoCommand(), actualModels, MESSAGE_REDO_LIMIT_REACHED, expectedModels);
    }

    private InventoryModel makeTestInventoryModel() {
        return new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());
    }

    private DeliveryModel makeTestDeliveryModel() {
        return new DeliveryModelManager(getTypicalDeliveryBook(), new UserPrefs());
    }
}
