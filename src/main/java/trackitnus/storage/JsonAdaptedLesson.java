package trackitnus.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import trackitnus.commons.exceptions.IllegalValueException;
import trackitnus.logic.parser.ParserUtil;
import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.lesson.LessonDateTime;
import trackitnus.model.lesson.Type;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String code;
    private final String type;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("code") String code, @JsonProperty("type") String type,
                             @JsonProperty("date") String date) {
        this.code = code;
        this.type = type;
        this.date = date;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        code = source.getCode().code;
        type = source.getTypeStr();
        date = source.getDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Lesson toModelType() throws IllegalValueException {

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName()));
        }
        if (!Code.isValidCode(code)) {
            throw new IllegalValueException(Code.MESSAGE_CONSTRAINTS);
        }
        final Code modelCode = new Code(code);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        final Type modelType = Enum.valueOf(Type.class, type);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LocalDate.class.getSimpleName()));
        }
        try {
            ParserUtil.parseLessonDateTime(date);
        } catch (Exception e) {
            throw new IllegalValueException(Lesson.DATE_MESSAGE_CONSTRAINTS);
        }
        final LessonDateTime modelTime = ParserUtil.parseLessonDateTime(date);

        return new Lesson(modelCode, modelType, modelTime);
    }
}
