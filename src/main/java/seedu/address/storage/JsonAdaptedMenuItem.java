package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.Food;
import seedu.address.model.food.MenuItem;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link seedu.address.model.food.Food}.
 */
public class JsonAdaptedMenuItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Food's %s field is missing!";
    public static final String INVALID_PRICE_FORMAT = "Price must be a double and non-negative.";

    private final String name;
    private final double price;
    private final String filePath;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFood} with the given food details.
     */
    @JsonCreator
    public JsonAdaptedMenuItem(@JsonProperty("name") String name, @JsonProperty("price") double price,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("imagePath") String filePath) {
        this.name = name;
        this.price = price;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.filePath = filePath;
    }

    /**
     * Converts a given {@code Food} into this class for Jackson use.
     */
    public JsonAdaptedMenuItem(MenuItem source) {
        name = source.getName();
        price = source.getPrice();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        filePath = source.getFilePath();
    }

    /**
     * Converts this Jackson-friendly adapted menu item object into the model's {@code MenuItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted food.
     */
    public MenuItem toModelType() throws IllegalValueException {
        final List<Tag> foodTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            foodTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }

        if (!Food.isValidPrice(price)) {
            throw new IllegalValueException("Price must be a double and non-negative.");
        }

        if (filePath == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Image"));
        }

        final Set<Tag> modelTags = new HashSet<>(foodTags);
        return new MenuItem(name, price, modelTags, filePath);
    }

}
