package seedu.address.model;

public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }
}
