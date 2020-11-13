package seedu.jarvis.model.mission;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MissionList {
    private final ObservableList<Mission> internalList = FXCollections.observableArrayList();

    /**
     * Adds a mission to the list.
     */
    public void add(Mission toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns true if the list contains an equivalent mission as the given argument.
     */
    public boolean contains(Mission toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public void setMissions(List<Mission> missions) {
        requireNonNull(missions);
        this.internalList.setAll(missions);
    }

    public ObservableList<Mission> asObservableList() {
        return internalList;
    }

    public boolean isMissionInList(String name) {
        return internalList.stream().anyMatch(mission -> mission.getTitle().equals(name));
    }

    /**
     * Updates the mission found to be ungraded
     *
     * @param name mission title
     * @return true if missions was found and updated successfully
     */
    public boolean updateMission(String name) {
        Optional<Mission> missionToUpdate = internalList.stream().filter(mission -> mission.getTitle().equals(name))
                .findFirst();

        if (missionToUpdate.isPresent()) {
            Mission mission = missionToUpdate.get();
            this.internalList.remove(missionToUpdate.get());
            mission = mission.setIsGraded(false);
            this.internalList.add(mission);
            return true;
        } else {
            return false;
        }
    }
}
