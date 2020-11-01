package trackitnus.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import trackitnus.commons.util.CollectionUtil;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;


/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    public static final String TYPE = "T";
    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should be in the format dd/MM/yyyy or dd/MM/yyyy hh:mm";
    public static final String WEIGHTAGE_MESSAGE_CONSTRAINTS = "Weightage should be in the"
        + " form of a floating point number";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Name name;
    private final LocalDate date;
    private final Code code;
    private final String remark;

    /**
     * name, date & remark must be present and not null
     *
     * @param name
     * @param date
     * @param remark
     */
    public Task(Name name, LocalDate date, Code code, String remark) {
        CollectionUtil.requireAllNonNull(name, date, remark);
        this.name = name;
        this.date = date;
        this.code = code;
        this.remark = remark;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean belongsToModule(Code code) {
        return getCode().isPresent() && getCode().get().equals(code);
    }

    public String getRemark() {
        return remark;
    }

    public Optional<Code> getCode() {
        return Optional.ofNullable(code);
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.name.equals(name)
            && otherTask.date.equals(date)
            && otherTask.getCode().equals(getCode())
            && otherTask.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, code, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Date: ")
            .append(getDate());
        if (getCode().isPresent()) {
            builder.append(" Code: ").append(getCode().get());
        }
        if (!getRemark().equals("")) {
            builder.append(" Remark: ").append(getRemark());
        }
        return builder.toString();
    }

    /**
     * Returns true if the two tasks are the same
     * This methods is here for to act as a compatibility layer for UniqueTaskList
     */
    public boolean isSameTask(Task task) {
        return this.equals(task);
    }
}
