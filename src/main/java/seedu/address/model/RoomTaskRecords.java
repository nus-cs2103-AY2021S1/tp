package seedu.address.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomTaskAssociation;
import seedu.address.model.task.Task;

/**
 * RoomTaskRecords exists mainly for the purpose of exposing an ObservableList for the user interface of
 * {@code Task} to display the list of tasks in all {@code Room}.
 *
 * It stores a list of {@code RoomTaskAssociation}. {@code RoomTaskAssociation} is preferred to {@code Task}
 * as it provides an API to quickly query room number and task index of a task.
 *
 * RoomTaskRecords follows the Singleton pattern. Solution adapted with modifications from:
 * https://stackoverflow.com/questions/1050991/singleton-with-arguments-in-java
 */
public class RoomTaskRecords implements ReadOnlyList<RoomTaskAssociation> {

    private static RoomTaskRecords theOne = null;

    private static final Logger logger = LogsCenter.getLogger(RoomTaskRecords.class);

    private final ObservableList<RoomTaskAssociation> internalList = FXCollections.observableArrayList();
    private final ObservableList<RoomTaskAssociation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private RoomTaskRecords() { // set to private to ensure that creation is done through init(...) method
    }

    /**
     * Returns the RoomTaskRecords.
     *
     * @throws AssertionError if RoomTaskRecords has not been initialized.
     */
    public static RoomTaskRecords getInstance() {
        if (theOne == null) {
            throw new AssertionError("RoomTaskRecords has to be initialized first.");
        }

        return theOne;
    }

    /**
     * Creates and initializes the RoomTaskRecords. The information about the rooms and the tasks in
     * those rooms are obtained from {@code roomList}. A listener is attached to {@code roomList} to
     * ensure that the associations between rooms and tasks are synchronized even when there are
     * changes to {@code roomList}.
     *
     * Only one instance of RoomTaskRecords can be created.
     *
     * @param roomList The list of rooms from which to create the associations between room and task.
     * @return The singleton instance of RoomTaskRecords.
     * @throw AssertionError if RoomTaskRecords has already been initialized
     */
    public static synchronized RoomTaskRecords init(ObservableList<Room> roomList) {
        if (theOne != null) {
            throw new AssertionError("RoomTaskRecords has already been initialized.");
        }

        logger.finer("Creating RoomTaskRecords and attaching a listener to refresh room-task associations...");

        theOne = new RoomTaskRecords();
        theOne.createRoomTaskAssociations(roomList);
        theOne.updateAssociationIfChanged(roomList);

        logger.finer("Successfully attached listener to RoomTaskRecords.");

        return theOne;
    }

    /**
     * Creates the associations between each room and all the tasks in the rooms.
     * These associations are stored into {@code internalList}.
     */
    private void createRoomTaskAssociations(ObservableList<Room> roomList) {
        List<RoomTaskAssociation> associations = new ArrayList<>();

        for (Room room : roomList) {
            int taskIndex = 1;
            for (Task task : room.getReadOnlyTasks()) {
                associations.add(new RoomTaskAssociation(room, task, taskIndex));
                taskIndex++;
            }
        }

        internalList.setAll(associations);
    }

    /**
     * Adds a listener to {@code roomList} to recreate all {@code RoomTaskAssociation} whenever
     * there is a change.
     *
     * This ensures that the associations are properly synchronized and the task indexes are correct.
     */
    private void updateAssociationIfChanged(ObservableList<Room> roomList) {
        roomList.addListener(new ListChangeListener<Room>() {
            @Override
            public void onChanged(Change<? extends Room> change) {
                while (change.next()) {
                    logger.fine("Changes detected in list of rooms. Updating room-task associations...");
                    createRoomTaskAssociations(roomList);
                }
            }
        });
    }

    @Override
    public ObservableList<RoomTaskAssociation> getReadOnlyList() {
        return internalUnmodifiableList;
    }
}
