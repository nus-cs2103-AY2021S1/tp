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
}
