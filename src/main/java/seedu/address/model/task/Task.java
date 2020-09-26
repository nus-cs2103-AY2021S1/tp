package seedu.address.model.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private String name;
    private String description;
    private LocalDate publishDate;
    private LocalDateTime deadline;
    private double progress;

    public Task(String name, String description) {
        initialize(name, description);
    }

    public Task(String name, String description, LocalDateTime deadline) {
        initialize(name, description);
        setDeadline(deadline);
    }

    private void initialize(String name, String description) {
        this.name = name;
        this.description = description;
        publishDate = LocalDate.now();
        deadline = null;
        progress = 0;
    }

    private void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
