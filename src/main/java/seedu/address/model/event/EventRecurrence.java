package seedu.address.model.event;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;

public enum EventRecurrence {
    DAILY("daily", "FREQ=DAILY;INTERVAL=1"),
    WEEKLY("weekly", "FREQ=WEEKLY;INTERVAL=1"),
    NONE("none", "FREQ=YEARLY;INTERVAL=1");

    private String recurrenceString;
    private String VEventRecurRule;

    EventRecurrence(String recurrenceString, String VEventRecurRule) {
        this.recurrenceString = recurrenceString;
        this.VEventRecurRule = VEventRecurRule;
    }

    public static EventRecurrence checkWhichRecurrence(String input) throws ParseException {
        assert input != null;
        String formattedInput = input.trim().toLowerCase();

        for (EventRecurrence type: EventRecurrence.values()) {
            if (type.recurrenceString.equals(formattedInput)) {
                return type;
            }
        }
        throw new ParseException("Event recurrence is incorrect");
    }

    public static EventRecurrence checkWhichRecurRule(String input) throws ParseException {
        assert input != null;
        for (EventRecurrence type: EventRecurrence.values()) {
            if (type.VEventRecurRule.equals(input)) {
                return type;
            }
        }
        throw new ParseException("Recurrence rule is incorrect");
    }

    public String getVEventRecurRule() {
        return this.VEventRecurRule;
    }
}
