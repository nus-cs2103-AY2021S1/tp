package seedu.stock.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.model.ReadOnlySerialNumberSetsBook;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.stock.SerialNumberSet;

/**
 * An immutable SerialNumberSets that is serializable to JSON format.
 */
@JsonRootName(value = "serialNumberSets")
class JsonSerializableSerialNumberSetsBook {

    public static final String MESSAGE_DUPLICATE_SERIAL_NUMBER_SET = "Serial number sets data contains duplicate "
            + "serial number sets.";

    private final List<JsonAdaptedSerialNumberSet> serialNumberSets = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSerialNumberSet} with the given serial number sets.
     */
    @JsonCreator
    public JsonSerializableSerialNumberSetsBook(@JsonProperty("serialNumberSets")
                                                       List<JsonAdaptedSerialNumberSet> serialNumberSets) {
        this.serialNumberSets.addAll(serialNumberSets);
    }

    /**
     * Converts a given {@code ReadOnlyStockBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSerialNumberSetsBook}.
     */
    public JsonSerializableSerialNumberSetsBook(ReadOnlySerialNumberSetsBook source) {
        serialNumberSets.addAll(source.getSerialNumberSetsList().stream()
                .map(JsonAdaptedSerialNumberSet::new).collect(Collectors.toList()));
    }

    /**
     * Converts this SerialNumberSetsBook into the model's {@code SerialNumberSetsBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SerialNumberSetsBook toModelType() throws IllegalValueException {
        SerialNumberSetsBook serialNumberSetsBook = new SerialNumberSetsBook();
        for (JsonAdaptedSerialNumberSet jsonAdaptedSerialNumberSet : serialNumberSets) {
            SerialNumberSet serialNumberSet = jsonAdaptedSerialNumberSet.toModelType();
            if (serialNumberSetsBook.hasSerialNumberSet(serialNumberSet)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SERIAL_NUMBER_SET);
            }
            serialNumberSetsBook.addSerialNumberSet(serialNumberSet);
        }
        return serialNumberSetsBook;
    }

}
