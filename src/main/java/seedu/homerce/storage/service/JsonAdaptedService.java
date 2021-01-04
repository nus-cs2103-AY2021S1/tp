package seedu.homerce.storage.service;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;

/**
 * Jackson-friendly version of {@link Service}.
 */
public class JsonAdaptedService {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Service's %s field is missing!";

    private final String title;
    private final BigDecimal price;
    private final Double duration;
    private final String serviceCode;

    /**
     * Constructs a {@code JsonAdaptedService} with the given Service details.
     */
    @JsonCreator
    public JsonAdaptedService(@JsonProperty("title") String title, @JsonProperty("price") Double price,
                              @JsonProperty("duration") Double duration,
                              @JsonProperty("serviceCode") String serviceCode) {
        this.title = title;
        this.price = new BigDecimal(price);
        this.duration = duration;
        this.serviceCode = serviceCode;
    }

    /**
     * Converts a given {@code Service} into this class for Jackson use.
     */
    public JsonAdaptedService(Service source) {
        title = source.getTitle().value;
        price = source.getAmount().value;
        duration = source.getDuration().value;
        serviceCode = source.getServiceCode().value;

    }

    /**
     * Converts this Jackson-friendly adapted service object into the model's {@code Service} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted service.
     */
    public Service toModelType() throws IllegalValueException {
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

        Service service = new Service(modelTitle, modelDuration, modelAmount);
        service.addServiceCode(serviceCode);

        return service;
    }
}
