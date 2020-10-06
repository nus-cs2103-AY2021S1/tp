package seedu.address.testutil;

import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.Admin;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_SCHOOL = "NUS High School";
    public static final String DEFAULT_YEAR = "Year 4";

    private Name name;
    private Phone phone;
    private School school;
    private Year year;
    private Admin admin;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        school = new School(DEFAULT_SCHOOL);
        year = new Year(DEFAULT_YEAR);
        admin = new Admin();
        //have to add the admin object here
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        school = studentToCopy.getSchool();
        year = studentToCopy.getYear();
        admin = studentToCopy.getAdmin();
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
     * Sets the {@code ClassTime} of the {@code Student} that we are building.
     */
    //    public StudentBuilder withClassTime(String classTime) {
    //        this.classTime = new ClassTime(classTime);
    //        return this;
    //    }
    //
    //    /**
    //     * Sets the {@code AdditionalDetails} of the {@code Student} that we are building.
    //     */
    //    public StudentBuilder withAdditionalDetails(String additionalDetails) {
    //        this.additionalDetails = new AdditionalDetails(additionalDetails);
    //        return this;
    //    }
    //
    //    /**
    //     * Sets the {@code Subject} of the {@code Student} that we are building.
    //     */
    //    public StudentBuilder withSubject(String subject) {
    //        this.subject = new Subject(subject);
    //        return this;
    //    }

    public Student build() {
        return new Student(name, phone, school, year, admin);
    }

}
