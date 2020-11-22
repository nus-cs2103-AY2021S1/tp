package nustorage.model.record.exceptions;

import nustorage.model.record.InventoryRecord;

public class NegativeNumberOfItemException extends RuntimeException {

    public NegativeNumberOfItemException() {
        super("This would result in negative number of items in storage");
    }

    public NegativeNumberOfItemException(InventoryRecord inventoryRecord) {
        super("This would result in negative number of " + inventoryRecord + " in storage");
    }
}

