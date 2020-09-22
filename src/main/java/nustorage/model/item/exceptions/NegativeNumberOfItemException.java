package nustorage.model.item.exceptions;

import nustorage.model.item.Item;

public class NegativeNumberOfItemException extends RuntimeException{
    public NegativeNumberOfItemException() {
        super("This would result in negative number of items in storage");
    }

    public NegativeNumberOfItemException(Item item) {
        super("This would result in negative number of " + item + " in storage");
    }
}

