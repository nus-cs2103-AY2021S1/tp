package seedu.address.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Performance;

public class PerformanceStorage {

    private String filepath;

    public PerformanceStorage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads Performance.
     */
    public Performance load() throws IOException {
        createFile();
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        Performance performance = new Performance();
        while (scanner.hasNext()) {
            Attempt attempt = new AttemptParser().parseAttempt(scanner.nextLine());
            performance.addAttempt(attempt);
        }
        scanner.close();
        return performance;
    }

    /**
     * Saves performance.
     */
    public void save(Performance performance) throws IOException {
        File file = new File(filepath);
        new FileWriter(file, false).close();
        FileWriter filewriter = new FileWriter(file, true);
        String composedAttempt = "";
        for (Attempt attempt : performance.getAttempts()) {
            composedAttempt = new AttemptComposer().composeAttempt(attempt);
            composedAttempt += System.lineSeparator();
            filewriter.write(composedAttempt);
        }
        filewriter.close();
    }

    /**
     * Creates file if not found.
     */
    public void createFile() throws IOException {
        File file = new File(filepath.substring(0, filepath.lastIndexOf("/")));
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        file = new File(filepath);
        file.createNewFile();
    }
}
