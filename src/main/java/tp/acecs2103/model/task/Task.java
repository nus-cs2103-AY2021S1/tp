package tp.acecs2103.model.task;

import java.time.LocalDate;
import java.util.logging.Logger;

import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.commons.util.CollectionUtil;
import tp.acecs2103.model.exceptions.InvalidTaskOperationException;

/**
 * Represents a general task in Ace CS2103/T.
 */
public class Task implements Comparable<Task> {
    private static final Logger logger = LogsCenter.getLogger(Task.class);

    private Index index;
    private WeekNumber weekNumber;
    private Description description;
    private OfficialDeadline officialDeadline;
    private CustomizedDeadline customizedDeadline;
    private Remark remark;
    private TaskCategory category;
    private boolean customized;
    private boolean doneStatus;

    /**
     * Creates a {@code Task} with given details.
     */
    public Task(Index index, WeekNumber weekNumber,
                Description description, OfficialDeadline officialDeadline,
                CustomizedDeadline customizedDeadline, Remark remark, boolean customized,
                boolean doneStatus) {
        CollectionUtil.requireAllNonNull(index, weekNumber, description);
        this.index = index;
        this.weekNumber = weekNumber;
        this.description = description;
        this.officialDeadline = officialDeadline;
        this.customizedDeadline = customizedDeadline;
        this.remark = remark;
        this.category = TaskCategory.TASK;
        this.customized = customized;
        this.doneStatus = doneStatus;
    }

    /**
     * Gets the task index.
     */
    public Index getIndex() {
        return index;
    }

    /**
     * Gets the week number implied by the task index.
     */
    public WeekNumber getWeekNumberFromIndex() {
        String i = index.value;
        String weekNumber = i.length() == 4 ? i.substring(1, 2) : i.substring(1, 3);
        return new WeekNumber(weekNumber);
    }

    /**
     * Gets the week number.
     */
    public WeekNumber getWeekNumber() {
        return weekNumber;
    }

    /**
     * Gets the task description.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Gets the official deadline of task.
     */
    public OfficialDeadline getOfficialDeadline() {
        return officialDeadline;
    }

    /**
     * Gets the customized deadline of task.
     */
    public CustomizedDeadline getCustomizedDeadline() {
        return customizedDeadline;
    }

    /**
     * Gets the remark of the task.
     */
    public Remark getRemark() {
        return remark;
    }

    /**
     * Gets the category of the task.
     */
    public TaskCategory getCategory() {
        if (this instanceof IP) {
            return TaskCategory.IP;
        } else if (this instanceof TP) {
            return TaskCategory.TP;
        } else if (this instanceof Topic) {
            return TaskCategory.TOPIC;
        } else {
            return TaskCategory.ADMIN;
        }
    }

    /**
     * Checks whether the task is customized.
     */
    public boolean isCustomized() {
        return customized;
    }

    /**
     * Checks whether the task is done
     */
    public boolean isDone() {
        return doneStatus;
    }

    /**
     * Checks whether the task has index {@code taskIndex}
     */
    public boolean hasIndex(Index taskIndex) {
        return this.index.equals(taskIndex);
    }

    /**
     * Checks whether the task has customized deadline.
     */
    public boolean hasDeadline() {
        return customizedDeadline != null;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        assert !doneStatus;
        doneStatus = true;
    }

    /**
     * Mark the task as pending (i.e. not done yet).
     */
    public void markAsPending() {
        assert doneStatus;
        doneStatus = false;
    }

    /**
     * Sets a deadline to the task.
     * @param deadline A valid LocalDate.
     */
    public void setDeadline(CustomizedDeadline deadline) throws InvalidTaskOperationException {
        assert deadline != null;
        if (!this.isOverdue() && deadline.compareTo(this.officialDeadline) > 0) {
            throw new InvalidTaskOperationException("Customised deadline should not be later than official deadline. "
                    + "Please set the customised deadline to a date before or on: "
                    + this.officialDeadline);
        }
        customizedDeadline = deadline;
    }

    /**
     * Check whether the task a valid task, aka if all three requirements below are met:
     * 1. the official deadline is correctly set to null for a customised task;
     * 2. a customised deadline is set
     * 3. index number format is consistent with the week number, eg. 01205 for a week 12 task
     */
    public boolean isValid() {
        return (isCustomized() == (officialDeadline == null))
                && isCustomized() == (customizedDeadline != null)
                && getWeekNumber().equals(getWeekNumberFromIndex());
    }

    /**
     * Check whether the task is in week {@code weekIndex}.
     */
    public boolean isWeekX(WeekNumber weekIndex) {
        return weekNumber.equals(weekIndex);
    }

    /**
     * Checks whether the description and remark of task contains certain key word.
     */
    public boolean contains(String keyword) {
        keyword = keyword.toLowerCase();

        if (description != null && remark == null) {
            return description.value.toLowerCase().contains(keyword);
        }
        return description.value.toLowerCase().contains(keyword)
                || remark.value.toLowerCase().contains(keyword);

    }

    /**
     * Checks whether the task is same task as {@code task}.
     */
    public boolean isSameTask(Task task) {
        return index.equals(task.getIndex());
    }

    /**
     * Checks is a task if overdue (i.e. passed ddl but have not done yet )
     */
    public boolean isOverdue() {
        if (getOfficialDeadline() != null && getOfficialDeadline().getTimeInfo() != null) {
            return getOfficialDeadline().getTimeInfo().compareTo(LocalDate.now()) < 0;
        } else if (getCustomizedDeadline() != null && getCustomizedDeadline().getTimeInfo() != null) {
            return getCustomizedDeadline().getTimeInfo().compareTo(LocalDate.now()) < 0;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Task o) {
        Deadline ddl1 = getOfficialDeadline() == null ? getCustomizedDeadline() : getOfficialDeadline();
        assert ddl1 != null;
        Deadline ddl2 = o.getOfficialDeadline() == null ? o.getCustomizedDeadline() : o.getOfficialDeadline();
        assert ddl1 != null;
        assert ddl2 != null;
        return ddl1.compareTo(ddl2);
    }

    @Override
    public String toString() {
        String returnString = "[Week " + this.getWeekNumber().value + "] " + this.getCategory() + " Task "
                              + this.getIndex().value + " with description: " + this.getDescription().value;
        if (officialDeadline != null) {
            returnString += "\nOfficial deadline: " + officialDeadline.value;
        }
        if (customizedDeadline != null) {
            returnString += "\nCustomized deadline: " + customizedDeadline.value;
        }
        if (remark != null) {
            returnString += "\nRemark: " + remark.value;
        }
        return returnString;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && index.equals(((Task) other).index)
                && weekNumber.equals(((Task) other).weekNumber)
                && description.equals(((Task) other).description));
    }
}
