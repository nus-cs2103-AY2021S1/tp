package seedu.simplykitchen.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;
import seedu.simplykitchen.model.food.Food;

/**
 * An Immutable Food inventory that is serializable to JSON format.
 */
@JsonRootName(value = "foodInventory")
class JsonSerializableFoodInventory {

    public static final String MESSAGE_DUPLICATE_FOOD = "Food list contains duplicate food(s).";

    private final List<JsonAdaptedFood> foods = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFoodInventory} with the given food items.
     */
    @JsonCreator
    public JsonSerializableFoodInventory(@JsonProperty("foods") List<JsonAdaptedFood> foods) {
        this.foods.addAll(foods);
    }

    /**
     * Converts a given {@code ReadOnlyFoodInventory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFoodInventory}.
     */
    public JsonSerializableFoodInventory(ReadOnlyFoodInventory source) {
        foods.addAll(source.getFoods().stream().map(JsonAdaptedFood::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Food inventory into the model's {@code FoodInventory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodInventory toModelType() throws IllegalValueException {
        FoodInventory foodInventory = new FoodInventory();
        for (JsonAdaptedFood jsonAdaptedFood : foods) {
            Food food = jsonAdaptedFood.toModelType();
            if (foodInventory.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FOOD);
            }
            foodInventory.addFood(food);
        }
        return foodInventory;
    }

}
