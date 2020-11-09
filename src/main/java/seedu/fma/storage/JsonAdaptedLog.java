package seedu.fma.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.fma.commons.exceptions.IllegalValueException;
import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;
import seedu.fma.model.util.Name;

/**
 * Jackson-friendly version of {@link Log}.
 */
class JsonAdaptedLog {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Log's %s field is missing!";

    private final String exercise;
    private final String dateTime;
    private final String rep;
    private final String comment;

    /**
     * Constructs a {@code JsonAdaptedLog} with the given log details.
     */
    @JsonCreator
    public JsonAdaptedLog(@JsonProperty("exercise") String exercise, @JsonProperty("dateTime") String dateTime,
                          @JsonProperty("rep") String rep, @JsonProperty("comment") String comment) {
        this.exercise = exercise;
        this.dateTime = dateTime;
        this.rep = rep;
        this.comment = comment;
    }

    /**
     * Converts a given {@code Log} into this class for Jackson use.
     */
    public JsonAdaptedLog(Log source) {
        exercise = source.getExercise().getName().toString();
        dateTime = source.getDateTime().toString();
        rep = source.getReps().toString();
        comment = source.getComment().value;
    }

    /**
     * Converts this Jackson-friendly adapted log object into the model's {@code Log} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted log.
     */
    public Log toModelType(LogBook logBook) throws IllegalValueException {
        if (exercise == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Exercise.class.getSimpleName()));
        }
        Exercise modelExercise;
        try {
            modelExercise = logBook.getExercise(new Name(exercise));
        } catch (ExerciseNotFoundException e) {
            throw new IllegalValueException(Exercise.MESSAGE_CONSTRAINTS);
        }

        if (dateTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }
        LocalDateTime modelLocalDateTime;
        try {
            modelLocalDateTime = LocalDateTime.parse(dateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("Date and Time should be in ISO_LOCAL_DATE_TIME format");
        }

        if (rep == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rep.class.getSimpleName()));
        }
        try {
            Integer.parseInt(rep);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Rep.NUMBER_CONSTRAINTS);
        }
        if (!Rep.isValidRep(Integer.parseInt(rep))) {
            throw new IllegalValueException(Rep.MESSAGE_CONSTRAINTS);
        }
        final Rep modelRep = new Rep(Integer.parseInt(rep));

        if (comment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Comment.class.getSimpleName()));
        }
        if (!Comment.isValidComment(comment)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }
        final Comment modelComment = new Comment(comment);

        return new Log(modelExercise, modelRep, modelComment, modelLocalDateTime);
    }
}
