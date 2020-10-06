package tp.cap5buddy.todolist;

import tp.cap5buddy.todolist.exception.DuplicateTaskException;
import tp.cap5buddy.todolist.exception.TaskNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class TodoList {
    private final List<Task> list;
    private final List<Task> archives;

    public TodoList() {
        this.list = new ArrayList<>();
        this.archives = new ArrayList<>();
    }

    public TodoList(List<Task> list) {
        this.list = new ArrayList<>(list);
        this.archives = new ArrayList<>();
    }

    public boolean contains(Task task) {
        requireNonNull(task);
        return this.list.contains(task);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public boolean add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        return this.list.add(toAdd);
    }

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

    public TodoList sortByDescription() {
        ArrayList<Task> temp = new ArrayList<>(list);
        temp.sort(new TaskComparatorByDescription());
        return new TodoList(temp);
    }

    public TodoList sortByPriority() {
        ArrayList<Task> temp = new ArrayList<>(list);
        temp.sort(new TaskComparatorByPriority());
        return new TodoList(temp);
    }

    public TodoList sortByDate() {
        ArrayList<Task> temp = new ArrayList<>(list);
        temp.sort(new TaskComparatorByDate());
        return new TodoList(temp);
    }

    public void archive(Task toArchive) {
        remove(toArchive);
        this.archives.add(toArchive);
    }
}
