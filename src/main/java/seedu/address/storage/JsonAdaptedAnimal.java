package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Email;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Animal}.
 */
class JsonAdaptedAnimal {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Animal's %s field is missing!";

    private final String name;
    private final String id;
    private final String email;
    private final String species;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAnimal} with the given animal details.
     */
    @JsonCreator
    public JsonAdaptedAnimal(@JsonProperty("name") String name, @JsonProperty("id") String id,
            @JsonProperty("email") String email, @JsonProperty("species") String species,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.species = species;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Animal} into this class for Jackson use.
     */
    public JsonAdaptedAnimal(Animal source) {
        name = source.getName().fullName;
        id = source.getId().value;
        email = source.getEmail().value;
        species = source.getSpecies().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted animal object into the model's {@code Animal} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted animal.
     */
    public Animal toModelType() throws IllegalValueException {
        final List<Tag> animalTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            animalTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (species == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName()));
        }
        if (!Species.isValidSpecies(species)) {
            throw new IllegalValueException(Species.MESSAGE_CONSTRAINTS);
        }
        final Species modelSpecies = new Species(species);

        final Set<Tag> modelTags = new HashSet<>(animalTags);

        return new Animal(modelName, modelId, modelEmail, modelSpecies, modelTags);
    }

}
