package com.eva.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.person.Name;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.applicant.application.Education;
import com.eva.model.person.applicant.application.Experience;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAdaptedApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedExperience> experienceSection = new ArrayList<>();
    private final List<JsonAdaptedEducation> educationSection = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedApplication} with the given details.
     */
    @JsonCreator
    public JsonAdaptedApplication(
            @JsonProperty("name") String name,
            @JsonProperty("experienceSection") List<JsonAdaptedExperience> experienceSection,
            @JsonProperty("educationSection") List<JsonAdaptedEducation> educationSection
    ) {
        this.name = name;
        if (experienceSection != null) {
            this.experienceSection.addAll(experienceSection);
        }
        if (educationSection != null) {
            this.educationSection.addAll(educationSection);
        }
    }

    /**
     * Converts a given {@code Applicant} into this class for Jackson use.
     */
    public JsonAdaptedApplication(Application source) {
        name = source.getApplicantName();
        experienceSection.addAll(source.getExperienceSection().stream()
            .map(JsonAdaptedExperience::new)
            .collect(Collectors.toList()));
        educationSection.addAll(source.getEducationSection().stream()
            .map(JsonAdaptedEducation::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted application object into the model's {@code Application} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application.
     */
    public Application toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final List<Experience> expSection = new ArrayList<>();
        for (JsonAdaptedExperience exp : experienceSection) {
            expSection.add(exp.toModelType());
        }

        final List<Education> eduSection = new ArrayList<>();
        for (JsonAdaptedEducation edu : educationSection) {
            eduSection.add(edu.toModelType());
        }

        return new Application(name, expSection, eduSection);
    }
}
