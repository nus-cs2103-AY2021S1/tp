package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagTask;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {

    public static final Tag GROUPMATE = new TagBuilder().withTagName("groupmate").build();
    public static final Tag COLLEAGUE = new TagBuilder().withTagName("colleague").build();
    public static final Tag CS2103T = new TagBuilder().withTagName("CS2103T").build();
    public static final Tag CS2040S = new TagBuilder().withTagName("CS2040S").build();
    public static final Tag SEP = new TagBuilder().withTagName("SEP").build();
    public static final Tag HALL = new TagBuilder().withTagName("Hall").build();
    public static final Tag HANDBALL = new TagBuilder().withTagName("Handball").build();

    //Tags with Links
    public static final Tag CS2101 = new TagBuilder().withTagName("CS2101")
                                                    .withMeetingLink("https://skype.com").build();
    public static final Tag CS2040 = new TagBuilder().withTagName("CS2040")
                                                    .withMeetingLink("https://zoomus.nus").build();

    //Tags with TagTasks
    public static final Tag CS2103 = new TagBuilder().withTagName("CS2103")
            .withTagTasks(new TagTask("submit assignment", false)).build();
    public static final Tag CS2030 = new TagBuilder().withTagName("CS2030")
            .withTagTasks(new TagTask("submit lab", true)).build();

    //Tags with TagTasks and Links
    public static final Tag CS1101S = new TagBuilder().withTagName("CS1101S")
            .withTagTasks(new TagTask("practical", false))
            .withMeetingLink("https://skype.com").build();

    private TypicalTags() {} // prevents instantiation

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(GROUPMATE, COLLEAGUE, CS2103T, CS2040S, SEP, HALL, HANDBALL));
    }

    public static List<Tag> getTypicalTagsWithLinks() {
        return new ArrayList<>(Arrays.asList(CS2101, CS2040));
    }

    public static List<Tag> getTypicalTagsWithTasks() {
        return new ArrayList<>(Arrays.asList(CS2103, CS2030));
    }
}
