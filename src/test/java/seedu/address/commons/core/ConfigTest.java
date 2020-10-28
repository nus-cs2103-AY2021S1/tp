package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equals() {
        Config defaultConfig = new Config();
        Config defaultConfigCopy = new Config();
        Config differentConfig = new Config();

        differentConfig.setUserPrefsFilePath(Paths.get("nonexistentFile.json"));

        assertNotNull(defaultConfig);

        // same object -> returns true
        assertEquals(defaultConfig, defaultConfig);

        // different type -> returns false
        assertNotEquals(null, defaultConfig);
        assertNotEquals(1, defaultConfig);

        // same value -> returns true
        assertEquals(defaultConfig, defaultConfigCopy);

        // differentUserPrefs path -> returns false
        assertNotEquals(defaultConfig, differentConfig);
    }

    @Test
    public void hashCode_test() {
        Config defaultConfig = new Config();
        Config defaultConfigCopy = new Config();
        Config differentConfig = new Config();

        differentConfig.setUserPrefsFilePath(Paths.get("nonexistentFile.json"));

        // same object -> same hashcode
        assertEquals(defaultConfig.hashCode(), defaultConfig.hashCode());

        // same value -> same hashcode
        assertEquals(defaultConfig.hashCode(), defaultConfigCopy.hashCode());

        // different value -> different hashcode
        assertNotEquals(defaultConfig.hashCode(), differentConfig.hashCode());
    }

}
