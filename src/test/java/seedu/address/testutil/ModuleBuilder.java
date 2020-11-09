package seedu.address.testutil;

import seedu.address.model.module.Grade;
import seedu.address.model.module.ModularCredit;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.semester.Semester;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_NAME = "CS2103T";
    public static final String DEFAULT_GRADE = "A+";
    public static final int DEFAULT_MODULAR_CREDIT = 4;
    public static final Semester DEFAULT_SEMESTER = Semester.Y1S1;

    private ModuleName moduleName;
    private Grade grade;
    private ModularCredit modularCredit;
    private Semester semester;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        grade = new Grade(DEFAULT_GRADE);
        modularCredit = new ModularCredit(DEFAULT_MODULAR_CREDIT);
        semester = DEFAULT_SEMESTER;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getModuleName();
        grade = moduleToCopy.getGrade();
        modularCredit = moduleToCopy.getModularCredit();
        semester = moduleToCopy.getSemester();
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Module} that we are building.
     */
    public ModuleBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Sets the {@code ModularCredit} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModularCredit(int modularCredit) {
        this.modularCredit = new ModularCredit(modularCredit);
        return this;
    }

    /**
     * Sets the {@code Semester} of the {@code Module} that we are building.
     */
    // not sure if parameter is a string or semester
    public ModuleBuilder withSemester(String semester) {
        this.semester = Semester.valueOf(semester);
        return this;
    }

    public Module build() {
        return new Module(moduleName, grade, modularCredit, semester);
    }

}
