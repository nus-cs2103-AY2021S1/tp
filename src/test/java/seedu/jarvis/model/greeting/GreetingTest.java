package seedu.jarvis.model.greeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GreetingTest {
    @Test
    void constructor_createsDefaultGreeting() {
        Greeting greeting = new Greeting();
        assertTrue(greeting.toString().equals(""));
    }

    @Test
    void isEmpty_emptyGreeting_returnsTrue() {
        Greeting greeting = new Greeting();

        // When greeting is empty string
        assertTrue(greeting.isEmpty());

        // When greeting is default message
        greeting.setGreeting("Hi! Please log in to continue");
        assertTrue(greeting.isEmpty());
    }

    @Test
    void setGreeting_validString_success() {
        Greeting greeting = new Greeting();
        greeting.setGreeting("Wu Peirong");
        assertTrue(greeting.getGreeting().getValue().equals("Welcome, Wu Peirong!"));
    }

    @Test
    void padWithIntro() {
        Greeting greeting = new Greeting();
        assertTrue(greeting.padWithIntro("Wu Peirong").equals("Welcome, Wu Peirong!"));
    }

    @Test
    void testEquals() {
        Greeting greeting1 = new Greeting();
        greeting1.setGreeting("Alex Yeoh");

        Greeting greeting2 = new Greeting();
        greeting2.setGreeting("Alex Yeoh");

        Greeting greeting3 = new Greeting();
        greeting3.setGreeting("");

        // same values -> return true
        assertTrue(greeting1.equals(greeting2));

        // same object -> return true
        assertTrue(greeting1.equals(greeting1));

        // null -> returns false
        assertFalse(greeting1.equals(null));

        // different type -> returns false
        assertFalse(greeting1.equals(1));

        // different greeting message -> returns false
        assertFalse(greeting1.equals(greeting3));
    }
}
