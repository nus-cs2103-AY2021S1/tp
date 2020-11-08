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
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.model.module.grade.GradeTracker;
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

    /**
     * Generates list of sample modules
     */
    public static Module[] getSampleModules() {
        GradeTracker gradeTrackerCompleted = new GradeTracker(5.0);
        GradeTracker gradeTrackerWithAssignments = new GradeTracker();
        gradeTrackerWithAssignments.addAssignment(new Assignment(new AssignmentName("Quiz1"),
                new AssignmentPercentage(15), new AssignmentResult(70)));
        Module cs2030 = new Module(new ModuleName("CS2030"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2030")),
                gradeTrackerCompleted, getTagSet("CoreModule"), new ModularCredits(4));
        Module cs2101 = new Module(new ModuleName("CS2101"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module cs2105 = new Module(new ModuleName("CS2105"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2105")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module cs1101S = new Module(new ModuleName("CS1101S"), getZoomLinkMap(new ModuleLesson("Lecture"),
                new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module is1103 = new Module(new ModuleName("IS1103"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        return new Module[] {cs2030, cs2101, cs2105, cs1101S, is1103
        };
    }

    /**
     * Generates list of sample archived modules
     */
    public static Module[] getSampleArchivedModules() {
        GradeTracker gradeTrackerWithAssignments = new GradeTracker();
        gradeTrackerWithAssignments.addAssignment(new Assignment(new AssignmentName("Quiz1"),
                new AssignmentPercentage(15), new AssignmentResult(70)));
        Module es2660 = new Module(new ModuleName("ES2660"),
                getTagSet("FluffModule", "Completed"), new ModularCredits(4));
        Module cs3230 = new Module(new ModuleName("CS3230"),
                getTagSet("CoreModule", "Completed"), new ModularCredits(4));
        return new Module[] {es2660, cs3230
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
     * Returns a ReadOnlyModuleList version of sample modules
     */
    public static ReadOnlyModuleList getSampleModuleList() {
        ModuleList sampleMl = new ModuleList();
        for (Module sampleModule : getSampleModules()) {
            sampleMl.addModule(sampleModule);
        }
        return sampleMl;
    }

    /**
     * Returns a ReadOnlyModuleList version of sample archived modules
     */
    public static ReadOnlyModuleList getSampleArchivedModuleList() {
        ModuleList sampleMl = new ModuleList();
        for (Module sampleModule : getSampleArchivedModules()) {
            sampleMl.addModule(sampleModule);
        }
        return sampleMl;
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
