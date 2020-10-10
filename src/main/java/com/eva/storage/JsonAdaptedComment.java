package com.eva.storage;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.person.comment.Comment;
import com.eva.model.tag.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedComment {

    private final LocalDate date;
    private final String description;
    private final int identity;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedComment(String description) {
        this.date = LocalDate.parse(description.split(" ", 2)[0]);
        this.description = description.split(" ", 2)[1];
        this.identity = Integer.parseInt(description.substring(description.length() - 1));
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedComment(Comment source) {
        date = source.date;
        description = source.description;
        identity = source.getIdentity();
    }

    @JsonValue
    public String getCommentDate() {
        if (description.substring(description.length() - 1).equals(Integer.toString(identity))) {
            return date.toString() + " " + description;
        }
        return date.toString() + " " + description + " " + identity;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Comment toModelType() throws IllegalValueException {
        if (!Comment.isValidComment(date.toString() + " " + description + " " + identity)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Comment(date, description, identity);
    }

}
