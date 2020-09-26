package seedu.address.model.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private String name;
    private String description;
    private LocalDate publishDate;
    private LocalDate startDate;
    private LocalDateTime deadline;
    private LocalDate lastDoneOn;
    private int frequency;
    private LocalDate endDate;
    private double progress;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        publishDate = LocalDate.now();
        startDate = null;
        deadline = null;
        lastDoneOn = null;
        frequency = 0;
        endDate = null;
        progress = 0;
    }
}
