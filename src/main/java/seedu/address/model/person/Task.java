package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

abstract class Task {
    // Identity fields
    private final Name name;
    private final Phone time;
    private final Email moduleCode;

    Task(Name name, Phone time, Email moduleCode) {
        requireAllNonNull(name, time, moduleCode);
        this.name = name;
        this.time = time;
        this.moduleCode = moduleCode;
    }

    public Name getName() {
        return name;
    }

    public Phone getTime() {
        return time;
    }

    public Email getModuleCode() {
        return moduleCode;
    }
}
