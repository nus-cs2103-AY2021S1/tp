package seedu.address.model.event;

import jfxtras.icalendarfx.components.VEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;

public class MapperTest {

    @Test
    public void mapEventToVEvent_validEvent_returnsVEvent() {
        Event event = (ALICE_CLASS_EVENT);
        VEvent expectedVEvent = new VEvent();
        expectedVEvent.withSummary(ALICE_CLASS_EVENT.getEventName())
                .withDateTimeStart(ALICE_CLASS_EVENT.getEventStartDateTime())
                .withDateTimeEnd(ALICE_CLASS_EVENT.getEventEndDateTime())
                .withUniqueIdentifier(ALICE_CLASS_EVENT.getUniqueIdentifier())
                .withRecurrenceRule(ALICE_CLASS_EVENT.getRecurrence().getVEventRecurRule());

        VEvent mappedVEvent = Mapper.mapEventToVEvent(event);
        assertEquals(expectedVEvent, mappedVEvent);
    }

    @Test
    public void mapVEventToEvent_validVEvent_returnEvent() {
        VEvent vEvent = new VEvent();
        vEvent.withSummary(ALICE_CLASS_EVENT.getEventName())
                .withDateTimeStart(ALICE_CLASS_EVENT.getEventStartDateTime())
                .withDateTimeEnd(ALICE_CLASS_EVENT.getEventEndDateTime())
                .withUniqueIdentifier(ALICE_CLASS_EVENT.getUniqueIdentifier())
                .withRecurrenceRule(ALICE_CLASS_EVENT.getRecurrence().getVEventRecurRule());

        Event expectedEvent = ALICE_CLASS_EVENT;

        Event mappedEvent = Mapper.mapVEventToEvent(vEvent);
        assertEquals(expectedEvent, mappedEvent);

    }

}
