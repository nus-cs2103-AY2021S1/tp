package seedu.address.model.student.admin;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents all administrative details of a Student in Reeve.
 * Consists of the lesson venue, time, monthly fees, last paid date and additional notes.
 * Guarantees: details are present and non-null, all fields are validated and immutable.
 */
public class Admin {

    /*
     * A placeholder Admin object until it has been fully integrated.
     * To remove when no longer needed.
     */
    private static Admin placeholder;
    static {
        ClassVenue venue = new ClassVenue("Placeholder venue");
        ClassTime time = new ClassTime("1 0000-2359");
        Fee fee = new Fee("123");
        PaymentDate date = new PaymentDate("1/1/01");
        placeholder = new Admin(venue, time, fee, date, new HashSet<>());
    }

    private final ClassVenue classVenue;
    private final ClassTime classTime;
    private final Fee fee;
    private final PaymentDate paymentDate;
    private final Set<AdditionalDetail> details = new HashSet<>();

    /**
     * venue, time, fee, date and details are not null.
     */
    public Admin(ClassVenue venue, ClassTime time, Fee fee, PaymentDate date,
                 Set<AdditionalDetail> details) {
        requireAllNonNull(venue, time, fee, date, details);
        this.classVenue = venue;
        this.classTime = time;
        this.fee = fee;
        this.paymentDate = date;
        this.details.addAll(details);
    }

    public ClassVenue getClassVenue() {
        return classVenue;
    }

    public ClassTime getClassTime() {
        return classTime;
    }

    public Fee getFee() {
        return fee;
    }

    public PaymentDate getPaymentDate() {
        return paymentDate;
    }

    public Set<AdditionalDetail> getDetails() {
        return details;
    }

    public static Admin getPlaceholder() {
        return placeholder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classVenue, classTime, fee, paymentDate, details);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Admin)) {
            return false;
        }

        Admin other = (Admin) obj;
        return other.getClassVenue().equals(getClassVenue())
                && other.getClassTime().equals(getClassTime())
                && other.getFee().equals(getFee())
                && other.getPaymentDate().equals(getPaymentDate())
                && other.getDetails().equals(getDetails());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Class Venue: ")
                .append(classVenue)
                .append(" Lesson Times: ")
                .append(classTime)
                .append(" Fee: ")
                .append(fee)
                .append(" Last Paid: ")
                .append(paymentDate)
                .append(" Notes: ");
        details.forEach(builder::append);
        return builder.toString();
    }
}
