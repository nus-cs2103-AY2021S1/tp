package tp.cap5buddy.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import tp.cap5buddy.grades.Grade;
import tp.cap5buddy.modules.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedGrade {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String name;
    private final int percentageOfFinalGrade;
    private final double results;

    /**
     * Constructs a {@code JsonAdaptedGrade} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGrade(@JsonProperty("name") String name,
                            @JsonProperty("percentageOfFinalGrade") int percentageOfFinalGrade,
                            @JsonProperty("results") double results) {
        this.name = name;
        this.percentageOfFinalGrade = percentageOfFinalGrade;
        this.results = results;
    }

    /**
     * Converts a given {@code Grade} into this class for Jackson use.
     */
    public JsonAdaptedGrade(Grade source) {
        name = source.getName();
        percentageOfFinalGrade = source.getPercentageOfFinalGrade();
        results = source.getResults();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Grade toModelType() throws IllegalValueException {
        return new Grade(name, percentageOfFinalGrade, results);
    }
}
