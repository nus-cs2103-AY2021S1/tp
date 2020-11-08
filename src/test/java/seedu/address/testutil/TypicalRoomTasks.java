package seedu.address.testutil;

import seedu.address.model.room.RoomTasks;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects found in {@code Room} to be used in tests.
 */
public class TypicalRoomTasks {

    private TypicalRoomTasks() {} // prevents instantiation

    public static RoomTasks getTypicalRoomTasks() {
        RoomTasks roomTasks = new RoomTasks();
        for (Task task : TypicalTasks.getTypicalTasks()) {
            roomTasks.addTask(task);
        }
        return roomTasks;
    }
}
