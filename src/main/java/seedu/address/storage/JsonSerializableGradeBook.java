package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Module;

/**
 * An Immutable GradeBook that is serializable to JSON format.
 */
@JsonRootName(value = "gradebook")
class JsonSerializableGradeBook {

    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    private final GoalTarget goalTarget;

    /**
     * Constructs a {@code JsonSerializableGradeBook} with the given modules.
     */
    @JsonCreator
    public JsonSerializableGradeBook(@JsonProperty("modules") List<JsonAdaptedModule> modules,
                                     @JsonProperty("goalTarget") GoalTarget goalTarget) {
        this.goalTarget = goalTarget;
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyGradeBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGradeBook}.
     * @param goalTarget
     */
    public JsonSerializableGradeBook(ReadOnlyGradeBook source, GoalTarget goalTarget) {
        this.goalTarget = goalTarget;
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code GradeBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GradeBook toModelType() throws IllegalValueException {
        GradeBook gradeBook = new GradeBook();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (gradeBook.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            gradeBook.addModule(module);
        }
        return gradeBook;
    }

    @JsonProperty(value = "goalTarget")
    public GoalTarget getGoalTarget() {
        return goalTarget;
    }
}
