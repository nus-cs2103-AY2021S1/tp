package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
// import seedu.address.model.contact.Contact;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradeTracker;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String name;
    private final String zoomLink;
    private final JsonAdaptedGradeTracker gradeTracker;
    //private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name,
                             @JsonProperty("zoomLink") String zoomLink,
                             @JsonProperty("gradeTracker") JsonAdaptedGradeTracker storedGradeTracker) {
        this.name = name;
        this.zoomLink = zoomLink;
        if (storedGradeTracker == null) {
            this.gradeTracker = new JsonAdaptedGradeTracker(new GradeTracker());
        } else {
            this.gradeTracker = storedGradeTracker;
        }
        //tagging temporarily removed
        /*if (tagged != null) {
            this.tagged.addAll(tagged);
        }*/
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName().fullName;
        zoomLink = source.getLink().value;
        gradeTracker = new JsonAdaptedGradeTracker(source.getGradeTracker());
        //tagging temporarily removed
        /*tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));*/
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {
        /*final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }*/

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidName(name)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelName = new ModuleName(name);
        if (zoomLink == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ZoomLink.class.getSimpleName()));
        }
        if (!ZoomLink.isValidZoomLink(zoomLink)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ZoomLink modelLink = new ZoomLink(zoomLink);
        if (gradeTracker == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GradeTracker.class.getSimpleName()));
        }
        if (!GradeTracker.isValidGradeTracker(gradeTracker.toModelType())) {
            throw new IllegalValueException(GradeTracker.MESSAGE_INVALID_GRADE);
        }
        //email and tagging removed temporarily
        /*if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        final Set<Tag> modelTags = new HashSet<>(personTags);*/
        return new Module(modelName, modelLink, gradeTracker.toModelType());
    }

}
