package seedu.address.model.assignment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Task {
    // Identity fields
    private final Name name;
    private final Deadline time;
    private final ModuleCode moduleCode;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline time, ModuleCode moduleCode) {
        requireAllNonNull(name, time, moduleCode);
        this.name = name;
        this.time = time;
        this.moduleCode = moduleCode;
    }

    public Name getName() {
        return name;
    }

    public Deadline getTime() {
        return time;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }
}
