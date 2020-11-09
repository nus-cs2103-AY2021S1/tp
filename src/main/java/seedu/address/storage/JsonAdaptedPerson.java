package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Person;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String experience;
    private final String dateOfApplication;
    private final String isBlacklisted;
    private final String urlLink;
    private final String salary;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("experience") String experience,
                             @JsonProperty("dateOfApplication") String dateOfApplication,
                             @JsonProperty("isBlacklisted") String isBlacklisted,
                             @JsonProperty("address") String address, @JsonProperty("urlLink") String urlLink,
                             @JsonProperty("salary") String salary,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.experience = experience;
        this.dateOfApplication = dateOfApplication;
        this.isBlacklisted = isBlacklisted;
        this.urlLink = urlLink;
        this.salary = salary;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        experience = source.getExperience().toString();
        dateOfApplication = source.getDateOfApplication().dateString;
        isBlacklisted = source.getBlacklistStatus().toString();
        address = source.getAddressOptional().map(address -> address.value).orElse(null);
        urlLink = source.getUrlLinkOptional().map(link -> link.value).orElse(null);
        salary = source.getSalaryOptional().map(Salary::toString).orElse(null);
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (experience == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Experience.class.getSimpleName()));
        }
        if (!Experience.isValidExperience(experience)) {
            throw new IllegalValueException(Experience.MESSAGE_CONSTRAINTS);
        }
        final Experience modelExperience = new Experience(experience);

        if (dateOfApplication == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Date of Application"));
        }
        if (!Date.isValidDate(dateOfApplication)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDateOfApplication = new Date(dateOfApplication);

        if (isBlacklisted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Blacklist Status"));
        }
        if (!BlacklistStatus.isValidBlacklistStatus(isBlacklisted)) {
            throw new IllegalValueException(BlacklistStatus.MESSAGE_CONSTRAINTS);
        }
        final BlacklistStatus modelBlacklistStatus = new BlacklistStatus(isBlacklisted);

        final Optional<Address> modelAddressOptional;
        if (address == null) {
            modelAddressOptional = Optional.empty();
        } else if (Address.isValidAddress(address)) {
            modelAddressOptional = Optional.of(new Address(address));
        } else { // address not valid
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Optional<UrlLink> modelUrlLinkOptional;
        if (urlLink == null) {
            modelUrlLinkOptional = Optional.empty();
        } else if (UrlLink.isValidLink(urlLink)) {
            modelUrlLinkOptional = Optional.of(new UrlLink(urlLink));
        } else { // urlLink not valid
            throw new IllegalValueException(UrlLink.MESSAGE_CONSTRAINTS);
        }

        final Optional<Salary> modelSalaryOptional;
        if (salary == null) {
            modelSalaryOptional = Optional.empty();
        } else if (Salary.isValidSalary(salary)) {
            modelSalaryOptional = Optional.of(new Salary(salary));
        } else { // salary not valid
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelExperience, modelDateOfApplication,
                modelBlacklistStatus, modelAddressOptional, modelUrlLinkOptional, modelSalaryOptional, modelTags);
    }

}
