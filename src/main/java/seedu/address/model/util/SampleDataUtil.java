package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.Reeve;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.Admin;


/**
 * Contains utility methods for populating {@code Reeve} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new School("NUS High School"),
                    new Year("4"), Admin.getPlaceholder()) // please fully implement an Admin
        };
    }

    public static ReadOnlyReeve getSampleAddressBook() {
        Reeve sampleAb = new Reeve();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a {@code AdditionalDetail} set containing the list of given strings.
     */
    public static Set<AdditionalDetail> getDetailSet(String... strings) {
        return Arrays.stream(strings)
                .map(AdditionalDetail::new)
                .collect(Collectors.toSet());
    }

}
