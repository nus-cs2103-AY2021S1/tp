package seedu.homerce.storage.revenue;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Title;

public class JsonAdaptedRevenue {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Revenue's %s field is missing!";

    private final String title;
    private final BigDecimal price;
    private final Double duration;
    private final String serviceCode;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedRevenue} with the given Revenue details.
     */
    @JsonCreator
    public JsonAdaptedRevenue(@JsonProperty("title") String title, @JsonProperty("price") Double price,
                              @JsonProperty("duration") Double duration,
                              @JsonProperty("serviceCode") String serviceCode,
                              @JsonProperty("date") String date) {
        this.title = title;
        this.price = new BigDecimal(price);
        this.duration = duration;
        this.serviceCode = serviceCode;
        this.date = date;
    }

    /**
     * Converts a given {@code Revenue} into this class for Jackson use.
     */
    public JsonAdaptedRevenue(Revenue source) {
        title = source.getService().getTitle().value;
        price = source.getService().getAmount().value;
        duration = source.getService().getDuration().value;
        serviceCode = source.getService().getServiceCode().value;
        date = source.getDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted revenue object into the model's {@code Revenue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted revenue.
     */
    public Revenue toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (duration == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Duration.class.getSimpleName()));
        }
        if (!Duration.isValidDuration(duration)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS);
        }
        final Duration modelDuration = new Duration(duration);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(price.doubleValue())) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(price.doubleValue());

        if (serviceCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ServiceCode.class.getSimpleName()));
        }
        if (!ServiceCode.isValidServiceCode(serviceCode)) {
            throw new IllegalValueException(ServiceCode.MESSAGE_CONSTRAINTS);
        }

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }

        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        Service service = new Service(modelTitle, modelDuration, modelAmount);
        service.addServiceCode(serviceCode);

        return new Revenue(service, modelDate);
    }
}
