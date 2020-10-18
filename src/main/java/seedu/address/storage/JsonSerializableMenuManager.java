package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;


/**
 * An Immutable MenuManager that is serializable to JSON format.
 */
@JsonRootName(value = "menuManager")
class JsonSerializableMenuManager {

    public static final String MESSAGE_DUPLICATE_FOOD = "Menu contains duplicate foods.";

    private final List<JsonAdaptedFood> foods = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMenuManager} with the given foods.
     */
    @JsonCreator
    public JsonSerializableMenuManager(@JsonProperty("foods") List<JsonAdaptedFood> foods) {
        this.foods.addAll(foods);
    }

    /**
     * Converts a given {@code ReadOnlyMenuManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMenuManager}.
     */
    public JsonSerializableMenuManager(ReadOnlyMenuManager source) {
        foods.addAll(source.getFoodList().stream().map(JsonAdaptedFood::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code MenuManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MenuManager toModelType() throws IllegalValueException {
        MenuManager menuManager = new MenuManager();
        for (JsonAdaptedFood jsonAdaptedFood : foods) {
            Food food = jsonAdaptedFood.toModelType();
            if (menuManager.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FOOD);
            }
            menuManager.addFood(food);
        }
        return menuManager;
    }

}
