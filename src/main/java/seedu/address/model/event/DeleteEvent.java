package seedu.address.model.event;

import java.time.LocalDateTime;

/**
 * This class is used to support deleting events in the schedule.
 *
 */
public class DeleteEvent extends Event {

    private static final String placeHolderUniqueIdentifier = LocalDateTime.now().toString();
    private static final EventRecurrence placeHolderEventRecur = EventRecurrence.NONE;

    public DeleteEvent(String eventName, LocalDateTime eventStartDateTime, LocalDateTime eventEndDateTime) {
        this(eventName, eventStartDateTime, eventEndDateTime, placeHolderUniqueIdentifier, placeHolderEventRecur);
    }

    /**
     * Creates a deleteEvent object that will be used to check with the schedule's event.
     */
    private DeleteEvent(String eventName, LocalDateTime eventStartDateTime, LocalDateTime eventEndDateTime,
                       String uniqueIdentifier, EventRecurrence recurrence) {
        super(eventName, eventStartDateTime, eventEndDateTime, uniqueIdentifier, recurrence);
    }
}
