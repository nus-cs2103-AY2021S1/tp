package seedu.address.model.util;

import java.util.Optional;

import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;
import seedu.address.model.deliverable.deliverable.Deadline;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.deliverable.deliverable.Milestone;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.meeting.From;
import seedu.address.model.meeting.meeting.Location;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.To;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.model.person.person.Email;
import seedu.address.model.person.person.Name;
import seedu.address.model.person.person.Person;
import seedu.address.model.person.person.Phone;
import seedu.address.model.person.person.Role;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    Role.getRole("stk"), new Description("End user")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    Role.getRole("dev"), new Description("Frontend Engineer")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    Role.getRole("stk"), new Description("Business analyst")),
            new Person(new Name("David Li"), new Phone(Optional.empty()), new Email("lidavid@example.com"),
                    Role.getRole("dev"), new Description("Backend Engineer")),
            new Person(new Name("Irfan Ibrahim"), new Phone(Optional.empty()), new Email("irfan@example.com"),
                    Role.getRole("stk"), new Description(Optional.empty())),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    Role.getRole("stk"), new Description("End user")),
            new Person(new Name("Bianca Li"), new Phone("1234567"), new Email("bianca@example.com"),
                    Role.getRole("stk"), new Description("Product Designer")),
            new Person(new Name("Budi Putra"), new Phone("1234566"), new Email("budi@example.com"),
                    Role.getRole("dev"), new Description("DevOps Engineer")),
            new Person(new Name("Amber Johnson"), new Phone("81623941"), new Email("amber@example.com"),
                    Role.getRole("stk"), new Description("Finance expert")),
            new Person(new Name("Samuel Sam"), new Phone("78129394"), new Email("samsam@example.com"),
                    Role.getRole("dev"), new Description("Full-stack Engineer")),
            new Person(new Name("Charlie Oliver"), new Phone("93210283"), new Email("charlie@example.com"),
                    Role.getRole("stk"), new Description("Business analyst. Alex works with him.")),
            new Person(new Name("Devina Yu"), new Phone(Optional.empty()), new Email("devina@example.com"),
                    Role.getRole("dev"), new Description("Full-stack Engineer")),
            new Person(new Name("Carol Geller"), new Phone(Optional.empty()), new Email("carol@example.com"),
                    Role.getRole("stk"), new Description("Potential Customer")),
            new Person(new Name("Martin Seth"), new Phone(Optional.empty()), new Email("martin@example.com"),
                        Role.getRole("stk"), new Description("Potential Customer")),
            new Person(new Name("Rosa Sinantra"), new Phone("92624417"), new Email("rosa@example.com"),
                    Role.getRole("stk"), new Description("End user")),
            new Person(new Name("Bonny Thompson"), new Phone("1234444"), new Email("thompson@example.com"),
                    Role.getRole("stk"), new Description("Works at Johnson & Johnson")),
            new Person(new Name("Lindsay Perry"), new Phone("12343337"), new Email("lindsay@example.com"),
                    Role.getRole("dev"), new Description("Senior UI/UX Designer")),
            new Person(new Name("Robbie Margaret"), new Phone("92624417"), new Email("robbie@example.com"),
                        Role.getRole("stk"), new Description("End user")),
            new Person(new Name("Zachary Quinn"), new Phone("123123123"), new Email("zachary@example.com"),
                        Role.getRole("stk"), new Description("Business Associates")),
            new Person(new Name("Christian Pine"), new Phone("321321321"), new Email("pine@example.com"),
                        Role.getRole("dev"), new Description("QA engineer")),
            new Person(new Name("Cassandra Bullock"), new Phone("56565656"), new Email("cassandra@example.com"),
                        Role.getRole("dev"), new Description("QA engineer")),
            new Person(new Name("Robby Williams"), new Phone("12346123"), new Email("robby@example.com"),
                        Role.getRole("stk"), new Description("Head of Engineering")),
            new Person(new Name("Justin Gomez"), new Phone("1234444"), new Email("justin@example.com"),
                        Role.getRole("stk"), new Description("Business Analyst")),
            new Person(new Name("Sarah McQuarie"), new Phone("12343337"), new Email("mcquarie@example.com"),
                        Role.getRole("dev"), new Description("Data Scientist")),
            new Person(new Name("Joe Manganiel"), new Phone("92624417"), new Email("joe@example.com"),
                        Role.getRole("stk"), new Description("Head of business")),
            new Person(new Name("Bryan Randall"), new Phone("123123123"), new Email("bryan@example.com"),
                        Role.getRole("stk"), new Description("Angel investor from Sequoia VC")),
            new Person(new Name("Samuel Smith"), new Phone("321321321"), new Email("smith@example.com"),
                        Role.getRole("stk"), new Description("Investor from 600 startups")),
            new Person(new Name("Farrah Lionel"), new Phone("56565656"), new Email("farrah@example.com"),
                        Role.getRole("stk"), new Description("Investor from Bank of the People")),
        };
    }

    public static Deliverable[] getSampleDeliverables() {
        return new Deliverable[] {
            new Deliverable(new Title("Define problem to solve"), new Milestone("1.1"),
                new Description("Survey potential customers to gather feedback."),
                new Deadline("04-08-2020 15:00"), true, new Contacts("Carol Geller, Martin Seth")),
            new Deliverable(new Title("Determine the Minimum Viable Product (MVP)"), new Milestone("1.2"),
                new Description("Come up with minimum set of features to test key assumptions. "
                        + "Discuss with Bianca (Product Designer) too."),
                new Deadline("18-08-2020 12:00"), true, new Contacts("Bianca Li")),
            new Deliverable(new Title("Finish mock-ups"), new Milestone("1.3"),
                new Description("Liaise with the UI/UX team. "
                        + "Lindsay can represent her team as senior UI/UX designer."),
                new Deadline("01-09-2020 10:00"), true, new Contacts("Lindsay Lauren")),
            new Deliverable(new Title("Finalise design and plan"), new Milestone("1.4"),
                new Description("This is urgent and important!"),
                new Deadline("15-09-2020 15:00"), true, new Contacts("Robby Williams, Breonna Randall")),
            new Deliverable(new Title("Settle prioritization of features"), new Milestone("2.1"),
                new Description("Hold meeting with the dev team."),
                new Deadline("29-09-2020 12:00"), true, new Contacts("Bernice Yu, David Li, Budi Putra,"
                    + "Christian Pine, Cassandra Bullock, Samuel Sam, Devina Yu")),
            new Deliverable(new Title("Deliver first version of MVP"), new Milestone("2.3"),
                new Description("Check-in with Bernice (Tech Lead)."),
                new Deadline("15-10-2020 10:00"), true, new Contacts("Bernice Yu")),
            new Deliverable(new Title("Conduct MVP usability testing"), new Milestone("2.4"),
                new Description("Schedule meetings with end users."),
                new Deadline("20-10-2020 15:00"), true, new Contacts("Alex Yeoh, Roy Balakrishnan, "
                    + "Rosa       Sinantra")),
            new Deliverable(new Title("Refine design"), new Milestone("2.5"),
                new Description("Discuss design with Product Designer and UI/UX team again."),
                new Deadline("28-10-2020 12:00"), true, new Contacts("Lindsay Lauren, Bianca Li")),
            new Deliverable(new Title("Deliver second version of MVP"), new Milestone("2.6"),
                new Description("Reminder to plan time wisely."),
                new Deadline("30-10-2020 10:00"), false, new Contacts("Bernice Yeoh")),
            new Deliverable(new Title("Deliver find feature"), new Milestone("3.1"),
                new Description("Check-in with Bernice (Tech Lead)."),
                new Deadline("08-11-2020 15:00"), false, new Contacts("Bernice Yeoh")),
            new Deliverable(new Title("Deliver sort feature"), new Milestone("3.1"),
                new Description("Check-in with Bernice (Tech Lead)."),
                new Deadline("08-11-2020 15:00"), false, new Contacts("Bernice Yeoh")),
            new Deliverable(new Title("Define marketing goals "), new Milestone("3.1"),
                new Description("Get financial consultation."),
                new Deadline("10-11-2020 10:00"), false, new Contacts("Amber Johnson")),
            new Deliverable(new Title("Finalise marketing goals"), new Milestone("3.2"),
                new Description("Get financial consultation."),
                new Deadline("12-11-2020 10:00"), false, new Contacts("Amber Johnson")),
            new Deliverable(new Title("Deliver view feature"), new Milestone("3.2"),
                new Description("Check-in with Bernice (Tech Lead)."),
                new Deadline("20-11-2020 10:00"), false, new Contacts("Bernice Yu")),
            new Deliverable(new Title("Product Launch \uD83D\uDE80"), new Milestone("10"),
                new Description("Launch product to market"),
                new Deadline("04-05-2021 23:59"), false, new Contacts(Optional.empty())),
        };
    }

    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Meeting(new Title("Survey potential customers"),
                new Description("Gather feedback"),
                new From("04-08-2020 15:00"),
                new To("18:00"),
                new Contacts("Carol Geller, Martin Seth"),
                new Location("Stardollar Cafe")),
            new Meeting(new Title("Discuss product design"), new Description("With product designer"),
                new From("18-08-2020 12:00"),
                new To("14:00"),
                new Contacts("Bianca Li"),
                new Location("Min Cafe")),
            new Meeting(new Title("Discuss product mock-ups"), new Description("With UI/UX team. "
                    + "Can be represented by the senior UI/UX designer."),
                new From("01-09-2020 10:00"),
                new To("11:00"),
                new Contacts("Lindsay Lauren"),
                new Location("Meeting room C")),
            new Meeting(new Title("Present plan to Heads"),
                new Description("Don't forget to present survey results."),
                new From("15-09-2020 15:00"),
                new To("17:00"),
                new Contacts("Robby Williams, Breonna Randall"),
                new Location("Meeting room A")),
            new Meeting(new Title("Discuss development plan"), new Description("With dev team"),
                new From("29-09-2020 12:00"),
                new To("14:00"),
                new Contacts("Bernice Yu, David Li, Budi Putra,"
                        + "Christian Pine, Cassandra Bullock, Samuel Sam, Devina Yu"),
                new Location("Meeting room B")),
            new Meeting(new Title("Check-in about MVP progress"), new Description("With tech lead"),
                new From("15-10-2020 12:00"),
                new To("14:00"),
                new Contacts("Bernice Yu"),
                new Location("Zoom call")),
            new Meeting(new Title("Check-in about MVP completion"), new Description("Goals must be achieved!"),
                new From("17-10-2020 10:00"),
                new To("14:00"),
                new Contacts("Bernice Yu"),
                new Location("Zoom call")),
            new Meeting(new Title("Meeting end users for usability testing"), new Description("With end users"),
                new From("20-10-2020 10:00"),
                new To("16:00"),
                new Contacts("Alex Yeoh, Roy Balakrishnan, Rosa Sinantra"),
                new Location("Stardollar Cafe")),
            new Meeting(new Title("Discuss refinement of design and plan"),
                new Description("With Product Designer and UI/UX team"),
                new From("28-10-2020 10:00"),
                new To("16:00"),
                new Contacts("Lindsay Lauren, Bianca Li"),
                new Location("Zoom call")),
            new Meeting(new Title("Consult about marketing goals"),
                new Description("With finance expert"),
                new From("09-11-2020 10:00"),
                new To("16:00"),
                new Contacts("Amber Johnson"),
                new Location("Zoom call")),
            new Meeting(new Title("Scrum meeting"), new Description("With dev team"),
                new From("10-11-2020 10:00"),
                new To("14:00"),
                new Contacts("Bernice Yu, David Li, Budi Putra,"
                        + "Christian Pine, Cassandra Bullock, Samuel Sam, Devina Yu"),
                new Location("Zoom call")),
            new Meeting(new Title("Finalise marketing plan"),
                new Description("With finance expert"),
                new From("11-11-2020 10:00"),
                new To("16:00"),
                new Contacts("Amber Johnson"),
                new Location("Zoom call")),
            new Meeting(new Title("Discuss current progress"), new Description("With Head of Business"),
                new From("20-11-2020 10:00"),
                new To("14:00"),
                new Contacts("Joe Manganiel"),
                new Location("Zoom call")),
            new Meeting(new Title("Provide updates on MVP"), new Description("With Head of Engineering"),
                new From("21-11-2020 10:00"),
                new To("14:00"),
                new Contacts("Robby Williams"),
                new Location("Zoom call")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyDeliverableBook getSampleDeliverableBook() {
        DeliverableBook sampleDb = new DeliverableBook();
        for (Deliverable sampleDeliverable : getSampleDeliverables()) {
            sampleDb.addDeliverable(sampleDeliverable);
        }
        return sampleDb;
    }

    public static ReadOnlyMeetingBook getSampleMeetingBook() {
        MeetingBook sampleMb = new MeetingBook();
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleMb.addMeeting(sampleMeeting);
        }
        return sampleMb;
    }

}
