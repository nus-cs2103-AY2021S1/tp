package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.EventList;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.contact.Contact;
// import seedu.address.model.contact.ContactName;
// import seedu.address.model.contact.Email;
// import seedu.address.model.contact.Telegram;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSamplePersons() {
        return new Contact[] {
            /*
            new Contact(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"),
                new Telegram("@alexyeoh"), getTagSet("friends")),
            new Contact(new ContactName("Bernice Yu"), new Email("berniceyu@example.com"),
                new Telegram("@bernice"), getTagSet("colleagues", "friends")),
            new Contact(new ContactName("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                new Telegram("@charlotte"), getTagSet("neighbours")),
            new Contact(new ContactName("David Li"), new Email("lidavid@example.com"),
                new Telegram("@david"), getTagSet("family")),
            new Contact(new ContactName("Irfan Ibrahim"), new Email("irfan@example.com"),
                new Telegram("@irfan"), getTagSet("classmates")),
            new Contact(new ContactName("Roy Balakrishnan"), new Email("royb@example.com"),
            new Contact(new Name("Roy Balakrishnan"), new Email("royb@example.com"),
                new Telegram("@roybala"))
                // getTagSet("colleagues"))
                new Telegram("@roybala"), getTagSet("colleagues"))
        */
        };
    }

    public static ReadOnlyContactList getSampleAddressBook() {
        ModuleList sampleAb = new ModuleList();
        for (Contact samplePerson : getSamplePersons()) {
            // sampleAb.addPerson(samplePerson);
        }
        // return sampleAb;
        return null;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyEventList getSampleEventList() {
        EventList sampleEv = new EventList();
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Important"));
        LocalDateTime dateTime = LocalDateTime.parse("10-9-2020 1200", DateTimeFormatter.ofPattern("d-M-uuuu HHmm"));
        Event sampleEventOne = new Event(new EventName("CS2100 Assignment 1"), new EventTime(dateTime), tags);
        sampleEv.addEvent(sampleEventOne);
        tags.add(new Tag("Urgent"));
        dateTime = LocalDateTime.parse("13-11-2020 1600", DateTimeFormatter.ofPattern("d-M-uuuu HHmm"));
        Event sampleEventTwo = new Event(new EventName("CS2103T PE"), new EventTime(dateTime), tags);
        sampleEv.addEvent(sampleEventTwo);
        return sampleEv;
    }

}
