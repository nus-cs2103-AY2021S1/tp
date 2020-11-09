package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalActivities.THEMEPARK;
import static seedu.address.testutil.typicals.TypicalActivities.ZOO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.activity.exceptions.ActivityNotFoundException;
import seedu.address.model.activity.exceptions.DuplicateActivityException;
import seedu.address.testutil.builders.ActivityBuilder;

public class UniqueActivityListTest {
    private final UniqueActivityList uniqueActivityList = new UniqueActivityList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.contains(null));
    }

    @Test
    public void contains_activityNotInList_returnsFalse() {
        assertFalse(uniqueActivityList.contains(ZOO));
    }

    @Test
    public void contains_activityInList_returnsTrue() {
        uniqueActivityList.add(ZOO);
        assertTrue(uniqueActivityList.contains(ZOO));
    }

    @Test
    public void contains_activityWithSameIdentityFieldsInList_returnsTrue() {
        uniqueActivityList.add(ZOO);
        Activity editedZoo = new ActivityBuilder(ZOO).withLocation(ZOO.getLocation().getValue())
                .withName(ZOO.getName().getValue()).withDateTime(ZOO.getActivityDateTime().getDateTime()).build();
        assertTrue(uniqueActivityList.contains(editedZoo));
    }

    @Test
    public void add_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.add(null));
    }

    @Test
    public void add_duplicateActivity_throwsDuplicateactivityException() {
        uniqueActivityList.add(ZOO);
        assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.add(ZOO));
    }

    @Test
    public void setActivity_nullTargetActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivity(null, ZOO));
    }

    @Test
    public void setActivity_nullEditedActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivity(ZOO, null));
    }

    @Test
    public void setActivity_targetActivityNotInList_throwsActivityNotFoundException() {
        assertThrows(ActivityNotFoundException.class, () -> uniqueActivityList.setActivity(ZOO, ZOO));
    }

    @Test
    public void setActivity_editedActivityIsSameActivity_success() {
        uniqueActivityList.add(ZOO);
        uniqueActivityList.setActivity(ZOO, ZOO);
        UniqueActivityList expecteduniqueActivityList = new UniqueActivityList();
        expecteduniqueActivityList.add(ZOO);
        assertEquals(expecteduniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasSameIdentity_success() {
        uniqueActivityList.add(ZOO);
        Activity editedZoo = new ActivityBuilder(ZOO).withLocation("2020-10-10 12:00").build();
        uniqueActivityList.setActivity(ZOO, editedZoo);
        UniqueActivityList expecteduniqueActivityList = new UniqueActivityList();
        expecteduniqueActivityList.add(editedZoo);
        assertEquals(expecteduniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasDifferentIdentity_success() {
        uniqueActivityList.add(ZOO);
        uniqueActivityList.setActivity(ZOO, THEMEPARK);
        UniqueActivityList expecteduniqueActivityList = new UniqueActivityList();
        expecteduniqueActivityList.add(THEMEPARK);
        assertEquals(expecteduniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivity_editedActivityHasNonUniqueIdentity_throwsDuplicateactivityException() {
        uniqueActivityList.add(ZOO);
        uniqueActivityList.add(THEMEPARK);
        assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.setActivity(ZOO, THEMEPARK));
    }

    @Test
    public void remove_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.remove(null));
    }

    @Test
    public void remove_activityDoesNotExist_throwsActivityNotFoundException() {
        assertThrows(ActivityNotFoundException.class, () -> uniqueActivityList.remove(ZOO));
    }

    @Test
    public void remove_existingActivity_removesActivity() {
        uniqueActivityList.add(ZOO);
        uniqueActivityList.remove(ZOO);
        UniqueActivityList expecteduniqueActivityList = new UniqueActivityList();
        assertEquals(expecteduniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullUniqueActivityList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivities((UniqueActivityList) null));
    }

    @Test
    public void setActivities_uniqueActivityList_replacesOwnListWithProvideduniqueActivityList() {
        uniqueActivityList.add(ZOO);
        UniqueActivityList expecteduniqueActivityList = new UniqueActivityList();
        expecteduniqueActivityList.add(THEMEPARK);
        uniqueActivityList.setActivities(expecteduniqueActivityList);
        assertEquals(expecteduniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueActivityList.setActivities((List<Activity>) null));
    }

    @Test
    public void setActivities_list_replacesOwnListWithProvidedList() {
        uniqueActivityList.add(ZOO);
        List<Activity> activityList = Collections.singletonList(THEMEPARK);
        uniqueActivityList.setActivities(activityList);
        UniqueActivityList expecteduniqueActivityList = new UniqueActivityList();
        expecteduniqueActivityList.add(THEMEPARK);
        assertEquals(expecteduniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setActivities_listWithDuplicateActivities_throwsDuplicateActivityException() {
        List<Activity> listWithDuplicateActivities = Arrays.asList(ZOO, ZOO);
        assertThrows(DuplicateActivityException.class, ()
            -> uniqueActivityList.setActivities(listWithDuplicateActivities));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueActivityList.asUnmodifiableObservableList().remove(0));
    }
}
