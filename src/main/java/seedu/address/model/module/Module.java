package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.semester.Semester;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModuleName moduleName;

    // Data fields
    private Grade grade;
    private ModularCredit modularCredit;
    private boolean hasGrade;
    private Semester semester;

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleName moduleName, Grade grade, ModularCredit modularCredit, Semester semester) {
        requireAllNonNull(moduleName, grade);
        this.moduleName = moduleName;
        this.modularCredit = modularCredit;
        this.grade = grade;
        this.semester = semester;
        hasGrade = true;
    }

    /**
     * Only grade field can be empty.
     */
    public Module(ModuleName moduleName, ModularCredit modularCredit, Semester semester) {
        this.moduleName = moduleName;
        this.modularCredit = modularCredit;
        this.semester = semester;
        hasGrade = false;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public ModularCredit getModularCredit() {
        return modularCredit;
    }

    public Grade getGrade() {
        if (hasGrade) {
            return grade;
        } else {
            return new Grade(Cap.getEmptyGrade());
        }
    }

    public Semester getSemester() {
        return semester;
    }

    public boolean hasGrade() {
        return hasGrade && !grade.toString().equals(Cap.getEmptyGrade());
    }

    /**
     * Returns true if both module of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleName().equals(getModuleName());
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleName().equals(getModuleName())
                && otherModule.getGrade().equals(getGrade());
    }

    @Override
    public int hashCode() {
        // Use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, grade);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleName())
                .append(" | Grade: ")
                .append(getGrade())
                .append(" | Semester: ")
                .append(getSemester());
        return builder.toString();
    }

}
