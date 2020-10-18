package tp.cap5buddy.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import tp.cap5buddy.modules.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String name;
    private final String zoomLink;
    //private final JsonAdaptedGrade finalGrade;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name, @JsonProperty("zoomLink") String zoomLink
                             /*@JsonProperty("finalGrade") JsonAdaptedGrade finalGrade*/) {
        this.name = name;
        this.zoomLink = zoomLink;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName();
        zoomLink = source.getLink();
        //finalGrade = new JsonAdaptedGrade(finalGrade1.getName(), 100, finalGrade1.getResults());
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        return new Module(name, zoomLink);
    }
}
