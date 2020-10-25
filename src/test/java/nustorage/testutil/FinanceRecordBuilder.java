package nustorage.testutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import nustorage.logic.parser.ParserUtil;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.FinanceRecord;

/**
 * A utility class to help with building Person objects.
 */
public class FinanceRecordBuilder {

    public static final double DEFAULT_AMOUNT = 250000;
    public static final String DEFAULT_DATE = "2020-04-20";
    public static final String DEFAULT_TIME = "13:00";

    private double amount;
    private LocalDateTime datetime;

    /**
     * Creates a {@code FinanceRecordBuilder} with the default details.
     */
    public FinanceRecordBuilder() {
        amount = DEFAULT_AMOUNT;
        datetime = LocalDateTime.of(LocalDate.parse(DEFAULT_DATE), LocalTime.parse(DEFAULT_TIME));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public FinanceRecordBuilder(FinanceRecord recordToCopy) {
        amount = recordToCopy.getAmount();
        datetime = recordToCopy.getDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public FinanceRecordBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public FinanceRecordBuilder withDatetime(String datetime) {
        try {
            this.datetime = ParserUtil.parseDatetime(datetime);
        } catch (ParseException e) {
            return this;
        }
        return this;
    }

    public FinanceRecord build() {
        return new FinanceRecord(amount, datetime);
    }

}
