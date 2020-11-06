package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.VersionedListException;
import seedu.address.testutil.ModuleListBuilder;

public class VersionedModuleListTest {
    private static final ModuleList VALID_MODULE_LIST = new ModuleListBuilder().withModule(CS2030)
            .withModule(CS2101).build();
    private final VersionedModuleList versionedModuleList = new VersionedModuleList(getTypicalModuleList());


    @Test
    public void constructor() {
        assertEquals(getTypicalModuleList(), versionedModuleList);
    }
    @Test
    public void execute_commit_success() {
        //Initiate empty VersionedModuleList
        VersionedModuleList initialVersionedModuleList = new VersionedModuleList();
        initialVersionedModuleList.commit(VALID_MODULE_LIST);
        assertEquals(initialVersionedModuleList, VALID_MODULE_LIST);
    }

    @Test
    public void undoData_withNoHistory_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedModuleList.undo());
    }

    @Test
    public void execute_undoWithHistory_success() {
        //Initiate empty VersionedModuleList
        VersionedModuleList initialVersionedModuleList = new VersionedModuleList();
        initialVersionedModuleList.commit(VALID_MODULE_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedModuleList, VALID_MODULE_LIST);
        try {
            initialVersionedModuleList.undo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedModuleList, new ModuleList());
    }

    @Test
    public void redoData_withNoFuture_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedModuleList.redo());
    }

    @Test
    public void execute_redoWithFuture_success() {
        //Initiate empty VersionedModuleList
        VersionedModuleList initialVersionedModuleList = new VersionedModuleList();
        initialVersionedModuleList.commit(VALID_MODULE_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedModuleList, VALID_MODULE_LIST);
        try {
            initialVersionedModuleList.undo();
            //make sure that undo was successful
            assertEquals(initialVersionedModuleList, new ModuleList());
            initialVersionedModuleList.redo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedModuleList, VALID_MODULE_LIST);
    }
}
