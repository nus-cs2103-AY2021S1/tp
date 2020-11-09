package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AttractionList;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.attraction.Attraction;

/**
 * An Immutable AttractionList that is serializable to JSON format.
 */
@JsonRootName(value = "attractionlist")
class JsonSerializableAttractionList {

    public static final String MESSAGE_DUPLICATE_ATTRACTION = "Attraction list contains duplicate attraction(s).";

    private final List<JsonAdaptedAttraction> attractions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAttractionList} with the given attractions.
     */
    @JsonCreator
    public JsonSerializableAttractionList(@JsonProperty("attractions") List<JsonAdaptedAttraction> attractions) {
        this.attractions.addAll(attractions);
    }

    /**
     * Converts a given {@code ReadOnlyAttractionList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAttractionList}.
     */
    public JsonSerializableAttractionList(ReadOnlyAttractionList source) {
        attractions.addAll(source.getAttractionList().stream().map(JsonAdaptedAttraction::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this attraction list into the model's {@code AttractionList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AttractionList toModelType() throws IllegalValueException {
        AttractionList attractionList = new AttractionList();
        for (JsonAdaptedAttraction jsonAdaptedAttraction : attractions) {
            Attraction attraction = jsonAdaptedAttraction.toModelType();
            if (attractionList.hasAttraction(attraction)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ATTRACTION);
            }
            attractionList.addAttraction(attraction);
        }
        return attractionList;
    }

}
