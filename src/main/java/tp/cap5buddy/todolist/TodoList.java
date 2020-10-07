package tp.cap5buddy.todolist;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import tp.cap5buddy.todolist.exception.DuplicateTaskException;
import tp.cap5buddy.todolist.exception.TaskNotFoundException;

public class TodoList {
    private final List<Task> list;
    private final List<Task> archives;

    /**
     * Constructs an empty TodoList.
     */
    public TodoList() {
        this.list = new ArrayList<>();
        this.archives = new ArrayList<>();
    }

    /**
     * Constructs a TodoList based on the given list.
     *
     * @param list the list containing task(s) to be added to the todolist.
     */
    public TodoList(List<Task> list) {
        this.list = new ArrayList<>(list);
        this.archives = new ArrayList<>();
    }

    /**
     * Checks if todo list contains the specified task.
     *
     * @param task the task to be checked.
     * @return true if the todolist contains the specified task, false otherwise.
     */
    public boolean contains(Task task) {
        requireNonNull(task);
        return this.list.contains(task);
    }

    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Adds a task to the todolist.
     *
     * @param toAdd task to be added.
     * @return true if the task is added, false otherwise.
     */
    public boolean add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        return this.list.add(toAdd);
    }

    /**
     * Removes a task from the todolist.
     *
     * @param toRemove task to be removed.
     * @return true if the task is removed, false otherwise.
     */
    public boolean remove(Task toRemove) {
        if (!contains(toRemove)) {
            throw new TaskNotFoundException();
        }
        return this.list.remove(toRemove);
    }

    public void clear() {
        this.list.clear();
    }

    public Task set(Task target, Task editedTask) {
        requireNonNull(editedTask);
        int index = this.list.indexOf(target);

        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }
        return this.list.set(index, editedTask);
    }

    /**
     * Sorts todolist by the description of the task lexicographically.
     *
     * @return a new todolist that has been sorted.
     */
    public TodoList sortByDescription() {
        ArrayList<Task> temp = new ArrayList<>(list);
        temp.sort(new TaskComparatorByDescription());
        return new TodoList(temp);
    }

    /**
     * Sorts todolist by the priority of the task from the highest to lowest.
     *
     * @return a new todolist that has been sorted.
     */
    public TodoList sortByPriority() {
        ArrayList<Task> temp = new ArrayList<>(list);
        temp.sort(new TaskComparatorByPriority());
        return new TodoList(temp);
    }

    /**
     * Sorts todolist by the date of the task.
     *
     * @return a new todolist that has been sorted.
     */
    public TodoList sortByDate() {
        ArrayList<Task> temp = new ArrayList<>(list);
        temp.sort(new TaskComparatorByDate());
        return new TodoList(temp);
    }

    /**
     * Archives a task from the main list.
     *
     * @param toArchive task to be archived.
     */
    public void archive(Task toArchive) {
        remove(toArchive);
        this.archives.add(toArchive);
    }

    public int getSize() {
        return this.list.size();
    }
}
