package seedu.zookeep.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.zookeep.commons.exceptions.IllegalValueException;
import seedu.zookeep.model.feedtime.FeedTime;

/**
 * Jackson-friendly version of {@link FeedTime}.
 */
class JsonAdaptedFeedTime {

    private final String feedTimeText;

    /**
     * Constructs a {@code JsonAdaptedFeedTime} with the given {@code feedTimeText}.
     */
    @JsonCreator
    public JsonAdaptedFeedTime(String feedTimeText) {
        this.feedTimeText = feedTimeText;
    }

    /**
     * Converts a given {@code FeedTime} into this class for Jackson use.
     */
    public JsonAdaptedFeedTime(FeedTime source) {
        feedTimeText = source.feedTime;
    }

    @JsonValue
    public String getFeedTimeName() {
        return feedTimeText;
    }

    /**
     * Converts this Jackson-friendly adapted feedTime object into the model's {@code FeedTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted feedTime.
     */
    public FeedTime toModelType() throws IllegalValueException {
        if (!FeedTime.isValidFeedTime(feedTimeText)) {
            throw new IllegalValueException(FeedTime.MESSAGE_CONSTRAINTS);
        }
        return new FeedTime(feedTimeText);
    }

}
