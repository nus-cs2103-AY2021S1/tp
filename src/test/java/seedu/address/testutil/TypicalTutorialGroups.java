package seedu.address.testutil;

import static seedu.address.testutil.TypicalStudents.getTypicalStudentList;

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
            new TimeOfDay("14:00"),
            getTypicalStudentList()
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

    public static final TutorialGroup V10 = new TutorialGroup(
            new TutorialGroupId("V10"),
            new DayOfWeek("WED"),
            new TimeOfDay("09:00"),
            new TimeOfDay("10:00")
    );

    /**
     * Returns an {@code UniqueTutorialGroupList} with all the typical tutorial groups.
     */
    public static UniqueTutorialGroupList getTutorialGroupList() {
        UniqueTutorialGroupList tgl = new UniqueTutorialGroupList();
        for (TutorialGroup tutorialGroup: getTypicalTutorialGroups()) {
            tgl.addTutorialGroup(tutorialGroup);
        }
        return tgl;
    }

    public static List<TutorialGroup> getTypicalTutorialGroups() {
        return new ArrayList<>(Arrays.asList(T05, B06, S12, V04));
    }
}
