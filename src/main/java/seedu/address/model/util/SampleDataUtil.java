package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.*;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PersonBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("VIP"), 1),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("VIP"), 2),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet(), 3),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("VIP"), 4),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet(), 5),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("VVIP"), 6)
        };
    }

    public static ReadOnlyPersonBook getSamplePersonBook() {
        PersonBook sampleAb = new PersonBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    // 2103 - 2133
    public static Booking[] getSampleBookings() {
        return new Booking[] {
            new Booking(2103, 1, LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(1), true, 1),
            new Booking(2104, 2, LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(1), true, 2),
            new Booking(2105, 3, LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(1), true, 3),
            new Booking(2106, 4, LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(1), false, 4),
            new Booking(2107, 5, LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(1), false, 5),
            new Booking(2108, 6, LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(1), false, 6),
        };
    }

    public static ReadOnlyBookingBook getSampleBookingBook() {
        BookingBook sampleBb = new BookingBook();
        for (Booking sampleBooking : getSampleBookings()) {
            sampleBb.addBooking(sampleBooking);
        }
        return sampleBb;
    }

    public static RoomService[] getSampleRoomService() {
        return new RoomService[] {

        };
    }

    public static ReadOnlyRoomServiceBook getSampleRoomServiceBook() {
        RoomServiceBook sampleRsb = new RoomServiceBook();
        for (RoomService sampleRoomService : getSampleRoomService()) {
            sampleRsb.addRoomService(sampleRoomService);
        }
        return sampleRsb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
