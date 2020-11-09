package seedu.medibook.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Record;
import seedu.medibook.model.patient.Weight;

/**
 * Jackson-friendly version of {@link Record}.
 */
class JsonAdaptedRecord {

    public static final String MISSING_HEIGHT_RECORD_MESSAGE = "Record's height record field is missing!";
    public static final String MISSING_WEIGHT_RECORD_MESSAGE = "Record's Weight record field is missing!";

    private final HashMap<String, String> heightRecord;
    private final HashMap<String, String> weightRecord;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given records.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("heightRecord") HashMap<String, String> heightRecord,
                             @JsonProperty("weightRecord") HashMap<String, String> weightRecord) {
        this.heightRecord = heightRecord;
        this.weightRecord = weightRecord;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        heightRecord = toStringMap(source.getHeightRecord());
        weightRecord = toStringMap(source.getWeightRecord());
    }

    @JsonGetter
    public Map<String, String> getHeightRecord() {
        return heightRecord;
    }

    @JsonSetter
    public void setHeightRecord(String date, String height) {
        heightRecord.put(date, height);
    }

    @JsonGetter
    public Map<String, String> getWeightRecord() {
        return weightRecord;
    }

    @JsonSetter
    public void setWeightRecord(String date, String weight) {
        weightRecord.put(date, weight);
    }

    /**
     * Converts this Jackson-friendly adapted Record object into the model's {@code Record} object.
     */
    public Record toModelType() throws IllegalValueException {
        if (this.heightRecord == null) {
            throw new IllegalValueException(MISSING_HEIGHT_RECORD_MESSAGE);
        }

        if (this.weightRecord == null) {
            throw new IllegalValueException(MISSING_WEIGHT_RECORD_MESSAGE);
        }

        HashMap<Date, Height> heightRecord = toDateHeightMap(this.heightRecord);
        HashMap<Date, Weight> weightRecord = toDateWeightMap(this.weightRecord);
        return new Record(heightRecord, weightRecord);
    }

    private HashMap<String, String> toStringMap(HashMap<Date, ?> record) {
        HashMap<String, String> result = new HashMap<>();
        record.keySet().forEach(key -> {
            String value = record.get(key).toString();
            result.put(key.getInputString(), value);
        });

        return result;
    }

    private HashMap<Date, Height> toDateHeightMap(HashMap<String, String> stringMap) {
        HashMap<Date, Height> result = new HashMap<>();
        stringMap.keySet().forEach(key -> {
            Date date = new Date(key, true);
            Height height = new Height(stringMap.get(key));
            result.put(date, height);
        });

        return result;
    }

    private HashMap<Date, Weight> toDateWeightMap(HashMap<String, String> stringMap) {
        HashMap<Date, Weight> result = new HashMap<>();
        stringMap.keySet().forEach(key -> {
            Date date = new Date(key, true);
            Weight weight = new Weight(stringMap.get(key));
            result.put(date, weight);
        });

        return result;
    }

}
