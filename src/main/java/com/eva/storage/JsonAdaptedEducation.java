package com.eva.storage;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.commons.util.DateUtil;
import com.eva.model.person.applicant.application.Education;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class JsonAdaptedEducation {
    private final String startDate;
    private final String endDate;
    private final String schoolName;

    /**
     * Constructs a {@code JsonAdaptedEducation} with the given details.
     */
    @JsonCreator
    public JsonAdaptedEducation(
            @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate,
            @JsonProperty("schoolName") String schoolName
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.schoolName = schoolName;
    }

    /**
     * Converts a given {@code Education} into this class for Jackson use.
     */
    public JsonAdaptedEducation(Education source) {
        startDate = DateUtil.dateToString(source.getStartDate());
        endDate = DateUtil.dateToString(source.getEndDate());
        schoolName = source.getSchoolName();
    }

    /**
     * Converts this Jackson-friendly adapted education object into the model's {@code Education} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Education toModelType() throws IllegalValueException {
        return new Education(startDate, endDate, schoolName);
    }
}
