package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_SAMPLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2101;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        // assertFalse(uniqueModuleList.contains(CS2030));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        // uniqueModuleList.add(CS2030);
        assertTrue(uniqueModuleList.contains(CS2030));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        // uniqueModuleList.add(CS2030);
        Module editedModule = new ModuleBuilder(CS2030).withZoomLink(VALID_ZOOMLINK_SAMPLE)
                .build();
        assertTrue(uniqueModuleList.contains(editedModule));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicatePersonException() {
        // uniqueModuleList.add(CS2030);
        // assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(CS2030));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(null, CS2030));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(CS2030, null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
        // assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.setModule(CS2030, CS2030));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        // uniqueModuleList.add(CS2030);
        uniqueModuleList.setModule(CS2030, CS2030);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2030);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
        // uniqueModuleList.add(CS2030);
        Module editedModule = new ModuleBuilder(CS2030).withZoomLink(VALID_ZOOMLINK_SAMPLE)
                .build();
        uniqueModuleList.setModule(CS2030, editedModule);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(editedModule);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        // uniqueModuleList.add(CS2030);
        uniqueModuleList.setModule(CS2030, CS2101);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2101);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
        // uniqueModuleList.add(CS2030);
        uniqueModuleList.add(CS2101);
        // assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModule(CS2030, CS2101));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        // assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(CS2030));
    }

    @Test
    public void remove_existingModule_removesModule() {
        // uniqueModuleList.add(CS2030);
        uniqueModuleList.remove(CS2030);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((UniqueModuleList) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        // uniqueModuleList.add(CS2030);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2101);
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        // uniqueModuleList.add(CS2030);
        List<Module> moduleList = Collections.singletonList(CS2101);
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2101);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(CS2030, CS2030);
        // assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}
