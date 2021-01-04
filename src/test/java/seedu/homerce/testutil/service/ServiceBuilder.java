package seedu.homerce.testutil.service;

import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;

/**
 * A utility class to help with building Service objects.
 */
public class ServiceBuilder {
    public static final String DEFAULT_TITLE = "Hair Treatment";
    public static final double DEFAULT_DURATION = 1.5;
    public static final double DEFAULT_AMOUNT = 25.5;
    public static final String DEFAULT_SERVICE_CODE = "SC000";

    private Title title;
    private Duration duration;
    private Amount amount;
    private ServiceCode serviceCode;

    /**
     * Creates a {@code ServiceBuilder} with the default details.
     */
    public ServiceBuilder() {
        title = new Title(DEFAULT_TITLE);
        duration = new Duration(DEFAULT_DURATION);
        amount = new Amount(DEFAULT_AMOUNT);
        serviceCode = new ServiceCode(DEFAULT_SERVICE_CODE);
    }

    /**
     * Initializes the ServiceBuilder with the data of {@code serviceToCopy}.
     */
    public ServiceBuilder(Service serviceToCopy) {
        title = serviceToCopy.getTitle();
        duration = serviceToCopy.getDuration();
        amount = serviceToCopy.getAmount();
        serviceCode = serviceToCopy.getServiceCode();
    }

    /**
     * Sets the {@code Title} of the {@code Service} that we are building.
     */
    public ServiceBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Service} that we are building.
     */
    public ServiceBuilder withServiceCode(String serviceCode) {
        this.serviceCode = new ServiceCode(serviceCode);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Service} that we are building.
     */
    public ServiceBuilder withDuration(double duration) {
        this.duration = new Duration(duration);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Service} that we are building.
     */
    public ServiceBuilder withAmount(double amount) {
        this.amount = new Amount(amount);
        return this;
    }

    public Service build() {
        return new Service(title, duration, amount).addServiceCode(serviceCode.value);
    }
}
