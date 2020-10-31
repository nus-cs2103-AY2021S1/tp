package seedu.cc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.GuiSettings;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;
import seedu.cc.model.account.exceptions.AccountNotFoundException;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new CommonCents(), new CommonCents(modelManager.getCommonCents()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setCommonCentsFilePath(Paths.get("commonCents/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setCommonCentsFilePath(Paths.get("new/commonCents/file/path"));
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
    public void setCommonCentsFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCommonCentsFilePath(null));
    }

    @Test
    public void setCommonCentsFilePath_validPath_setsCommonCentsFilePath() {
        Path path = Paths.get("commonCents/file/path");
        modelManager.setCommonCentsFilePath(path);
        assertEquals(path, modelManager.getCommonCentsFilePath());
    }

    @Test
    public void setCommonCents() {
        CommonCents commonCents = new CommonCents();
        modelManager.setCommonCents(commonCents);
        assertEquals(commonCents, modelManager.getCommonCents());
    }

    @Test
    public void hasAccount_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAccount(null));
    }

    @Test
    public void hasAccount_accountNotInCommonCents_returnsFalse() {
        assertFalse(modelManager.hasAccount(new Account(new Name("ALICE"))));
    }

    @Test
    public void hasAccount_accountInCommonCents_returnsTrue() {
        Account account = new Account(new Name("ALICE"));
        modelManager.addAccount(account);
        assertTrue(modelManager.hasAccount(account));
    }

    @Test
    public void deleteAccount_accountNotInCommonCents_throwsAccountNotFoundException() {
        Account account = new Account(new Name("ALICE"));
        assertThrows(AccountNotFoundException.class, () -> modelManager.deleteAccount(account));
    }

    @Test
    public void deleteAccount_accountInCommonCents_returnsTrue() {
        Account account = new Account(new Name("ALICE"));
        modelManager.addAccount(account);
        modelManager.deleteAccount(account);
        assertFalse(modelManager.hasAccount(account));
    }

    @Test
    public void addAccount_isValidAccount_returnsTrue() {
        Account account = new Account(new Name("ALICE"));
        modelManager.addAccount(account);
        assertTrue(modelManager.hasAccount(account));
    }

    @Test
    public void setAccount_nullAccount_throwsNullPointerException() {
        Account account = new Account(new Name("ALICE"));
        assertThrows(NullPointerException.class, () -> modelManager.setAccount(null, account));
        assertThrows(NullPointerException.class, () -> modelManager.setAccount(account, null));
        assertThrows(NullPointerException.class, () -> modelManager.setAccount(null, null));
    }

    @Test
    public void setAccount_validAccount_returnsTrue() {
        Account targetAccount = new Account(new Name("ALICE"));
        Account editedAccount = new Account(new Name("BEN"));

        modelManager.addAccount(targetAccount);
        modelManager.setAccount(targetAccount, editedAccount);
        assertTrue(modelManager.hasAccount(editedAccount));
    }

    @Test
    public void setAccount_nullEditedAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAccount(null));
    }

    @Test
    public void setAccount_validEditedAccount_returnsTrue() {
        Account editedAccount = new Account(new Name("ALICE"));
        modelManager.addAccount(editedAccount);
        modelManager.setAccount(editedAccount);
        assertTrue(modelManager.hasAccount(editedAccount));
    }

    @Test
    public void getFilteredAccountList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAccountList().remove(0));
    }

    @Test
    void updateFilteredAccountList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredAccountList(null));
    }

    @Test
    public void equals() {
        Account account = new Account(new Name("General Account"));
        CommonCents commonCents = new CommonCents();
        commonCents.addAccount(account);
        CommonCents differentCommonCents = new CommonCents();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(commonCents, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(commonCents, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different commonCents -> returns false
        assertNotEquals(modelManager, new ModelManager(differentCommonCents, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAccountList(Model.PREDICATE_SHOW_ALL_ACCOUNTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setCommonCentsFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(commonCents, differentUserPrefs));
    }
}
