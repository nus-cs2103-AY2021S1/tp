package seedu.address.commons.core;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class LogsCenterTest {

    private LogsCenter logsCenter;

    @Test
    void setUp() {
        Config config = new Config();
        config.setLogLevel(Level.INFO);
        config.setUserPrefsFilePath(Paths.get("test.json"));
        logsCenter = new LogsCenter();
        LogsCenter.init(config);
    }
}
