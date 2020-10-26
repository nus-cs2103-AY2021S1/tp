package seedu.zookeep.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.zookeep.commons.exceptions.IllegalValueException;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;

/**
 * Jackson-friendly version of {@link Animal}.
 */
class JsonAdaptedAnimal {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Animal's %s field is missing!";

    private final String name;
    private final String id;
    private final String species;
    private final List<JsonAdaptedMedicalCondition> medicalConditions = new ArrayList<>();
    private final List<JsonAdaptedFeedTime> feedTimes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAnimal} with the given animal details.
     */
    @JsonCreator
    public JsonAdaptedAnimal(@JsonProperty("name") String name, @JsonProperty("id") String id,
            @JsonProperty("species") String species,
            @JsonProperty("medicalConditions") List<JsonAdaptedMedicalCondition> medicalConditions,
            @JsonProperty("feedTimes") List<JsonAdaptedFeedTime> feedTimes) {
        this.name = name;
        this.id = id;
        this.species = species;
        if (medicalConditions != null) {
            this.medicalConditions.addAll(medicalConditions);
        }
        if (feedTimes != null) {
            this.feedTimes.addAll(feedTimes);
        }
    }

    /**
     * Converts a given {@code Animal} into this class for Jackson use.
     */
    public JsonAdaptedAnimal(Animal source) {
        name = source.getName().fullName;
        id = source.getId().value;
        species = source.getSpecies().value;
        medicalConditions.addAll(source.getMedicalConditions().stream()
                .map(JsonAdaptedMedicalCondition::new)
                .collect(Collectors.toList()));
        feedTimes.addAll(source.getFeedTimes().stream()
                .map(JsonAdaptedFeedTime::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted animal object into the model's {@code Animal} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted animal.
     */
    public Animal toModelType() throws IllegalValueException {
        final List<MedicalCondition> animalMedicalConditions = new ArrayList<>();

        for (JsonAdaptedMedicalCondition medicalCondition : medicalConditions) {
            animalMedicalConditions.add(medicalCondition.toModelType());
        }

        final List<FeedTime> animalFeedTimes = new ArrayList<>();

        for (JsonAdaptedFeedTime feedTime : feedTimes) {
            animalFeedTimes.add(feedTime.toModelType());
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

        if (species == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName()));
        }
        if (!Species.isValidSpecies(species)) {
            throw new IllegalValueException(Species.MESSAGE_CONSTRAINTS);
        }
        final Species modelSpecies = new Species(species);

        final Set<MedicalCondition> modelMedicalConditions = new HashSet<>(animalMedicalConditions);

        final Set<FeedTime> modelFeedTimes = new HashSet<>(animalFeedTimes);

        return new Animal(modelName, modelId, modelSpecies, modelMedicalConditions, modelFeedTimes);
    }

}
