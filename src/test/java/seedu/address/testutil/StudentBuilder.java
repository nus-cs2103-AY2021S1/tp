package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.admin.AdditionalDetails;
import seedu.address.model.admin.Admin;
import seedu.address.model.admin.ClassTime;
import seedu.address.model.admin.ClassVenue;
import seedu.address.model.student.*;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_SCHOOL = "NUS High School";
    public static final String DEFAULT_YEAR = "Year 4";
    public static final String DEFAULT_FEE = "$50/hr";
    public static final String DEFAULT_LAST_PAID_DATE = "30-09-2020";
    public static final String DEFAULT_CLASS_VENUE = "Blk 60 Kent Ridge Rd #20-223";
    public static final String DEFAULT_CLASS_TIME = "1 1500-1700";//means Monday 3pm - 5pm
    public static final String DEAFULT_ADDITIONAL_DETAILS = "He's lazy";

    private Name name;
    private Phone phone;
    public School school;
    public Year year;
    public Admin admin;

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
