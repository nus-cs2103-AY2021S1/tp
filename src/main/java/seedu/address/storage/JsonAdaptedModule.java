package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.contact.Contact;

/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String name;
    private final String zoomLink;
    //private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name,
                             @JsonProperty("zoomLink") String zoomLink) {
        this.name = name;
        this.zoomLink = zoomLink;
        //tagging temporarily removed
        /*if (tagged != null) {
            this.tagged.addAll(tagged);
        }*/
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName().fullName;
        zoomLink = source.getLink().value;
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
        //email and tagging removed temporarily
        /*if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        final Set<Tag> modelTags = new HashSet<>(personTags);*/
        return new Module(modelName, modelLink);
    }

}
