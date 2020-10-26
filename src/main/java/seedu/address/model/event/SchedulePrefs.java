package seedu.address.model.event;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class SchedulePrefs {
    private ScheduleViewMode viewMode;
    private LocalDateTime targetViewDateTime;

    public SchedulePrefs(ScheduleViewMode viewMode, LocalDateTime targetViewDateTime) {
        requireAllNonNull(viewMode, targetViewDateTime);
        this.viewMode = viewMode;
        this.targetViewDateTime = targetViewDateTime;
    }

    public ScheduleViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ScheduleViewMode viewMode) {
        requireNonNull(viewMode);
        this.viewMode = viewMode;
    }

    public LocalDateTime getTargetViewDateTime() {
        return targetViewDateTime;
    }

    public void setTargetViewDateTime(LocalDateTime targetViewDateTime) {
        requireNonNull(targetViewDateTime);
        this.targetViewDateTime = targetViewDateTime;
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
        return otherPrefs.getTargetViewDateTime().equals(getTargetViewDateTime())
                && otherPrefs.getViewMode().equals(getViewMode());
    }

    /**
     * Formats the event schedule pref object into a printable string format
     * @return a string representation of the event schedule prefs object
     */
    @Override
    public String toString() {
        return viewMode.name() + "_" + targetViewDateTime.toLocalDate().toString();
    }
}
