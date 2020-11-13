package com.eva.storage;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.commons.util.DateUtil;
import com.eva.model.person.applicant.application.Experience;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAdaptedExperience {
    private String startDate;
    private String endDate;
    private String companyName;
    private String position;
    private String description;

    /**
     * Constructs a {@code JsonAdaptedEducation} with the given details.
     */
    @JsonCreator
    public JsonAdaptedExperience(
            @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate,
            @JsonProperty("schoolName") String companyName,
            @JsonProperty("position") String position,
            @JsonProperty("description") String description
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.companyName = companyName;
        this.position = position;
        this.description = description;
    }

    /**
     * Converts a given {@code Experience} into this class for Jackson use.
     */
    public JsonAdaptedExperience(Experience source) {
        startDate = DateUtil.dateToString(source.getStartDate());
        endDate = DateUtil.dateToString(source.getEndDate());
        companyName = source.getCompanyName();
        position = source.getPosition();
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted experience object into the model's {@code Experience} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Experience toModelType() throws IllegalValueException {
        return new Experience(startDate, endDate, companyName, position, description);
    }
}
