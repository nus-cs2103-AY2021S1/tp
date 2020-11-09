package seedu.address.storage;

import java.io.IOException;

import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Performance;

public class PerformanceBook {

    private static final String performanceFilePath = "data/performance.txt";
    private Performance performance;
    private PerformanceStorage performanceStorage;

    /**
     * Creates performance book.
     */
    public PerformanceBook() throws IOException {
        performanceStorage = new PerformanceStorage(performanceFilePath);
        performance = performanceStorage.load();
    }

    /**
     * Creates performance book using given performance.
     */
    public PerformanceBook(Performance performance) {
        this.performance = performance;
        performanceStorage = new PerformanceStorage(performanceFilePath);
    }

    public static PerformanceBook createDefaultPerformanceBook() {
        return new PerformanceBook(new Performance());
    }

    /**
     * Saves new attempt;
     */
    public void saveAttempt(Attempt attempt) throws IOException {
        performance.addAttempt(attempt);
        performanceStorage.save(performance);
    }

    public void savePerformance() throws IOException {
        performanceStorage.save(performance);
    }

    public Performance getPerformance() {
        return performance;
    }
}

