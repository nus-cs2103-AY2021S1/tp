package seedu.zookeep.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.zookeep.commons.exceptions.IllegalValueException;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.animal.Animal;

/**
 * An Immutable ZooKeepBook that is serializable to JSON format.
 */
@JsonRootName(value = "zookeepbook")
class JsonSerializableZooKeepBook {

    public static final String MESSAGE_DUPLICATE_ANIMAL = "Animals list contains duplicate animal(s).";

    private final List<JsonAdaptedAnimal> animals = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableZooKeepBook} with the given animals.
     */
    @JsonCreator
    public JsonSerializableZooKeepBook(@JsonProperty("animals") List<JsonAdaptedAnimal> animals) {
        this.animals.addAll(animals);
    }

    /**
     * Converts a given {@code ReadOnlyZooKeepBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableZooKeepBook}.
     */
    public JsonSerializableZooKeepBook(ReadOnlyZooKeepBook source) {
        animals.addAll(source.getAnimalList().stream().map(JsonAdaptedAnimal::new).collect(Collectors.toList()));
    }

    /**
     * Converts this zookeep book into the model's {@code ZooKeepBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ZooKeepBook toModelType() throws IllegalValueException {
        ZooKeepBook zooKeepBook = new ZooKeepBook();
        for (JsonAdaptedAnimal jsonAdaptedAnimal : animals) {
            Animal animal = jsonAdaptedAnimal.toModelType();
            if (zooKeepBook.hasAnimal(animal)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ANIMAL);
            }
            zooKeepBook.addAnimal(animal);
        }
        return zooKeepBook;
    }

}
