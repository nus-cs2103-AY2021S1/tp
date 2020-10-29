package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

public class SchedulePrefs {
    private ScheduleViewMode viewMode;
    private LocalDateTime viewDateTime;

    /**
     * Creates a schedule preferences which encompass the user's preferred view mode and view date time.
     * @param viewMode
     * @param viewDateTime
     */
    public SchedulePrefs(ScheduleViewMode viewMode, LocalDateTime viewDateTime) {
        requireAllNonNull(viewMode, viewDateTime);
        this.viewMode = viewMode;
        this.viewDateTime = viewDateTime;
    }

    public ScheduleViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ScheduleViewMode viewMode) {
        requireNonNull(viewMode);
        this.viewMode = viewMode;
    }

    public LocalDateTime getViewDateTime() {
        return viewDateTime;
    }

    public void setViewDateTime(LocalDateTime viewDateTime) {
        requireNonNull(viewDateTime);
        this.viewDateTime = viewDateTime;
    }

    /**
     * Returns true if both SchedulePrefs have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SchedulePrefs)) {
            return false;
        }

        SchedulePrefs otherPrefs = (SchedulePrefs) other;
        return otherPrefs.getViewDateTime().equals(getViewDateTime())
                && otherPrefs.getViewMode().equals(getViewMode());
    }

    /**
     * Formats the event schedule pref object into a printable string format
     * @return a string representation of the event schedule prefs object
     */
    @Override
    public String toString() {
        return viewMode.name() + "_" + viewDateTime.toLocalDate().toString();
    }
}
