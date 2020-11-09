package seedu.address.model.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

import javafx.collections.ObservableList;

/**
 * Class for calculating statistics of a TodoList for the purpose of the GUI.
 */
public class TodoListUtil {

    /**
     * Returns an array containing the the number of each task which is categorized based on the status.
     *
     * @param todoList todoList to be checked
     * @return an array with size of 3. The 0, 1, and 2 index represents the number of overdue task, not completed task,
     *         and completed task respectively in the todoList.
     */
    public static int[] getStatistics(ObservableList<Task> todoList) {
        int[] statistics = new int[3];
        for (Task task : todoList) {
            // status field is compulsory
            assert task.getStatus().isPresent();
            if (task.getStatus().get().equals(Status.COMPLETED)) {
                statistics[2]++;
            } else if (task.isOverdue()) {
                statistics[0]++;
            } else {
                statistics[1]++;
            }
        }
        assert statistics[0] + statistics[1] + statistics[2] == todoList.size();
        return statistics;
    }

    public static HashMap<DayOfWeek, Integer> getFutureTasks(ObservableList<Task> todoList) {
        HashMap<DayOfWeek, Integer> futureTasks = new HashMap<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate maxDate = currentDate.plusDays(6);

        // initialize days in futureTasks
        futureTasks.put(DayOfWeek.MONDAY, 0);
        futureTasks.put(DayOfWeek.TUESDAY, 0);
        futureTasks.put(DayOfWeek.WEDNESDAY, 0);
        futureTasks.put(DayOfWeek.THURSDAY, 0);
        futureTasks.put(DayOfWeek.FRIDAY, 0);
        futureTasks.put(DayOfWeek.SATURDAY, 0);
        futureTasks.put(DayOfWeek.SUNDAY, 0);

        for (Task task : todoList) {
            if (task.getDate().isEmpty()) {
                continue;
            }
            LocalDate deadline = task.getDate().get().getValue();
            if (deadline.isBefore(currentDate)) {
                continue;
            }
            if (deadline.isAfter(maxDate)) {
                continue;
            }
            assert task.getStatus().isPresent();
            if (task.getStatus().get().equals(Status.COMPLETED)) {
                continue;
            }
            DayOfWeek day = deadline.getDayOfWeek();
            futureTasks.put(day, futureTasks.get(day) + 1);
        }
        return futureTasks;
    }
}
