package com.eva.storage;

import java.time.LocalDate;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.comment.Comment;
import com.eva.model.tag.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedComment {

    private final LocalDate date;
    private final String description;
    private final String title;

    /**
     * Constructs a {@code JsonAdaptedComment} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedComment(String description) {
        this.date = LocalDate.parse(description.split("\\|", 3)[1]);
        this.description = description.split("\\|", 3)[2];
        this.title = description.split("\\|", 3)[0];
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedComment(Comment source) {
        date = source.date;
        description = source.getDescription();
        title = source.getTitle().getTitleDescription();
    }

    @JsonValue
    public String getCommentDetails() {
        return title + "|" + date.toString() + "|" + description;
    }



    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Comment toModelType() throws IllegalValueException {
        if (!Comment.isValidAddComment(" d/ " + date.toString() + " desc/ " + description + " ti/ " + title)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(date, description, title);
    }

}
