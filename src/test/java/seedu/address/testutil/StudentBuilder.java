package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
    public static final String DEFAULT_SCHOOL = "alice@gmail.com";
    public static final String DEFAULT_YEAR = "Year 4";
    public static final String DEFAULT_CLASS_VENUE = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CLASS_TIME = "1 1500 - 1700";
    public static final String DEFAULT_ADDITIONAL_DETAILS = "She is quiet";
    public static final String DEFAULT_SUBJECT = "Mathematics";
    public static final String DEFAULT_MEETING_LINK = "www.zoom123.com";

    private Name name;
    private Phone phone;
    public School school;
    public Year year;
    private ClassVenue classVenue;
    private ClassTime classTime;
    private AdditionalDetails additionalDetails;
    private MeetingLink meetingLink;
    private Subject subject;
    private Set<Tag> tags;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        school = new School(DEFAULT_SCHOOL);
        year = new Year(DEFAULT_YEAR);
        classTime = new ClassTime(DEFAULT_CLASS_TIME);
        classVenue = new ClassVenue(DEFAULT_CLASS_VENUE);
        additionalDetails = new AdditionalDetails(DEFAULT_ADDITIONAL_DETAILS);
        meetingLink = new MeetingLink(DEFAULT_MEETING_LINK);
        subject = new Subject(DEFAULT_SUBJECT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        school = studentToCopy.getSchool();
        year = studentToCopy.getYear();
        classTime = studentToCopy.getClassTime();
        classVenue = studentToCopy.getClassVenue();
        additionalDetails = studentToCopy.getAdditionalDetails();
        meetingLink = studentToCopy.getMeetingLink();
        subject = studentToCopy.getSubject();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
     * Sets the {@code meetingLink} of the {@code Student} that we are building.
     */
    public StudentBuilder withMeetingLink(String meetingLink) {
        this.meetingLink = new MeetingLink(meetingLink);
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
     * Sets the {@code classVenue} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassVenue(String classVenue) {
        this.classVenue = new ClassVenue(classVenue);
        return this;
    }

    /**
     * Sets the {@code ClassTime} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassTime(String classTime) {
        this.classTime = new ClassTime(classTime);
        return this;
    }

    /**
     * Sets the {@code AdditionalDetails} of the {@code Student} that we are building.
     */
    public StudentBuilder withAdditionalDetails(String additionalDetails) {
        this.additionalDetails = new AdditionalDetails(additionalDetails);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Student} that we are building.
     */
    public StudentBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }


    public Student build() {
        return new Student(name, phone, school, year,
                classVenue, classTime, additionalDetails,
                meetingLink, subject, tags);
    }

}
