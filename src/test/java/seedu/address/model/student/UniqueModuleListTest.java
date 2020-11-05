package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.DuplicateShowableException;
import seedu.address.model.exceptions.ShowableNotFoundException;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;
import seedu.address.testutil.ModuleBuilder;

public class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(CS2100));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.addModule(CS2100);
        assertTrue(uniqueModuleList.contains(CS2100));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleList.addModule(CS2100);
        Module editedCS2100 = new ModuleBuilder(CS2100)
                .withUniqueTutorialGroupList(new UniqueTutorialGroupList()).build();
        assertTrue(uniqueModuleList.contains(editedCS2100));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.addModule(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateShowableException() {
        uniqueModuleList.addModule(CS2100);
        assertThrows(DuplicateShowableException.class, () -> uniqueModuleList.addModule(CS2100));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(null, CS2100));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModule(CS2100, null));
    }

    @Test
    public void setStudent_targetModuleNotInList_throwsShowableNotFoundException() {
        assertThrows(ShowableNotFoundException.class, () -> uniqueModuleList.setModule(CS2100, CS2100));
    }

    @Test
    public void setStudent_editedModuleIsSameModule_success() {
        uniqueModuleList.addModule(CS2100);
        uniqueModuleList.setModule(CS2100, CS2100);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.addModule(CS2100);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
        uniqueModuleList.addModule(CS2100);
        Module editedCS2100 = new ModuleBuilder(CS2100).withUniqueTutorialGroupList(new UniqueTutorialGroupList())
                .build();
        uniqueModuleList.setModule(CS2100, editedCS2100);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.addModule(editedCS2100);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        uniqueModuleList.addModule(CS2100);
        uniqueModuleList.setModule(CS2100, CS2103T);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.addModule(CS2103T);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateShowableException() {
        uniqueModuleList.addModule(CS2100);
        uniqueModuleList.addModule(CS2103T);
        assertThrows(DuplicateShowableException.class, () -> uniqueModuleList.setModule(CS2100, CS2103T));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.removeModule(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsShowableNotFoundException() {
        assertThrows(ShowableNotFoundException.class, () -> uniqueModuleList.removeModule(CS2100));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.addModule(CS2100);
        uniqueModuleList.removeModule(CS2100);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModuleList_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModuleList((UniqueModuleList) null));
    }

    @Test
    public void setStudentList_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.addModule(CS2100);

        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.addModule(CS2103T);
        uniqueModuleList.setModuleList(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModuleList_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModuleList((List<Module>) null));
    }

    @Test
    public void setModuleList_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.addModule(CS2100);
        List<Module> moduleList = Collections.singletonList(CS2103T);
        uniqueModuleList.setModuleList(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.addModule(CS2103T);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModuleList_listWithDuplicateModules_throwsDuplicateShowableException() {
        List<Module> listWithDuplicateModules = Arrays.asList(CS2100, CS2100);
        assertThrows(DuplicateShowableException.class, ()
                -> uniqueModuleList.setModuleList(listWithDuplicateModules)
        );
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueModuleList.asUnmodifiableObservableList().remove(0)
        );
    }
}

