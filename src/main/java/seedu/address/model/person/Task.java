package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

abstract class Task {
    // Identity fields
    private final Name name;
    private final Deadline time;
    private final ModuleCode moduleCode;

    Task(Name name, Deadline time, ModuleCode moduleCode) {
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
