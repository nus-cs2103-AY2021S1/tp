package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Gender("M"),
                getStudentGroupSet("badminton"), new Block("C"),
                new Room("205"), new MatriculationNumber("A0123456B")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Gender("F"),
                getStudentGroupSet("basketball", "dance"), new Block("B"),
                    new Room("405"), new MatriculationNumber("A0123456C")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Gender("F"),
                getStudentGroupSet("hackers"), new Block("A"), new Room("310"),
                    new MatriculationNumber("A0123456D")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Gender("M"),
                getStudentGroupSet("soccer"), new Block("D"),
                new Room("109"), new MatriculationNumber("A0123456E")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Gender("M"),
                getStudentGroupSet("choir"), new Block("C"), new Room("210"),
                    new MatriculationNumber("A0123456F")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Gender("M"),
                getStudentGroupSet("tableTennis"), new Block("B"), new Room("305"),
                new MatriculationNumber("A0123456G"))
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new EventName("Hall Lunch"), new EventDate("01/11/2020 13:00"),
                    new Location("Dining Hall"), new Description("Hall-wide lunch event.")),
            new Event(new EventName("Hall Dinner"), new EventDate("24/11/2020 18:00"),
                    new Location("Dining Hall"), new Description("Hall-wide dinner event."))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

    /**
     * Returns a student group set containing the list of strings given.
     */
    public static Set<StudentGroup> getStudentGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(StudentGroup::new)
                .collect(Collectors.toSet());
    }

}
