package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Tasker;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.consultation.Address;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Day;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Type;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Tasker} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Telegram("alexyeoh"),
                new MatricNumber("A1234567A"),
                getTagSet("F11"),
                new TreeSet<>()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Telegram("berniceyu"),
                new MatricNumber("A0001111B"),
                getTagSet("F11"),
                new TreeSet<>()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Telegram("charlotte"),
                new MatricNumber("A1111000C"),
                getTagSet("F11"),
                new TreeSet<>()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Telegram("david"),
                new MatricNumber("A7654321D"),
                getTagSet("F11"),
                new TreeSet<>()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Telegram("irfan"),
                new MatricNumber("A7654321E"),
                getTagSet("F11"),
                new TreeSet<>()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Telegram("royba"),
                new MatricNumber("A1234123F"),
                getTagSet("F11"),
                new TreeSet<>())
        };
    }

    public static Consultation[] getSampleConsultations() {
        return new Consultation[] {
            new Consultation(new Name("Alex Yeoh"), Day.fromDateString("24/10/2020"), Time.fromTimeString("18:00"),
                    new Address("SOC Basement"),
                    new Type("personal")),
            new Consultation(new Name("Alex Yeoh"), Day.fromDateString("21/10/2020"), Time.fromTimeString("10:00"),
                    new Address("SOC Basement 3"),
                    new Type("group"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        Tasker sampleAb = new Tasker();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Consultation sampleConsultation: getSampleConsultations()) {
            sampleAb.addConsultation(sampleConsultation);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a sorted {@code Attendance} sorted set containing the list of strings given.
     */
    public static SortedSet<Attendance> getAttendanceSet(String... dateStrings) {
        return new TreeSet<>(Arrays.stream(dateStrings)
                .map(Attendance::fromDateString)
                .collect(Collectors.toSet()));
    }

}
