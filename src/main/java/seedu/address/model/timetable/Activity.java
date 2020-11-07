package seedu.address.model.timetable;

import static java.util.Objects.requireNonNull;

import seedu.address.model.util.Name;

public abstract class Activity {

    private static final Activity EMPTY_ACTIVITY = new Activity(new Name("Empty Activity")) {
        @Override
        public boolean isSameActivity(Activity otherActivity) {
            return false;
        }

        @Override
        public boolean isRoutine() {
            return false;
        }
    };

    protected final Name name;

    /**
     * Constructs an Activity object.
     * @param name The name of the activity.
     */
    public Activity(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    public static Activity empty() {
        return EMPTY_ACTIVITY;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns true if both activities of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two activities.
     */
    public abstract boolean isSameActivity(Activity otherActivity);

    public abstract boolean isRoutine();
}
