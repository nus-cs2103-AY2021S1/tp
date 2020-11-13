package seedu.jarvis.model.topic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TopicsTest {
    @Test
    void getTopic_validWeek_returnsTopic() {
        Topics topics = new Topics();
        assertFalse(topics.getTopic(2).isEmpty());
    }

    @Test
    void getTopic_invalidWeek_returnsTopic() {
        Topics topics = new Topics();
        assertTrue(topics.getTopic(12).isEmpty());
    }
}
