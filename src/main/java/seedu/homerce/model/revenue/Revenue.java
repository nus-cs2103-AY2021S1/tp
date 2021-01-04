package seedu.homerce.model.revenue;

import java.util.Objects;

import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;

public class Revenue {

    //Identity field
    private Service service;
    private Date date;

    //Data field
    private Amount value;

    /**
     * Every field must be present and not null.
     */
    public Revenue(Service service, Date date) {
        this.service = service;
        this.date = date;
        this.value = service.getAmount();
    }

    public Service getService() {
        return service;
    }

    public Date getDate() {
        return date;
    }

    public Amount getValue() {
        return value;
    }

    /**
     * Returns true if both revenues have the same identity and data fields.
     * This defines a stronger notion of equality between two revenues.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        Revenue otherRevenue = (Revenue) other;
        return otherRevenue.getService().equals(getService())
            && otherRevenue.getDate().equals(getDate())
            && otherRevenue.getValue().equals(getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, date, value);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Revenue: ")
            .append(getService())
            .append(" Date: ")
            .append(getDate())
            .append(" Value: ")
            .append(getValue());
        return builder.toString();
    }
}
