package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class LogsCenterTest {

    private final LogsCenter defaultLogsCenter = new LogsCenter();

    @Test
    public void init_validTest() {
        Config defaultConfig = new Config();
        defaultLogsCenter.init(defaultConfig);
        Level expected = defaultConfig.getLogLevel();
        Level actual = defaultLogsCenter.getCurrentLogLevel();
        assertEquals(expected, actual);
    }

    @Test
    public void getLogger_invalidTest() {
        Logger expected = Logger.getLogger("");
        Logger actual = LogsCenter.getLogger((Class<Object>) null);
        assertEquals(expected, actual);
    }
}
