package seedu.jarvis.model.mission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalMissions.FRACTAL_DIMENSIONS;
import static seedu.jarvis.testutil.TypicalMissions.MUSICAL_NOTES;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jarvis.testutil.MissionBuilder;

class MissionListTest {

    private final MissionList missionList = new MissionList();
    private final List<Mission> missionListTest = new ArrayList<>();

    @Test
    void add_nullMission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> missionList.add(null));
    }

    @Test
    void contains_nullMission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> missionList.contains(null));
    }

    @Test
    public void contains_missionNotInList_returnsFalse() {
        assertFalse(missionList.contains(FRACTAL_DIMENSIONS));
    }

    @Test
    public void contains_missionInList_returnsTrue() {
        missionList.add(FRACTAL_DIMENSIONS);
        assertTrue(missionList.contains(FRACTAL_DIMENSIONS));
    }

    @Test
    void setNullMission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> missionList.setMissions(null));
        missionListTest.add(FRACTAL_DIMENSIONS);
        missionList.setMissions(missionListTest);
        assertFalse(missionList.contains(MUSICAL_NOTES));
    }

    @Test
    void changeMissionListTo_asObservableList() {
        assertFalse(missionList.getClass().equals(missionList.asObservableList().getClass()));
    }

    @Test
    void isMissionInList_missionInList_returnsTrue() {
        missionList.add(FRACTAL_DIMENSIONS);
        assertTrue(missionList.isMissionInList("Fractal Dimensions"));
    }

    @Test
    void updateMission_missionInList_returnsTrue() {
        missionList.add(MUSICAL_NOTES);
        missionList.updateMission("Musical Notes");
        Mission editedMusicalNotes = new MissionBuilder(MUSICAL_NOTES).withIsGraded(false).build();
        assertTrue(missionList.contains(editedMusicalNotes));
    }

    @Test
    void updateMission_missionNotInList_returnsFalse() {
        missionList.add(MUSICAL_NOTES);
        assertFalse(missionList.updateMission("Streams"));
    }
}
