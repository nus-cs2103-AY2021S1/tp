package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalTutorialGroups {

    public static final TutorialGroup T05 = new TutorialGroup(
            new TutorialGroupId("T05"),
            new DayOfWeek("MON"),
            new TimeOfDay("13:00"),
            new TimeOfDay("14:00")
    );

    public static final TutorialGroup B06 = new TutorialGroup(
            new TutorialGroupId("B06"),
            new DayOfWeek("THU"),
            new TimeOfDay("12:00"),
            new TimeOfDay("14:00")

    );

    public static final TutorialGroup S12 = new TutorialGroup(
            new TutorialGroupId("S12"),
            new DayOfWeek("FRI"),
            new TimeOfDay("08:00"),
            new TimeOfDay("10:30")
    );

    public static final TutorialGroup V04 = new TutorialGroup(
            new TutorialGroupId("V04"),
            new DayOfWeek("TUE"),
            new TimeOfDay("09:00"),
            new TimeOfDay("10:00")
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
