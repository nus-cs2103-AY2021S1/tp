package seedu.pivot.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.investigationcase.Case;

/**
 * An Immutable PIVOT that is serializable to JSON format.
 */
@JsonRootName(value = "pivot")
class JsonSerializablePivot {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    private static final Logger logger = LogsCenter.getLogger(JsonSerializablePivot.class);

    private final List<JsonAdaptedCase> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePivot} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePivot(@JsonProperty("persons") List<JsonAdaptedCase> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPivot} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePivot}.
     */
    public JsonSerializablePivot(ReadOnlyPivot source) {
        persons.addAll(source.getCaseList().stream().map(JsonAdaptedCase::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Pivot into the model's {@code Pivot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Pivot toModelType() throws IllegalValueException {
        logger.info("Converting JSON to PIVOT models");
        Pivot pivot = new Pivot();
        for (JsonAdaptedCase jsonAdaptedCase : persons) {
            Case investigationCase = jsonAdaptedCase.toModelType();
            if (pivot.hasCase(investigationCase)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            pivot.addCase(investigationCase);
        }
        return pivot;
    }

}
