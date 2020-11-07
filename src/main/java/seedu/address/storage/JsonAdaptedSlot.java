package seedu.address.storage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.routine.Routine;
import seedu.address.model.tag.Tag;
import seedu.address.model.timetable.Activity;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.timetable.Slot;
import seedu.address.model.util.Name;

/**
 * Jackson-friendly version of {@link Slot}.
 */
public class JsonAdaptedSlot {
    public static final String TYPE_ROUTINE = "routine";
    public static final String TYPE_LESSON = "lesson";

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Slot's %s field is missing!";

    private final String activityName;
    private final String type;
    private final String day;
    private final String duration;
    private final List<JsonAdaptedTag> tagsForLesson = new ArrayList<>();
    private final List<JsonAdaptedExercise> exercisesForRoutine = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSlot} with the given slot details.
     */
    @JsonCreator
    public JsonAdaptedSlot(@JsonProperty("activityName") String activityName,
                           @JsonProperty("type") String type,
                           @JsonProperty("day") String day,
                           @JsonProperty("duration") String duration,
                           @JsonProperty("tagsForLesson") List<JsonAdaptedTag> tags,
                           @JsonProperty("exercisesForRoutine") List<JsonAdaptedExercise> exercises) {
        this.activityName = activityName;
        this.type = type;
        this.day = day;
        this.duration = duration;
        if (type.equals(TYPE_LESSON)) {
            if (tags != null) {
                tagsForLesson.addAll(tags);
            }
        } else {
            if (exercises != null) {
                exercisesForRoutine.addAll(exercises);
            }
        }
    }

    /**
     * Converts a given {@code Slot} into this class for Jackson use.
     */
    public JsonAdaptedSlot(Slot source) {
        activityName = source.getActivity().getName().fullName;
        type = source.getActivity() instanceof Routine
                ? TYPE_ROUTINE
                : TYPE_LESSON;
        day = source.getDay().toString().toLowerCase();
        duration = source.getDuration().toString();
        if (type.equals(TYPE_LESSON)) {
            Lesson lesson = (Lesson) source.getActivity();
            tagsForLesson.addAll(lesson.getTags()
                    .stream()
                    .map(JsonAdaptedTag::new)
                    .collect(Collectors.toList()));
        } else {
            Routine routine = (Routine) source.getActivity();
            exercisesForRoutine.addAll(routine.getExercises()
                    .stream()
                    .map(JsonAdaptedExercise::new)
                    .collect(Collectors.toList()));
        }
    }

    /**
     * Converts this Jackson-friendly adapted slot object into the model's {@code Slot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted slot.
     */
    public Slot toModelType() throws IllegalValueException {

        if (activityName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(activityName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (Day.isUnknownDay(Day.getDayEnum(day))) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        if (!Duration.isValidDuration(duration)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS_FORMAT);
        }

        final Name modelName = new Name(activityName);

        final Activity modelActivity;
        if (type.equals(TYPE_LESSON)) {
            final List<Tag> lessonTags = new ArrayList<>();
            for (JsonAdaptedTag tag : tagsForLesson) {
                lessonTags.add(tag.toModelType());
            }
            final Set<Tag> modelTags = new HashSet<>(lessonTags);
            modelActivity = new Lesson(modelName, modelTags);
        } else {
            final List<Exercise> routineExercises = new ArrayList<>();
            for (JsonAdaptedExercise exercise : exercisesForRoutine) {
                routineExercises.add(exercise.toModelType());
            }
            final Set<Exercise> modelExercise = new HashSet<>(routineExercises);
            Routine modelRoutine = new Routine(modelName);
            for (Exercise exercise : modelExercise) {
                modelRoutine.addExercise(exercise);
            }
            modelActivity = modelRoutine;
        }

        final Day modelDay = Day.getDayEnum(day);

        final Duration modelDuration = parseDuration(duration);

        return new Slot(modelActivity, modelDay, modelDuration);
    }

    private Duration parseDuration(String toParse) throws IllegalValueException {
        String[] timeSplit = toParse.split("-");

        int startHour = Integer.parseInt(timeSplit[0].substring(0, 2));
        int startMinute = Integer.parseInt(timeSplit[0].substring(2, 4));
        LocalTime startTime = LocalTime.of(startHour, startMinute);

        int endHour = Integer.parseInt(timeSplit[1].substring(0, 2));
        int endMinute = Integer.parseInt(timeSplit[1].substring(2, 4));
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        if (!Duration.isValidDuration(startTime, endTime)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS_ORDER);
        }

        return new Duration(startTime, endTime);
    }
}
