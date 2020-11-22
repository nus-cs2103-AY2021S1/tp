package nustorage.model;

import static nustorage.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void getInventoryFilePath_returnsTrue() {
        UserPrefs userPref = new UserPrefs();
        assertTrue(userPref.getInventoryFilePath().toString().contains("inventory"));
    }

    @Test
    public void getFinanceAccountFilePath_returnsTrue() {
        UserPrefs userPref = new UserPrefs();
        assertTrue(userPref.getFinanceAccountFilePath().toString().contains("financeAccount"));
    }
}
