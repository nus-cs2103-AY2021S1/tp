package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    private final Config defaultConfig = new Config();

    @Test
    public void getLogLevel_returnsSetLogLevel() {
        defaultConfig.setLogLevel(Level.WARNING);
        assertEquals(Level.WARNING, defaultConfig.getLogLevel());
    }

    @Test
    public void getUserPrefsFilePath_returnsSetUserPrefsFilePath() {
        defaultConfig.setUserPrefsFilePath(Paths.get("prefs.json"));
        assertEquals(Paths.get("prefs.json"), defaultConfig.getUserPrefsFilePath());
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, defaultConfig.toString());
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(defaultConfig);

        // different log -> not equal
        Config differentLog = new Config();
        differentLog.setLogLevel(Level.WARNING);
        assertFalse(defaultConfig.equals(differentLog));

        // different path -> not equal
        Config differentPath = new Config();
        differentPath.setUserPrefsFilePath(Paths.get("different path"));
        assertFalse(defaultConfig.equals(differentPath));
    }

    @Test
    public void hashCode_test() {
        assertEquals(defaultConfig.hashCode(), defaultConfig.hashCode());

        // different log -> not equal
        Config differentLog = new Config();
        differentLog.setLogLevel(Level.WARNING);
        assertNotEquals(defaultConfig.hashCode(), differentLog.hashCode());

        // different path -> not equal
        Config differentPath = new Config();
        differentPath.setUserPrefsFilePath(Paths.get("different path"));
        assertNotEquals(defaultConfig.hashCode(), differentPath.hashCode());
    }
}
