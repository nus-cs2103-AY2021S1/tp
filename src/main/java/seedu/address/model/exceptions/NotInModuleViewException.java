package seedu.address.model.exceptions;

public class NotInModuleViewException extends RuntimeException {
    public NotInModuleViewException() {
        super("You are currently not in the Module view. Run listMod to go back to the module view.");
    }
}
