package seedu.address.storage;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ItemList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.item.Item;


/**
 * An Immutable ItemList that is serializable to JSON format.
 */
@JsonRootName(value = "itemlist")
class JsonSerializableItemList {

    public static final String MESSAGE_DUPLICATE_ITEM = "Items list contains duplicate item(s).";

    private final List<JsonAdaptedItem> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableItemList} with the given items.
     */
    @JsonCreator
    public JsonSerializableItemList(@JsonProperty("items") List<JsonAdaptedItem> items) {
        this.items.addAll(items);
    }

    /**
     * Converts a given {@code ReadOnlyItemList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableItemList}.
     */
    public JsonSerializableItemList(ReadOnlyItemList source) {
        items.addAll(source.getItemList().stream().map(JsonAdaptedItem::new).collect(Collectors.toList()));
    }

    /**
     * Converts this item list into the model's {@code ItemList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ItemList toModelType() throws IllegalValueException {
        ItemList itemList = new ItemList();
        for (JsonAdaptedItem jsonAdaptedItem : items) {
            Item item = jsonAdaptedItem.toModelType();
            if (item.getId() > Item.getIdCounter()) {
                Item.setIdCounter(item.getId());
            }
            if (itemList.hasItem(item)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            itemList.addItem(item);
        }
        return itemList;
    }

}
