package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Assignment[] getSampleAssignments() {
        return new Assignment[] {
            new Assignment(new Name("CS1231S Homework"), new Deadline("12-12-2020 1200"), new ModuleCode("CS2103T")),
            new Assignment(new Name("CS2103T Quiz"), new Deadline("09-10-2020 2359"), new ModuleCode("CS2100")),
            new Assignment(new Name("CS2106 Lab"), new Deadline("08-08-2020 1900"), new ModuleCode("CS2040S")),
            new Assignment(new Name("Peer review"), new Deadline("25-12-2020 1200"), new ModuleCode("CS2030")),
            new Assignment(new Name("IS1103 Mission"), new Deadline("13-10-2020 1300"), new ModuleCode("MA1521")),
            new Assignment(new Name("Oral Presentation"), new Deadline("30-04-2020 1700"), new ModuleCode("CS1010X"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Assignment sampleAssignment : getSampleAssignments()) {
            sampleAb.addAssignment(sampleAssignment);
        }
        return sampleAb;
    }

}
