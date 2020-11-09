package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Date;

public class RecordTest {
    private static final HashMap<Date, Height> RECORD_HEIGHT = new HashMap<>();
    private static final HashMap<Date, Weight> RECORD_WEIGHT = new HashMap<>();

    @Test
    public void constructor_nullHeightRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Record(null, RECORD_WEIGHT));
    }

    @Test
    public void constructor_nullWeightRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Record(RECORD_HEIGHT, null));
    }

    @Test
    public void equals() {
        Record record1 = new Record();
        Record record2 = new Record();
        assertEquals(record2, record1);

        record1.addHeightRecord(new Height("179"));
        record1.addWeightRecord(new Weight("76.8"));
        assertNotEquals(record2, record1);

        record2.addHeightRecord(new Height("179"));
        record2.addWeightRecord(new Weight("76.8"));
        assertEquals(record1, record2);
    }
}
