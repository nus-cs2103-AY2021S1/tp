package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event LUNCH = new EventBuilder().withEventName("Hall Lunch")
            .withDescription("Eat lunch together").withLocation("Dining Hall")
            .withDate("01/01/2020 15:00").build();
    public static final Event DINNER = new EventBuilder().withEventName("Hall Dinner")
            .withDescription("Eat dinner together").withLocation("Dining Hall")
            .withDate("01/02/2020 15:00").build();

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(LUNCH, DINNER));
    }

}
