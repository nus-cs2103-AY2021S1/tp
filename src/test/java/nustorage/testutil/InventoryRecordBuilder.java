package nustorage.testutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import nustorage.logic.parser.ParserUtil;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.InventoryRecord;

public class InventoryRecordBuilder {

    public static final String DEFAULT_NAME = "MacBook Pro";
    public static final String DEFAULT_DATE = "2020-04-20";
    public static final String DEFAULT_TIME = "13:00";

    private String itemName;
    private LocalDateTime dateTime;

    /**
     * Creates a {@code InventoryRecordBuilder} with the default details.
     */
    public InventoryRecordBuilder() {
        itemName = DEFAULT_NAME;
        dateTime = LocalDateTime.of(LocalDate.parse(DEFAULT_DATE), LocalTime.parse(DEFAULT_TIME));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public InventoryRecordBuilder(InventoryRecord recordToCopy) {
        itemName = recordToCopy.getItemName();
        dateTime = recordToCopy.getDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public InventoryRecordBuilder withName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public InventoryRecordBuilder withDatetime(String dateTime) {
        try {
            this.dateTime = ParserUtil.parseDatetime(dateTime);
        } catch (ParseException e) {
            return this;
        }
        return this;
    }

    public InventoryRecord build() {
        return new InventoryRecord(itemName, 0, 0.0 , dateTime);
    }
}
