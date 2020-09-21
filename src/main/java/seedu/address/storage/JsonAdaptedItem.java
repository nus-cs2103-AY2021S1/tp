package seedu.address.storage;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Item}.
 */
class JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    // Identity fields
    private final int id;
    private final String name;

    // Data fields
    private final String quantity;
    private final String description;
    private final List<Integer> locationIds = new ArrayList<>();
    private final List<Integer> recipeIds = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final boolean isDeleted;

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("id") int id,
                           @JsonProperty("name") String name,
                           @JsonProperty("quantity") String quantity,
                           @JsonProperty("description") String description,
                           @JsonProperty("locationIds") List<Integer> locationIds,
                           @JsonProperty("recipeIds") List<Integer> recipeIds,
                           @JsonProperty("tags") List<JsonAdaptedTag> tagged,
                           @JsonProperty("isDeleted") boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.isDeleted = isDeleted;
        if (locationIds != null) {
            this.locationIds.addAll(locationIds);
        }
        if (recipeIds != null) {
            this.recipeIds.addAll(recipeIds);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        id = source.getId();
        name = source.getName();
        quantity = source.getQuantity().value;
        description = source.getDescription();
        isDeleted = source.isDeleted();
        locationIds.addAll(new ArrayList<>(source.getLocationIds()));
        recipeIds.addAll(new ArrayList<>(source.getRecipeIds()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Item toModelType() throws IllegalValueException {
        final List<Tag> itemTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            itemTags.add(tag.toModelType());
        }

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        final Set<Tag> modelTags = new HashSet<>(itemTags);
        final Set<Integer> modelLocationIds = new HashSet<>(locationIds);
        final Set<Integer> modelRecipeIds = new HashSet<>(recipeIds);
        return new Item(id, name, modelQuantity, description, modelLocationIds,
                modelRecipeIds, modelTags, isDeleted);
    }

}
