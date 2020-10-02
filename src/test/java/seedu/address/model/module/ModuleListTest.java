package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS50;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS1010S;
import static seedu.address.testutil.TypicalModules.CS2030;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.testutil.ModuleBuilder;

public class ModuleListTest {
    private final ModuleList moduleList = new ModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(moduleList.contains(CS1010S));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        moduleList.add(CS1010S);
        assertTrue(moduleList.contains(CS1010S));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        moduleList.add(CS1010S);
        Module editedCS1010S = new ModuleBuilder(CS1010S).withName(VALID_MODULE_NAME_CS50).build();
        assertTrue(moduleList.contains(editedCS1010S));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        moduleList.add(CS1010S);
        assertThrows(DuplicateModuleException.class, () -> moduleList.add(CS1010S));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.setModule(null, CS1010S));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.setModule(CS1010S, null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> moduleList.setModule(CS1010S, CS1010S));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        moduleList.add(CS1010S);
        moduleList.setModule(CS1010S, CS1010S);
        ModuleList expectedModuleList = new ModuleList();
        expectedModuleList.add(CS1010S);
        assertEquals(expectedModuleList, moduleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
        moduleList.add(CS1010S);
        Module editedCS1010S = new ModuleBuilder(CS1010S).withName(VALID_MODULE_NAME_CS50).build();
        moduleList.setModule(CS1010S, editedCS1010S);
        ModuleList expectedModuleList = new ModuleList();
        expectedModuleList.add(editedCS1010S);
        assertEquals(expectedModuleList, moduleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        moduleList.add(CS1010S);
        moduleList.setModule(CS1010S, CS2030);
        ModuleList expectedModuleList = new ModuleList();
        expectedModuleList.add(CS2030);
        assertEquals(expectedModuleList, moduleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
        moduleList.add(CS1010S);
        moduleList.add(CS2030);
        assertThrows(DuplicateModuleException.class, () -> moduleList.setModule(CS1010S, CS2030));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> moduleList.remove(CS1010S));
    }

    @Test
    public void remove_existingModule_removesModule() {
        moduleList.add(CS1010S);
        moduleList.remove(CS1010S);
        ModuleList expectedModuleList = new ModuleList();
        assertEquals(expectedModuleList, moduleList);
    }

    @Test
    public void setModules_nullModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.setModules((ModuleList) null));
    }

    @Test
    public void setModules_moduleList_replacesOwnListWithProvidedModuleList() {
        moduleList.add(CS1010S);
        ModuleList expectedModuleList = new ModuleList();
        expectedModuleList.add(CS2030);
        moduleList.setModules(expectedModuleList);
        assertEquals(expectedModuleList, moduleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        moduleList.add(CS1010S);
        List<Module> modules = Collections.singletonList(CS2030);
        moduleList.setModules(modules);
        ModuleList expectedModuleList = new ModuleList();
        expectedModuleList.add(CS2030);
        assertEquals(expectedModuleList, moduleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(CS1010S, CS1010S);
        assertThrows(DuplicateModuleException.class, () -> moduleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> moduleList.asUnmodifiableObservableList().remove(0));
    }
}
