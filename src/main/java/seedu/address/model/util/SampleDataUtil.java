package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.contact.Contact;
// import seedu.address.model.contact.ContactName;
// import seedu.address.model.contact.Email;
// import seedu.address.model.contact.Telegram;
import seedu.address.model.module.*;
import seedu.address.model.module.Module;
import seedu.address.model.module.grade.*;
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

    public static Module[] getSampleModules() {
        GradeTracker gradeTrackerCompleted = new GradeTracker(5.0);
        GradeTracker gradeTrackerWithAssignments = new GradeTracker();
        gradeTrackerWithAssignments.addAssignment(new Assignment(new AssignmentName("Quiz1"),
                new AssignmentPercentage(15), new AssignmentResult(70)));
        Module CS2030 = new Module(new ModuleName("CS2030"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2030")),
                gradeTrackerCompleted, getTagSet("CoreModule"), new ModularCredits(4));
        Module CS2101 = new Module(new ModuleName("CS2030"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module CS2105 = new Module(new ModuleName("CS2030"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2105")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module CS1101S = new Module(new ModuleName("CS2030"), getZoomLinkMap(new ModuleLesson("Lecture"),
                new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module IS1103 = new Module(new ModuleName("CS2030"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        return new Module[] {CS2030, CS2101, CS2105, CS1101S, IS1103
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

    public static ReadOnlyModuleList getSampleModuleList() {
        ModuleList sampleMl = new ModuleList();
        for (Module sampleModule : getSampleModules()) {
            sampleMl.addModule(sampleModule);
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

    /**
     * Returns a hash map containing the lesson given.
     */
    public static HashMap<ModuleLesson, ZoomLink> getZoomLinkMap(ModuleLesson lesson, ZoomLink zoomLink) {
        HashMap<ModuleLesson, ZoomLink> zoomLinkMap = new HashMap<>();
        zoomLinkMap.put(lesson, zoomLink);
        return zoomLinkMap;
    }
}
