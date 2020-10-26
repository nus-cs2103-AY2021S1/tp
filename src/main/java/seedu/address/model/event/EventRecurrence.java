package seedu.address.model.event;

import seedu.address.commons.exceptions.IllegalValueException;

public enum EventRecurrence {
    DAILY("daily", "FREQ=DAILY;INTERVAL=1"),
    WEEKLY("weekly", "FREQ=WEEKLY;INTERVAL=1"),
    NONE("none", "");

    private String recurrenceString;
    private String VEventRecurRule;

    EventRecurrence(String recurrenceString, String VEventRecurRule) {
        this.recurrenceString = recurrenceString;
        this.VEventRecurRule = VEventRecurRule;
    }

    public static EventRecurrence checkWhichRecurrence(String input) throws IllegalArgumentException {
        assert input != null;
        String formattedInput = input.trim().toLowerCase();

        for (EventRecurrence type: EventRecurrence.values()) {
            if (type.recurrenceString.equals(formattedInput)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Event recurrence is incorrect");
    }

    public static EventRecurrence checkWhichRecurRule(String input) throws IllegalArgumentException {
        assert input != null;
        for (EventRecurrence type: EventRecurrence.values()) {
            if (type.VEventRecurRule.equals(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Recurrence rule is incorrect");
    }

    public String getVEventRecurRule() {
        return this.VEventRecurRule;
    }
}
