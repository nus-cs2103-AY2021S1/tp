package seedu.address.model.util;

import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.module.Module;

/**
 * Contains utility methods for populating {@code GradeBook} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModule() {
        return new Module[] {

        };
    }

    public static ReadOnlyGradeBook getSampleGradeBook() {
        GradeBook sampleAb = new GradeBook();
        for (Module sampleModule : getSampleModule()) {
            sampleAb.addModule(sampleModule);
        }
        return sampleAb;
    }
}
