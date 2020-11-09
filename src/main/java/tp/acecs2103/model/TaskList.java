package tp.acecs2103.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;

import tp.acecs2103.commons.util.AppUtil;
import tp.acecs2103.model.exceptions.InvalidTaskListOperationException;
import tp.acecs2103.model.exceptions.InvalidTaskOperationException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Deadline;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.WeekNumber;



public class TaskList {
    private ArrayList<Task> taskList;
    private WeekNumber timeRange;

    /**
     * Initializes a {@code TaskList} with given task list and time range.
     *
     * @param taskList is given task list.
     * @param timeRange is given time range.
     */
    public TaskList(ArrayList<Task> taskList, WeekNumber timeRange) {
        this.taskList = taskList;
        this.timeRange = timeRange;
    }

    /**
     * Initializes a {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.timeRange = AppUtil.getCurrentWeekNumber();
    }

    /**
     * Initializes a {@code TaskList} with a given task list.
     */
    public TaskList(TaskList taskList) {
        resetData(taskList);
    }

    /**
     * Resets the task list with given new data.
     *
     * @param newData is a {@code TaskList}.
     */
    public void resetData(TaskList newData) {
        requireNonNull(newData);
        taskList = newData.getTaskList();
        timeRange = newData.getTimeRange();
    }


    /**
     * Gets a array list consisting of all tasks.
     *
     * @return an ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }


    /**
     * Gets time range.
     *
     * @return a int of time range.
     */
    public WeekNumber getTimeRange() {
        return timeRange;
    }

    /**
     * Sets a time range for task list.
     *
     * @param weekNumber is new time range.
     */
    public void setTimeRange(WeekNumber weekNumber) {
        timeRange = weekNumber;
    }

    /**
     * Checks whether the task is already inside list.
     *
     * @param task is what we want we check.
     * @return true if it is already inside task and false if not.
     */
    public boolean hasTask(Task task) {
        for (Task i : taskList) {
            if (i.isSameTask(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a certain task based on index.
     *
     * @return a task with the required index.
     */
    public Task getTask(Index index) {
        for (Task task: taskList) {
            //index is stored as String in task object,// so a conversion from string to int is needed
            if (task.getIndex().equals(index)) {
                return task;
            }
        }
        return null;
    }

    /**
     * Finds tasks based on existing timeRange.
     *
     * @return a ArrayList consisting of all satisfied tasks.
     */
    public ArrayList<Task> find() {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task: taskList) {
            if (task.isWeekX(timeRange)) {
                newList.add(task);
            }
        }
        return newList;
    }

    /**
     * Finds tasks based on new keyWord.
     *
     * @param keyWord is new keyword given by user.
     * @return a new ArrayList to display.
     */
    public ArrayList<Task> find(String keyWord) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task: taskList) {
            if (task.contains(keyWord)) {
                newList.add(task);
            }
        }
        return newList;
    }

    /**
     * Filter the task list based on criteria given.
     *
     * @param isDone is the boolean value to describe if filter criteria is to display done tasks only
     * @param byOfficialDeadline is the boolean value to describe
     *                           if filter criteria is to display pending tasks by official deadline
     * @param weekNumber is the int value to describe the week number of the tasks to be displayed(optional)
     * @return a new ArrayList to display.
     */
    public ArrayList<Task> filter(boolean isDone, boolean byOfficialDeadline, WeekNumber weekNumber) {
        ArrayList<Task> newList = new ArrayList<>(taskList);
        ArrayList<Task> additionalList = new ArrayList<>();

        if (weekNumber != null) {
            newList = list(weekNumber);
        }

        for (Task task : newList) {
            if (task.isDone() == isDone) {
                additionalList.add(task);
            }
        }

        if (!isDone) {
            if (byOfficialDeadline) {
                Collections.sort(additionalList);
            } else {
                Collections.sort(additionalList, (o1, o2) -> {
                    Deadline ddl1 = (o1.getCustomizedDeadline() != null)
                            ? o1.getCustomizedDeadline() : o1.getOfficialDeadline();
                    Deadline ddl2 = (o2.getCustomizedDeadline() != null)
                            ? o2.getCustomizedDeadline() : o2.getOfficialDeadline();
                    int result = ddl1.compareTo(ddl2);
                    return result;
                });
            }
        }
        return additionalList;
    }

    /**
     * Finds all of tasks in certain week.
     *
     * @param weekNumber A valid week number.
     * @return a array list consisting of all satisfied tasks.
     */
    public ArrayList<Task> list(WeekNumber weekNumber) {
        timeRange = weekNumber;
        ArrayList<Task> newList = new ArrayList<Task>();
        for (Task task: taskList) {
            if (task.isWeekX(weekNumber)) {
                newList.add(task);
            }
        }
        return find();
    }

    /**
     * Initializes a task list by adding a task.
     *
     * @param task A valid task.
     * @return a new array list after find().
     */
    public ArrayList<Task> initialize(Task task) {
        taskList.add(task);
        return find();
    }

    /**
     * Adds a task to task list.
     */
    public ArrayList<Task> add(Task task) {
        timeRange = task.getWeekNumber();

        taskList.add(task);
        return find();
    }

    /**
     * Checks whether the certain task is customized.
     *
     * @param taskIndex A valid task index,
     */

    public boolean isCustomizedTask(Index taskIndex) {
        int i = 0;
        for (Task task : taskList) {
            if (task.hasIndex(taskIndex)) {
                break;
            }
            i++;
        }
        Task task = taskList.get(i);
        return task.isCustomized();
    }

    /**
     * Deletes a certain task.
     *
     * @param taskIndex A valid task index.
     * @return a new array list after find().
     */
    public ArrayList<Task> delete(Index taskIndex) throws InvalidTaskListOperationException {
        int i = 0;
        for (Task task : taskList) {
            if (task.hasIndex(taskIndex)) {
                break;
            }
            i++;
        }
        if (i == taskList.size()) {
            throw new InvalidTaskListOperationException(
                    "The task that you want to delete does not exist in the task list.");
        }
        Task task = taskList.get(i);
        if (task.isCustomized()) {
            taskList.remove(i);
            return find();
        }
        throw new InvalidTaskListOperationException("The task is default task which can not be deleted.");
    }

    /**
     * Mark a task as done.
     *
     * @param taskIndex A valid task index.
     * @return a new array list after find().
     */
    public ArrayList<Task> done(Index taskIndex) throws InvalidTaskListOperationException {
        int i = 0;
        for (Task task : taskList) {
            if (task.hasIndex(taskIndex)) {
                break;
            }
            i++;
        }
        Task task = taskList.get(i);
        if (!task.isDone()) {
            task.markAsDone();
            timeRange = task.getWeekNumber();
            return find();
        }
        throw new InvalidTaskListOperationException("The task is already marked as done.");
    }

    /**
     * Mark a task as pending.
     *
     * @param taskIndex A valid task index.
     * @return a new array list after find().
     */
    public ArrayList<Task> undone(Index taskIndex) throws InvalidTaskListOperationException {
        int i = 0;
        for (Task task : taskList) {
            if (task.hasIndex(taskIndex)) {
                break;
            }
            i++;
        }
        Task task = taskList.get(i);
        if (task.isDone()) {
            task.markAsPending();
            return find();
        }
        throw new InvalidTaskListOperationException("The task is already marked as done.");
    }


    /**
     * Sets a deadline to a certain task.
     *
     * @param taskIndex A valid task index.
     * @param deadline A valid deadline.
     * @return a new array list after find().
     */
    public ArrayList<Task> deadline(Index taskIndex, CustomizedDeadline deadline)
            throws InvalidTaskListOperationException {
        boolean foundTask = false;
        try {
            for (Task task: taskList) {
                if (task.hasIndex(taskIndex)) {
                    task.setDeadline(deadline);
                    foundTask = true;
                }
            }
        } catch (InvalidTaskOperationException e) {
            throw new InvalidTaskListOperationException(e.getMessage());
        }


        if (!foundTask) {
            throw new InvalidTaskListOperationException(
                    "The task that you want to set deadline to is not found in the task list.");
        }
        return find();
    }

    /**
     * Edits a certain task.
     *
     * @param target Target task.
     * @param newTask New task.
     * @return a new array list after find().
     */
    public ArrayList<Task> resetTask(Task target, Task newTask) {
        requireNonNull(target);
        requireNonNull(newTask);

        timeRange = newTask.getWeekNumber();

        int index = taskList.indexOf(target);
        taskList.set(index, newTask);
        return find();
    }

    /**
     * Fetches a task list with current key word and time range.
     *
     * @return a new array list.
     */
    public ArrayList<Task> getUiTaskList() {
        return find();
    }

    /**
     * Gets the size of task list.
     */
    public int size() {
        return taskList.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && taskList.equals(((TaskList) other).taskList));
    }
}

