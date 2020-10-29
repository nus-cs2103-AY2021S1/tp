package seedu.address.testutil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalTutorialGroups {

    public static final TutorialGroup T05 = new TutorialGroup(
            new TutorialGroupId("T05"),
            LocalTime.parse("13:00"),
            LocalTime.parse("14:00"),
            "Monday"
    );

    public static final TutorialGroup B06 = new TutorialGroup(
            new TutorialGroupId("B06"),
            LocalTime.parse("12:00"),
            LocalTime.parse("14:00"),
            "Thursday"
    );

    public static final TutorialGroup S12 = new TutorialGroup(
            new TutorialGroupId("S12"),
            LocalTime.parse("08:00"),
            LocalTime.parse("10:30"),
            "Friday"
    );

    public static final TutorialGroup V04 = new TutorialGroup(
            new TutorialGroupId("V04"),
            LocalTime.parse("09:00"),
            LocalTime.parse("10:00"),
            "Tuesday"
    );

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static UniqueTutorialGroupList getTutorialGroupList() {
        UniqueTutorialGroupList tgl = new UniqueTutorialGroupList();
        for (TutorialGroup tutorialGroup: getTypicalModules()) {
            tgl.addTutorialGroup(tutorialGroup);
        }
        return tgl;
    }

    public static List<TutorialGroup> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(T05, B06, S12));
    }
}
