package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CdCommandTest {

    ModelStub modelStub = new ModelStubWithCurrentPath();

    @Test
    public void equals() {
        CdCommand cdCommand1 = new CdCommand(AddressType.CHILD, "file");
        CdCommand cdCommand2 = new CdCommand(AddressType.ABSOLUTE, "file");
        CdCommand cdCommand3 = new CdCommand(AddressType.CHILD, "different");

        // same object -> returns true
        assertTrue(cdCommand1.equals(cdCommand1));

        // same values -> returns true
        CdCommand cdCommand1Copy = new CdCommand(AddressType.CHILD, "file");
        assertTrue(cdCommand1.equals(cdCommand1Copy));

        // different types -> returns false
        assertFalse(cdCommand1.equals(1));

        // null -> returns false
        assertFalse(cdCommand1.equals(null));

        // different address type -> returns false
        assertFalse(cdCommand1.equals(cdCommand2));

        // different address string -> returns false;
        assertFalse(cdCommand1.equals(cdCommand3));
    }

    @Test
    public void execute_validAddress_success() {
        CdCommand correctCdCommand = new CdCommand(AddressType.PARENT, "");
        try {
            correctCdCommand.execute(modelStub);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
