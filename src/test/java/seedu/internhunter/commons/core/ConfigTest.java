package seedu.internhunter.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigTest {

    private Config defaultConfig;

    @BeforeEach
    public void setUp() {
        defaultConfig = new Config();
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equals() {
        assertNotNull(defaultConfig);

        // same object -> return true
        assertTrue(defaultConfig.equals(defaultConfig));

        // same values -> return true
        assertTrue(defaultConfig.equals(new Config()));

        // null -> return false
        assertFalse(defaultConfig.equals(null));

        // different types -> return false
        assertFalse(defaultConfig.equals(0.5f));

        // different config level -> return false
        Config configDifferentLevel = new Config();
        configDifferentLevel.setLogLevel(Level.FINEST);
        assertFalse(defaultConfig.equals(configDifferentLevel));

        // different user prefs file path
        Config configDifferentUserPrefsFilePath = new Config();
        Path path = Paths.get("random");
        configDifferentUserPrefsFilePath.setUserPrefsFilePath(path);
        assertFalse(defaultConfig.equals(configDifferentUserPrefsFilePath));
    }

    @Test
    public void hashCode_equals() {
        assertNotNull(defaultConfig);
        assertEquals(defaultConfig.hashCode(), new Config().hashCode());
        assertEquals(defaultConfig.hashCode(), defaultConfig.hashCode());
    }

    @Test
    public void getLogLevel_equals() {
        assertEquals(defaultConfig.getLogLevel(), Level.INFO);
    }

    @Test
    public void getUserPrefsFilePath_equals() {
        assertEquals(defaultConfig.getUserPrefsFilePath(), Paths.get("preferences.json"));
    }

}
