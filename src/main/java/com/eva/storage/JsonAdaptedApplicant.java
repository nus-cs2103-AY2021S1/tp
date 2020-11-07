package com.eva.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.tag.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson-friendly version of {@link Applicant}.
 */
class JsonAdaptedApplicant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Applicant's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String interviewDate;
    private final String status;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedComment> comments = new ArrayList<>();
    private final JsonAdaptedApplication application;

    /**
     * Constructs a {@code JsonAdaptedStaff} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedApplicant(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("interviewDate") String interviewDate,
            @JsonProperty("status") String status,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("comments") List<JsonAdaptedComment> comments,
            @JsonProperty("application") JsonAdaptedApplication application
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.interviewDate = interviewDate;
        this.status = status;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (comments != null) {
            this.comments.addAll(comments);
        }
        this.application = application;
    }

    /**
     * Converts a given {@code Applicant} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        interviewDate = source.getInterviewDate()
                .map(InterviewDate::toString)
                .orElse("");
        status = source.getApplicationStatus().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        comments.addAll(source.getComments().stream()
                .map(JsonAdaptedComment::new)
                .collect(Collectors.toList()));
        application = new JsonAdaptedApplication(source.getApplication());

    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Applicant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Applicant toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Comment> personComments = new ArrayList<>();
        for (JsonAdaptedComment comment : comments) {
            personComments.add(comment.toModelType());
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

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        if (!ApplicationStatus.isValidApplicationStatus(status)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        final ApplicationStatus modelStatus = new ApplicationStatus(status);
        if (interviewDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewDate.class.getSimpleName()));
        }
        if (!interviewDate.isEmpty() && !InterviewDate.isValidInterviewDate(interviewDate)) {
            throw new IllegalValueException((InterviewDate.MESSAGE_CONSTRAINTS));
        }
        if (application == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Application.class.getSimpleName()));
        }
        final InterviewDate modelInterviewDate =
                interviewDate.isEmpty() ? null : new InterviewDate(interviewDate);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Comment> modelComments = new HashSet<>(personComments);

        Applicant newApplicant = new Applicant(modelName, modelPhone, modelEmail, modelAddress,
                modelTags, modelComments, Optional.ofNullable(modelInterviewDate), modelStatus);
        newApplicant.setApplication(application.toModelType());
        return newApplicant;
    }

}
