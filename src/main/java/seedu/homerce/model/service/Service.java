package seedu.homerce.model.service;

import java.util.Objects;

import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;
import seedu.homerce.model.util.uniquelist.UniqueListItem;

public class Service implements UniqueListItem {

    /**
     * The name of the service
     */
    private Title title;

    /**
     * Amount of money in dollars that this Service brings in
     */
    private Amount price;

    /**
     * Unique identification number for each Service
     */
    private ServiceCode serviceCode;

    /**
     * How long the service takes
     */
    private Duration duration;

    /**
     * Constructor for Service.
     *
     * @param title The title of the service.
     * @param duration The duration of the service.
     * @param price The price of the service.
     */
    public Service(Title title, Duration duration, Amount price) {
        this.title = title;
        this.duration = duration;
        this.price = price;
    }

    public Title getTitle() {
        return title;
    }

    public Amount getAmount() {
        return price;
    }

    public Duration getDuration() {
        return duration;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

    /**
     * Returns true if both services have the same ServiceCode.
     * Used for comparison of Service objects in a HashSet when generating a new ServiceCode.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Service)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        Service otherService = (Service) other;
        return otherService.getServiceCode().equals(getServiceCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceCode.value);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Duration: ")
                .append(getDuration())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Service Code: ")
                .append(getServiceCode().toString());
        return builder.toString();
    }

    public boolean isSameTitle(Service other) {
        return this.title.equals(other.title);
    }

    /**
     * Checks if both services being compared have the same Title.
     *
     * @param other the second service being compared.
     * @return true if both services have the same Title
     */
    @Override
    public boolean isSame(UniqueListItem other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Service)) {
            return false;
        }

        Service otherService = (Service) other;
        return title.equals(otherService.title);
    }

    /**
     * Sets a unique ServiceCode for the Service.
     *
     * @param inputCode is the service code to be set for the Service.
     */
    public Service addServiceCode(String inputCode) {
        this.serviceCode = new ServiceCode(inputCode);
        return this;
    }

    /**
     * Checks if both services being compared have the same attributes.
     *
     * @param other the second service being compared.
     * @return true if both services have the same attributes.
     */
    public boolean isNotEdited(UniqueListItem other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Service)) {
            return false;
        }

        Service otherService = (Service) other;
        return title.equals(otherService.title)
            && duration.equals(otherService.duration)
            && price.equals(otherService.price)
            && serviceCode.equals(otherService.serviceCode);
    }
}
