package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommandTest {

    public static String absoluteHomeAddress;
    public static String relativeFileAddress;
    public static String absoluteFileAddress;

    @BeforeAll
    static void setUpFileAddress() {
        absoluteHomeAddress = System.getProperty("user.home");

        String folderAddress = "./";
        relativeFileAddress = folderAddress;

        File folderFile = new File(folderAddress);
        assert folderFile.isDirectory();
        absoluteFileAddress = folderFile.getAbsolutePath();
    }

    @Test
    public void execute_addressTypeAbsolute_success() {
        Model modelStub = new ModelStubWithCurrentPath(absoluteFileAddress);
        CdCommand cdCommand = new CdCommand(AddressType.ABSOLUTE, absoluteHomeAddress);
        Model expectedModel = new ModelStubWithCurrentPath(absoluteHomeAddress);

        assertCommandSuccess(cdCommand, modelStub,
                String.format(CdCommand.MESSAGE_SUCCESS, absoluteHomeAddress), expectedModel);
    }

    @Test
    public void execute_addressTypeChild_success() {
        Model modelStub = new ModelStubWithCurrentPath(absoluteFileAddress);
        CdCommand cdCommand = new CdCommand(AddressType.CHILD, "src");
        String newFileAddress = absoluteFileAddress + "\\src";
        Model expectedModel = new ModelStubWithCurrentPath(newFileAddress);

        assertCommandSuccess(cdCommand, modelStub,
                String.format(CdCommand.MESSAGE_SUCCESS, newFileAddress), expectedModel);
    }


    @Test
    public void execute_addressTypeParent_success() {
        String childAddress = absoluteFileAddress + "\\src";
        Model modelStub = new ModelStubWithCurrentPath(childAddress);
        CdCommand cdCommand = new CdCommand(AddressType.PARENT, "");
        Model expectedModel = new ModelStubWithCurrentPath(absoluteFileAddress);

        assertCommandSuccess(cdCommand, modelStub,
                String.format(CdCommand.MESSAGE_SUCCESS, absoluteFileAddress), expectedModel);
    }

    @Test
    public void execute_unknownAddressType_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CdCommand(null, ""));
    }

    @Test
    public void execute_rootGetParent_throwCommandException() {
        Path root = Paths.get(absoluteHomeAddress).getRoot();
        Model modelStub = new ModelStubWithCurrentPath(root.toString());
        CdCommand cdCommand = new CdCommand(AddressType.PARENT, "");
        assertThrows(CommandException.class, CdCommand.MESSAGE_NO_PARENT_PATH, () -> cdCommand.execute(modelStub));
    }

    @Test
    public void execute_isNotDirectory_throwCommandException() {
        Model modelStub = new ModelStubWithCurrentPath(absoluteFileAddress);
        CdCommand cdCommand = new CdCommand(AddressType.CHILD, "build.gradle");

        assertThrows(CommandException.class,
                String.format(CdCommand.MESSAGE_PATH_INVALID, absoluteFileAddress + "\\build.gradle"),
                () -> cdCommand.execute(modelStub));
    }

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
}
