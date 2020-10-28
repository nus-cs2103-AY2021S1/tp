package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.util.SamplePolicyDataUtil;
import seedu.address.testutil.ClientListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ClientList(), new ClientList(modelManager.getClientList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setClientListFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setClientListFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setClientListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setClientListFilePath(null));
    }

    @Test
    public void setClientListFilePath_validPath_setsClientListFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setClientListFilePath(path);
        assertEquals(path, modelManager.getClientListFilePath());
    }

    @Test
    public void setPolicyListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPolicyListFilePath(null));
    }

    @Test
    public void setPolicyListFilePath_validPath_setsPolicyListFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPolicyListFilePath(path);
        assertEquals(path, modelManager.getPolicyListFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInClientList_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInClientList_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getIsArchiveMode_init_returnsFalse() {
        assertFalse(modelManager.getIsArchiveMode());
    }

    @Test
    public void setIsArchiveMode_setTrue_returnsTrue() {
        modelManager.setIsArchiveMode(true);
        assertTrue(modelManager.getIsArchiveMode());
    }

    @Test
    public void setIsArchiveMode_setFalse_returnsFalse() {
        modelManager.setIsArchiveMode(false);
        assertFalse(modelManager.getIsArchiveMode());
    }

    @Test
    public void getIsArchiveModeProperty_init_returnsFalse() {
        assertFalse(modelManager.getIsArchiveModeProperty().get());
    }

    @Test
    public void setIsArchiveMode_setTrue_updatesIsArchiveModeProperty() {
        modelManager.setIsArchiveMode(true);
        assertTrue(modelManager.getIsArchiveModeProperty().get());
    }

    @Test
    public void setIsArchiveMode_setFalse_updatesIsArchiveModeProperty() {
        modelManager.setIsArchiveMode(false);
        assertFalse(modelManager.getIsArchiveModeProperty().get());
    }

    @Test
    public void addPolicy_success() {
        Policy policy = LIFE_TIME;
        PolicyList policyList = new PolicyList();
        policyList.add(policy);
        modelManager.addPolicy(policy);
        assertEquals(policyList, modelManager.getPolicyList());
    }

    @Test
    public void clearPolicyList_success() {
        Policy policy = LIFE_TIME;
        modelManager.addPolicy(policy);
        modelManager.clearPolicyList();
        assertEquals(new PolicyList(), modelManager.getPolicyList());
    }

    @Test
    public void equals() {
        ClientList clientList = new ClientListBuilder().withPerson(ALICE).withPerson(BENSON).build();
        ClientList differentClientList = new ClientList();
        UserPrefs userPrefs = new UserPrefs();
        PolicyList policyList = SamplePolicyDataUtil.getSamplePolicyList();

        // same values -> returns true
        modelManager = new ModelManager(clientList, userPrefs, policyList);
        ModelManager modelManagerCopy = new ModelManager(clientList, userPrefs, policyList);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different clientList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentClientList, userPrefs, policyList)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(clientList, userPrefs, policyList)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setClientListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(clientList, differentUserPrefs, policyList)));

        // different isArchiveMode -> returns false
        ModelManager modelManagerCopyInArchiveMode = new ModelManager(clientList, userPrefs, policyList);
        modelManagerCopyInArchiveMode.setIsArchiveMode(true);
        assertFalse(modelManager.equals(modelManagerCopyInArchiveMode));
    }
}
