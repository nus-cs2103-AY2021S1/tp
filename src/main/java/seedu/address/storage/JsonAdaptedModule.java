package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
// import seedu.address.model.contact.Contact;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String name;
    private final String zoomLink;
    private final String modularCredits;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final JsonAdaptedGradeTracker gradeTracker;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name,
                             @JsonProperty("zoomLink") String zoomLink,
                             @JsonProperty("gradeTracker") JsonAdaptedGradeTracker storedGradeTracker,
                             @JsonProperty("modularCredits") String storedModularCredits,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.zoomLink = zoomLink;
        if (storedGradeTracker == null) {
            this.gradeTracker = new JsonAdaptedGradeTracker(new GradeTracker());
        } else {
            this.gradeTracker = storedGradeTracker;
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.modularCredits = storedModularCredits;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName().fullName;
        if (source.getLink() == null) {
            zoomLink = null;
        } else {
            zoomLink = source.getLink().value;
        }
        gradeTracker = new JsonAdaptedGradeTracker(source.getGradeTracker());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        modularCredits = source.getModularCredits().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {
        final List<Tag> moduleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            moduleTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidName(name)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelName = new ModuleName(name);
        final ZoomLink modelLink;
        if (zoomLink == null) {
            /*throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ZoomLink.class.getSimpleName()));*/
            modelLink = new ZoomLink("Not provided");
        } else if (!ZoomLink.isValidZoomLink(zoomLink)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        } else {
            modelLink = new ZoomLink(zoomLink);
        }
        if (gradeTracker == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GradeTracker.class.getSimpleName()));
        }
        if (!GradeTracker.isValidGradeTracker(gradeTracker.toModelType())) {
            throw new IllegalValueException(GradeTracker.MESSAGE_INVALID_GRADE);
        }
        if (!ModularCredits.isValidModularCredits(modularCredits)) {
            throw new IllegalValueException(ModularCredits.MESSAGE_CONSTRAINTS);
        }
        final ModularCredits modelModularCredits = new ModularCredits(Double.parseDouble(modularCredits));
        final Set<Tag> modelTags = new HashSet<>(moduleTags);
        return new Module(modelName, modelLink, gradeTracker.toModelType(), modelTags, modelModularCredits);
    }

}
