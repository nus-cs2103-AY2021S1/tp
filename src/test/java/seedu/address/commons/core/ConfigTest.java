package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    private final Config defaultConfig = new Config();

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod() {
        assertNotNull(defaultConfig);
        assertEquals(defaultConfig, defaultConfig);
    }

    @Test
    public void equalsMethodDifferentValues_test() {
        GuiSettings testGui = new GuiSettings();
        assertFalse(defaultConfig.equals(testGui));
    }

    @Test
    public void getLogLevel_validTest() {
        Level expected = Level.INFO;
        Level actual = defaultConfig.getLogLevel();
        assertEquals(expected, actual);
    }

    @Test
    public void getUserPrefsFilePath_validTest() {
        Path expected = Paths.get("preferences.json");
        Path actual = defaultConfig.getUserPrefsFilePath();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCode_validTest() {
        int expected = Objects.hash(defaultConfig.getLogLevel(), defaultConfig.getUserPrefsFilePath());
        int actual = defaultConfig.hashCode();
        assertEquals(expected, actual);
    }
}
