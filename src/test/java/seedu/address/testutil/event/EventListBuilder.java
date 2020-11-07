package seedu.address.testutil.event;


import seedu.address.model.EventList;
import seedu.address.model.event.Event;

/**
 * A utility class to help with building EventList objects.
 * Example usage: <br>
 *     {@code ModuleList moduleList = new ModuleList().withModule(CS2103T).build();}
 */
public class EventListBuilder {

    private EventList eventList;

    public EventListBuilder() {
        eventList = new EventList();
    }

    public EventListBuilder(EventList eventList) {
        this.eventList = eventList;
    }

    /**
     * Adds a new {@code Module} to the {@code ModuleList} that we are building.
     */
    public EventListBuilder withEvent(Event event) {
        eventList.addEvent(event);
        return this;
    }

    public EventList build() {
        return eventList;
    }

}
