package seedu.jarvis.model.topic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TopicTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Topic(null, null));
    }

    @Test
    public void getOutline() {
        Topic topic = new Topic("11", "Streams");
        assertTrue(topic.getOutline().equals("Streams"));
    }

    @Test
    public void getWeek() {
        Topic topic = new Topic("2", "Primitives");
        assertTrue(topic.getWeek().equals("2"));
    }

    @Test
    public void isEmpty_emptyTopic_returnsTrue() {
        Topic topic = new Topic();
        assertTrue(topic.isEmpty());
    }

    @Test
    public void isEmpty_notEmptyTopic_returnsFalse() {
        Topic topic = new Topic("5", "Lists");
        assertFalse(topic.isEmpty());
    }
}
