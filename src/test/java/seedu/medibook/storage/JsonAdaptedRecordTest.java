package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.storage.JsonAdaptedRecord.MISSING_HEIGHT_RECORD_MESSAGE;
import static seedu.medibook.storage.JsonAdaptedRecord.MISSING_WEIGHT_RECORD_MESSAGE;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Record;
import seedu.medibook.model.patient.Weight;

public class JsonAdaptedRecordTest {
    private static final String VALID_DATE = "09-07-1990";
    private static final String VALID_HEIGHT = "167";
    private static final String VALID_WEIGHT = "67.7";
    private static final String INVALID_DATE = "32-07-1990";
    private static final String INVALID_HEIGHT = "i67";
    private static final String INVALID_WEIGHT = "6o.7";

    private static final HashMap<String, String> VALID_RECORD_HEIGHT = new HashMap<>();
    private static final HashMap<String, String> VALID_RECORD_WEIGHT = new HashMap<>();
    private static final HashMap<String, String> INVALID_RECORD_HEIGHT = new HashMap<>();
    private static final HashMap<String, String> INVALID_RECORD_WEIGHT = new HashMap<>();

    static {
        INVALID_RECORD_HEIGHT.put(INVALID_DATE, INVALID_HEIGHT);
        INVALID_RECORD_WEIGHT.put(VALID_DATE, INVALID_WEIGHT);
        VALID_RECORD_HEIGHT.put(VALID_DATE, VALID_HEIGHT);
        VALID_RECORD_WEIGHT.put(VALID_DATE, VALID_WEIGHT);
    }

    @Test
    public void toModelType_validRecords_returnsRecord() throws Exception {
        JsonAdaptedRecord record = new JsonAdaptedRecord(VALID_RECORD_HEIGHT, VALID_RECORD_WEIGHT);
        HashMap<Date, Height> heightRecord = new HashMap<>();
        HashMap<Date, Weight> weightRecord = new HashMap<>();
        heightRecord.put(new Date(VALID_DATE, true), new Height(VALID_HEIGHT));
        weightRecord.put(new Date(VALID_DATE, true), new Weight(VALID_WEIGHT));
        Record expected = new Record(heightRecord, weightRecord);
        assertEquals(expected, record.toModelType());
    }

    @Test
    public void toModelType_invalidHeightRecord_throwsIllegalArgumentException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(INVALID_RECORD_HEIGHT, VALID_RECORD_WEIGHT);
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, record::toModelType);
    }

    @Test
    public void toModelType_nullHeightRecord_throwsIllegalValueException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(null, VALID_RECORD_WEIGHT);
        assertThrows(IllegalValueException.class, MISSING_HEIGHT_RECORD_MESSAGE, record::toModelType);
    }

    @Test
    public void toModelType_invalidWeightRecord_throwsIllegalArgumentException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(VALID_RECORD_HEIGHT, INVALID_RECORD_WEIGHT);
        assertThrows(IllegalArgumentException.class, Weight.MESSAGE_CONSTRAINTS, record::toModelType);
    }

    @Test
    public void toModelType_nullWeightRecord_throwsIllegalValueException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(VALID_RECORD_HEIGHT, null);
        assertThrows(IllegalValueException.class, MISSING_WEIGHT_RECORD_MESSAGE, record::toModelType);
    }
}
