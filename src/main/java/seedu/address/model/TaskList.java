package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Adds a task to the list
     * @param t task that is being added
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public int getTaskListSize() {
        return this.taskList.size();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Removes a task based on the number
     * @param number position number of the task in the list
     */
    public void removeTaskAtNumber(int number) {
        if (number > getTaskListSize() || number < 1) {
            System.out.println("Number is not valid");
        }
        this.taskList.remove(number - 1);
    }

    /**
     * Clears the task list
     */
    public void clear() {
        this.taskList.clear();
    }

    /**
     * Returns a task based on the number
     * @param number position of the task in the list
     * @return Task that is being retrieved
     */
    public Task getTask(int number) {
        return this.taskList.get(number - 1);
    }
}
