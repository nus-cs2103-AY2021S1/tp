package seedu.address.model.util;

import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.Reeve;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.Admin;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new School("NUS High School"),
                    new Year("Year 4"), new Admin())
//                    new Year("Year 4"),
//                    new ClassVenue("Blk 30 Geylang Street 29, #06-40"),
//                    new ClassTime("1 1500 -1700"),
//                    new AdditionalDetail("He's quiet"),
//                    new MeetingLink("alexyeoh@example.com"),
//                    new Subject("Mathematics"),
//                getTagSet("friends"))
        };
    }

    public static ReadOnlyReeve getSampleAddressBook() {
        Reeve sampleAb = new Reeve();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addPerson(sampleStudent);
        }
        return sampleAb;
    }

}
