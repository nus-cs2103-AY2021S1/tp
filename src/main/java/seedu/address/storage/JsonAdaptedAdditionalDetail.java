package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.admin.AdditionalDetail;

/**
 * Jackson-friendly version of {@link AdditionalDetail}.
 */
class JsonAdaptedAdditionalDetail {

    private final String detail;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAdditionalDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAdditionalDetail(AdditionalDetail source) {
        detail = source.detail;
    }

    @JsonValue
    public String getDetail() {
        return detail;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public AdditionalDetail toModelType() throws IllegalValueException {
        if (!AdditionalDetail.isValidAdditionalDetail(detail)) {
            throw new IllegalValueException(AdditionalDetail.MESSAGE_CONSTRAINTS);
        }
        return new AdditionalDetail(detail);
    }

}
