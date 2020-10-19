package seedu.address.model;

import seedu.address.logic.commands.exceptions.UndoRedoLimitReachedException;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryModel;

import java.util.List;
import java.util.Map;

public interface Models {
    /**
     * Gets the {@code DeliveryModel} stored inside {@code Models}.
     *
     * @return The {@code DeliveryModel} stored inside {@code Models}.
     */
    public DeliveryModel getDeliveryModel();

    /**
     * Sets the {@code DeliveryModel} stored inside {@code Models}.
     */
    public void setDeliveryModel(DeliveryModel deliveryModel);

    /**
     * Gets the {@code InventoryModel} stored inside {@code Models}.
     *
     * @return The {@code InventoryModel} stored inside {@code Models}.
     */
    public InventoryModel getInventoryModel();

    /**
     * Sets the {@code InventoryModel} stored inside {@code Models}.
     */
    public void setInventoryModel(InventoryModel inventoryModel);

    /**
     * Gets every {@code Model} as a {@code Map}.
     *
     * @return a {@code Map} mapping each {@code Model}'s key to its respective {@code Model}.
     */
    public Map<? extends String, ? extends Model> getModelsAsMap();

    /**
     * Commits the current state of {@code Models} into history.
     */
    public void commit();

    /**
     * Reverts the current state of {@code Models} one step in history.
     */
    public void undo() throws UndoRedoLimitReachedException;

    /**
     * Redoes an undone state of {@code Models} one step forward in history.
     */
    public void redo() throws UndoRedoLimitReachedException;
}
