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

    @JsonCreator
    public JsonAdaptedLeave(
            @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public JsonAdaptedLeave(Leave leave) {
        this.startDate = DateUtil.dateToString(leave.getStartDate());
        this.endDate = DateUtil.dateToString(leave.getEndDate());
    }

    public Leave toModelType() {
        return new Leave(startDate, endDate);
    }
}
