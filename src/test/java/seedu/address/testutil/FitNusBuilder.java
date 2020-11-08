package seedu.address.testutil;

import seedu.address.model.FitNus;
import seedu.address.model.lesson.Lesson;

/**
 * A utility class to help with building fitNUS objects.
 * Example usage: <br>
 *     {@code FitNus fn = new FitNusBuilder().withLesson("MA1521", "MA1101R").build();}
 */
public class FitNusBuilder {

    private FitNus fitNus;

    public FitNusBuilder() {
        fitNus = new FitNus();
    }

    public FitNusBuilder(FitNus fitNus) {
        this.fitNus = fitNus;
    }

    /**
     * Adds a new {@code Lesson} to the {@code fitNUS} that we are building.
     */
    public FitNusBuilder withLesson(Lesson lesson) {
        fitNus.addLesson(lesson);
        return this;
    }

    public FitNus build() {
        return fitNus;
    }
}
