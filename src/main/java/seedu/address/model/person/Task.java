package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

abstract class Task {
    // Identity fields
    private final Name name;
    private final Phone time;
    private final Address moduleCode;

    Task(Name name, Phone time, Address moduleCode) {
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

    public Address getModuleCode() {
        return moduleCode;
    }
}
