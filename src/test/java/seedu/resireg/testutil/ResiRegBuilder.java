package seedu.resireg.testutil;

import seedu.resireg.model.ResiReg;
import seedu.resireg.model.student.Student;

/**
 * A utility class to help with building ResiReg objects.
 * Example usage: <br>
 * {@code ResiReg rr = new ResiRegBuilder().withStudent("John", "Doe").build();}
 */
public class ResiRegBuilder {

    private ResiReg resiReg;

    public ResiRegBuilder() {
        resiReg = new ResiReg();
    }

    public ResiRegBuilder(ResiReg resiReg) {
        this.resiReg = resiReg;
    }

    /**
     * Adds a new {@code Student} to the {@code ResiReg} that we are building.
     */
    public ResiRegBuilder withStudent(Student student) {
        resiReg.addStudent(student);
        return this;
    }

    public ResiReg build() {
        return resiReg;
    }
}
