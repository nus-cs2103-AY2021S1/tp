package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_SCHOOL = "NUS High School";
    public static final String DEFAULT_YEAR = "4";
    public static final String DEFAULT_CLASS_VENUE = "Placeholder venue";
    public static final String DEFAULT_CLASS_TIME = "1 0000-2359";
    public static final String DEFAULT_FEE = "123";
    public static final String DEFAULT_PAYMENT_DATE = "1/1/01";

    // Identity fields
    private Name name;
    private Phone phone;
    private School school;
    private Year year;

    // Admin details
    private ClassVenue venue;
    private ClassTime time;
    private Fee fee;
    private PaymentDate paymentDate;
    private Set<AdditionalDetail> details;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        school = new School(DEFAULT_SCHOOL);
        year = new Year(DEFAULT_YEAR);

        venue = new ClassVenue(DEFAULT_CLASS_VENUE);
        time = new ClassTime(DEFAULT_CLASS_TIME);
        fee = new Fee(DEFAULT_FEE);
        paymentDate = new PaymentDate(DEFAULT_PAYMENT_DATE);
        details = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        school = studentToCopy.getSchool();
        year = studentToCopy.getYear();

        Admin studentAdmin = studentToCopy.getAdmin();
        venue = studentAdmin.getClassVenue();
        time = studentAdmin.getClassTime();
        fee = studentAdmin.getFee();
        paymentDate = studentAdmin.getPaymentDate();
        details = studentAdmin.getDetails();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code School} of the {@code Student} that we are building.
     */
    public StudentBuilder withSchool(String school) {
        this.school = new School(school);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code Student} that we are building.
     */
    public StudentBuilder withYear(String year) {
        this.year = new Year(year);
        return this;
    }

    /**
     * Sets the {@code ClassVenue} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassVenue(String venue) {
        this.venue = new ClassVenue(venue);
        return this;
    }

    /**
     * Sets the {@code ClassTime} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassTime(String time) {
        this.time = new ClassTime(time);
        return this;
    }

    /**
     * Sets the {@code Fee} of the {@code Student} that we are building.
     */
    public StudentBuilder withFee(String fee) {
        this.fee = new Fee(fee);
        return this;
    }

    /**
     * Sets the {@code PaymentDate} of the {@code Student} that we are building.
     */
    public StudentBuilder withPaymentDate(String date) {
        this.paymentDate = new PaymentDate(date);
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code Student} that we are building.
     */
    public StudentBuilder withDetails(String... details) {
        this.details = SampleDataUtil.getDetailSet(details);
        return this;
    }

    /**
     * Builds a {@code Student} based on the given information.
     */
    public Student build() {
        return new Student(name, phone, school, year,
                new Admin(venue, time, fee, paymentDate, details));
    }

}
