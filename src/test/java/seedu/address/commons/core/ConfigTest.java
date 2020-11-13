package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigTest {

    private Config config;
    @BeforeEach
    void setUp() {
        config = new Config();
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    void getLogLevelTest() {
        Level logLevel = Level.INFO;
        config.setLogLevel(logLevel);
        assertEquals(logLevel, config.getLogLevel());
    }

    @Test
    void getUserPrefFilePath() {
        Path expected = Paths.get("test.json");
        config.setUserPrefsFilePath(expected);
        assertEquals(expected, config.getUserPrefsFilePath());
    }

    @Test
    void equals_nullinput_false() {
        assertFalse(config.equals(null));
    }

    @Test
    void hashCodeTest() {
        Level logLevel = Level.INFO;
        Path path = Paths.get("test.json");
        config.setLogLevel(logLevel);
        config.setUserPrefsFilePath(path);
        assertEquals(Objects.hash(logLevel, path), config.hashCode());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
    }


}
