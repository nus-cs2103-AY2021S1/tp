package com.eva.storage;

import com.eva.commons.util.DateUtil;
import com.eva.model.person.staff.leave.Leave;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson-friendly version of {@link Leave}.
 */
public class JsonAdaptedLeave {
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a {@code JsonAdaptedLeave} with the given {@code startDate} as {@code endDate}.
     * @param startDate the start date string of the leave.
     * @param endDate the end date string og the leave.
     */
    @JsonCreator
    public JsonAdaptedLeave(
            @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts a given {@code Leave} into this class for Jackson use.
     */
    public JsonAdaptedLeave(Leave leave) {
        this.startDate = DateUtil.dateToString(leave.getStartDate());
        this.endDate = DateUtil.dateToString(leave.getEndDate());
    }

    /**
     * Converts this Jackson-friendly adapted leave object into the model's {@code Leave} object.
     * @return The converted {@code Leave}.
     */
    public Leave toModelType() {
        return new Leave(startDate, endDate);
    }
}
