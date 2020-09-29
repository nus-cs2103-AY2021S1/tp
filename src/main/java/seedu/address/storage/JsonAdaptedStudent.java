package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.*;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String school;
    private final String year;
    private final String classVenue;
    private final String classTime;
    private final String additionalDetails;
    private final String meetingLink;
    private final String subject;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("school") String school, @JsonProperty("year") String year,
                              @JsonProperty("classVenue") String classVenue,
                              @JsonProperty("classTime") String classTime,
                              @JsonProperty("additionalDetails") String additionalDetails,
                              @JsonProperty("meetingLink") String meetingLink,
                              @JsonProperty("subject") String subject,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.year = year;
        this.classVenue = classVenue;
        this.classTime = classTime;
        this.additionalDetails = additionalDetails;
        this.meetingLink = meetingLink;
        this.subject = subject;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        school = source.getSchool().school;
        year = source.getYear().year;
        classVenue = source.getClassVenue().value;
        classTime = source.getClassTime().classTime;
        additionalDetails = source.getAdditionalDetails().details;
        meetingLink = source.getMeetingLink().value;
        subject = source.getSubject().subject;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (meetingLink == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingLink.class.getSimpleName()));
        }
        if (!MeetingLink.isValidEmail(meetingLink)) {
            throw new IllegalValueException(MeetingLink.MESSAGE_CONSTRAINTS);
        }
        final MeetingLink modelMeetingLink = new MeetingLink(meetingLink);

        if (classVenue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassVenue.class.getSimpleName()));
        }
        if (!ClassVenue.isValidClassVenue(classVenue)) {
            throw new IllegalValueException(ClassVenue.MESSAGE_CONSTRAINTS);
        }
        final ClassVenue modelClassVenue = new ClassVenue(classVenue);

        if (school == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    School.class.getSimpleName()));
        }
        final School modelSchool = new School(school);

        if (year == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Year.class.getSimpleName()));
        }
        final Year modelYear = new Year(year);

        if (classTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassTime.class.getSimpleName()));
        }
        final ClassTime modelClassTime = new ClassTime(classTime);

        if (additionalDetails == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AdditionalDetails.class.getSimpleName()));
        }
        final AdditionalDetails modelAdditionalDetails = new AdditionalDetails(additionalDetails);

        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Subject.class.getSimpleName()));
        }
        final Subject modelSubject = new Subject(subject);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Student(modelName, modelPhone, modelSchool, modelYear,
                modelClassVenue, modelClassTime, modelAdditionalDetails,
                modelMeetingLink, modelSubject, modelTags);
    }

}
