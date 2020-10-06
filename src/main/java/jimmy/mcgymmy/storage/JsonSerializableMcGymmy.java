package jimmy.mcgymmy.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.storage.JsonAdaptedFood;


/**
 * An Immutable McGymmy that is serializable to JSON format.
 */
@JsonRootName(value = "mcgymmy")
class JsonSerializableMcGymmy {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate food(s).";

    private final List<JsonAdaptedFood> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMcGymmy} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMcGymmy(@JsonProperty("persons") List<JsonAdaptedFood> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyMcGymmy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMcGymmy}.
     */
    public JsonSerializableMcGymmy(ReadOnlyMcGymmy source) {
        persons.addAll(source.getFoodList().stream().map(JsonAdaptedFood::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code McGymmy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public McGymmy toModelType() throws IllegalValueException {
        McGymmy mcGymmy = new McGymmy();
        for (JsonAdaptedFood jsonAdaptedFood : persons) {
            Food food = jsonAdaptedFood.toModelType();
            if (mcGymmy.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            mcGymmy.addFood(food);
        }
        return mcGymmy;
    }

}
