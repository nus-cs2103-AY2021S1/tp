package seedu.address.testutil;

import seedu.address.model.GradeBook;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Gradebook objects.
 * Example usage: <br>
 *     {@code GradeBook ab = new GradeBookBuilder().withModule("CS2103").build();}
 */
public class GradeBookBuilder {

    private GradeBook gradeBook;

    public GradeBookBuilder() {
        gradeBook = new GradeBook();
    }

    public GradeBookBuilder(GradeBook gradeBook) {
        this.gradeBook = gradeBook;
    }

    /**
     * Adds a new {@code Module} to the {@code GradeBook} that we are building.
     */
    public GradeBookBuilder withModule(Module module) {
        gradeBook.addModule(module);
        return this;
    }

    public GradeBook build() {
        return gradeBook;
    }
}
