package seedu.address.model.travelplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalActivities.ZOO;
import static seedu.address.testutil.typicals.TypicalActivities.getTypicalActivityList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.exceptions.DuplicateActivityException;
import seedu.address.testutil.builders.ActivityBuilder;

public class ActivityListTest {

    private final ActivityList activityList = new ActivityList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), activityList.getObservableActivityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activityList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyActivityList_replacesData() {
        ActivityList newData = getTypicalActivityList(2);
        activityList.resetData(newData);
        assertEquals(newData, activityList);
    }

    @Test
    public void resetData_withDuplicateActivities_throwsDuplicateActivityException() {
        // Two activities with the same identity fields
        Activity editedZoo = new ActivityBuilder(ZOO).build();
        List<Activity> newActivities = Arrays.asList(ZOO, editedZoo);
        ActivityListStub newData = new ActivityListStub(newActivities);

        assertThrows(DuplicateActivityException.class, () -> activityList.resetData(newData));
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activityList.hasActivity(null));
    }

    @Test
    public void hasActivity_activityNotInActivityList_returnsFalse() {
        assertFalse(activityList.hasActivity(ZOO));
    }

    @Test
    public void hasActivity_activityInActivityList_returnsTrue() {
        activityList.addActivity(ZOO);
        assertTrue(activityList.hasActivity(ZOO));
    }

    @Test
    public void getActivityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> activityList.getObservableActivityList().remove(0));
    }

    /**
     * TODO
     * A stub ReadOnlyActivityList whose activities list can violate interface constraints.
     */
    private static class ActivityListStub extends ActivityList {
        private final ObservableList<Activity> activities = FXCollections.observableArrayList();

        ActivityListStub(Collection<Activity> activities) {
            this.activities.setAll(activities);
        }

        public ObservableList<Activity> getObservableActivityList() {
            return activities;
        }
    }

}
