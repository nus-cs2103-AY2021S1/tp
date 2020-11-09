package seedu.medibook.model.patient;

import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;

import seedu.medibook.model.commonfields.Date;

public class Record {
    private HashMap<Date, Height> heightRecord;
    private HashMap<Date, Weight> weightRecord;

    /**
     * Initializes a {@code} Record object with empty height and weight record.
     */
    public Record() {
        this.heightRecord = new HashMap<>();
        this.weightRecord = new HashMap<>();
    }

    /**
     * Initializes a {@code} Record object with the given height and weight record.
     */
    public Record(HashMap<Date, Height> heightRecord, HashMap<Date, Weight> weightRecord) {
        requireAllNonNull(heightRecord, weightRecord);
        this.heightRecord = heightRecord;
        this.weightRecord = weightRecord;
    }

    public void addHeightRecord(Height height) {
        addHeightRecord(new Date(), height);
    }

    /**
     * Inserts a {@code height} record for the given {@code date}.
     * @param date date of record
     * @param height height of patient
     */
    public void addHeightRecord(Date date, Height height) {
        assert date != null : "Date should not be null!";
        assert height != null : "Height should not be null!";

        heightRecord.put(date, height);
    }

    public void addWeightRecord(Weight weight) {
        addWeightRecord(new Date(), weight);
    }

    /**
     * Inserts a {@code weight} record for the given {@code date}.
     * @param date date of record
     * @param weight weight of patient
     */
    public void addWeightRecord(Date date, Weight weight) {
        assert date != null : "Date should not be null!";
        assert weight != null : "Weight should not be null!";

        weightRecord.put(date, weight);
    }

    public HashMap<Date, Height> getHeightRecord() {
        assert heightRecord != null : "Height record should not be null";
        return heightRecord;
    }

    public HashMap<Date, Weight> getWeightRecord() {
        assert weightRecord != null : "Weight record should not be null";
        return weightRecord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return otherRecord.getHeightRecord().equals(getHeightRecord())
                && otherRecord.getWeightRecord().equals(getWeightRecord());
    }
}
