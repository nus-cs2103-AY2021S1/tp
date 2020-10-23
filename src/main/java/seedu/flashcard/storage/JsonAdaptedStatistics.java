package seedu.flashcard.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.flashcard.commons.exceptions.IllegalValueException;
import seedu.flashcard.model.flashcard.Statistics;

/**
 * Jackson-friendly version of {@link Statistics}.
 */
class JsonAdaptedStatistics {

    private final int reviewFrequency;
    private final int successFrequency;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedStatistics(int reviewFrequency, int successFrequency) {
        this.reviewFrequency = reviewFrequency;
        this.successFrequency = successFrequency;
    }

    /**
     * Converts a given {@code Statistics} into this class for Jackson use.
     */
    public JsonAdaptedStatistics(Statistics source) {
        this.reviewFrequency = source.getReviewFrequency();
        this.successFrequency = source.getSuccessFrequency();
    }

    /**
     * Converts this Jackson-friendly adapted statistics object into the model's {@code Statistics} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Statistics toModelType() throws IllegalValueException {
        return new Statistics(reviewFrequency, successFrequency);
    }

}
