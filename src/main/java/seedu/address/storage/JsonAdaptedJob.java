package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.information.Address;
import seedu.address.model.information.CompanyName;
import seedu.address.model.information.Email;
import seedu.address.model.information.Job;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Vacancy;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Job}.
 */
class JsonAdaptedJob {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Job's %s field is missing!";

    private final String jobTitle;
    private final String company;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String priority;
    private final String vacancy;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedJob(@JsonProperty("jobtitle") String jobTitle, @JsonProperty("company") String company,
                          @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                          @JsonProperty("address") String address,
                          @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                          @JsonProperty("priority") String priority, @JsonProperty("vacancy") String vacancy) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.priority = priority;
        this.vacancy = vacancy;
    }

    /**
     * Converts a given {@code Job} into this class for Jackson use.
     */
    public JsonAdaptedJob(Job source) {
        jobTitle = source.getJobTitle().fullName;
        company = source.getCompanyName().fullCompanyName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        priority = source.getPriority().value;
        vacancy = source.getVacancy().value;
    }

    /**
     * Converts this Jackson-friendly adapted job object into the model's {@code Job} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted job.
     */
    public Job toModelType() throws IllegalValueException {
        final List<Tag> jobTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            jobTags.add(tag.toModelType());
        }

        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "title"));
        }
        if (!Name.isValidName(jobTitle)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelTitle = new Name(jobTitle);

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "company name"));
        }
        if (!Name.isValidName(company)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompany = new CompanyName(company);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(jobTags);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        if (vacancy == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Vacancy.class.getSimpleName()));
        }
        if (!Vacancy.isValidVacancy(vacancy)) {
            throw new IllegalValueException(Vacancy.MESSAGE_CONSTRAINTS);
        }
        final Vacancy modelVacancy = new Vacancy(vacancy);

        return new Job(modelTitle, modelCompany, modelPhone, modelEmail, modelAddress,
            modelTags, modelPriority, modelVacancy);
    }

}
