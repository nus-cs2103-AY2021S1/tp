package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Task {
    // Identity fields
    private final Name name;
    private final Time time;
    private final ModuleCode moduleCode;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Time time, ModuleCode moduleCode) {
        requireAllNonNull(name, time, moduleCode);
        this.name = name;
        this.time = time;
        this.moduleCode = moduleCode;
    }

    public Name getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Checks if task is the same.
     * @param otherTask Another task.
     * @return True if task is the same, false otherwise.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getModuleCode().equals(getModuleCode())
                && (otherTask.getTime().equals(getTime()));
    }
}
