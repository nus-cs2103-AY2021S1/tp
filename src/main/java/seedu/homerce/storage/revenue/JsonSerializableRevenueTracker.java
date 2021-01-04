package seedu.homerce.storage.revenue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.revenue.Revenue;

/**
 * An Immutable revenueTracker that is serializable to JSON format.
 */
@JsonRootName(value = "revenueTracker")
public class JsonSerializableRevenueTracker {

    private final List<JsonAdaptedRevenue> revenues = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRevenueTracker } with the given services.
     */
    @JsonCreator
    public JsonSerializableRevenueTracker(@JsonProperty("revenues") List<JsonAdaptedRevenue> revenues) {
        this.revenues.addAll(revenues);
    }

    /**
     * Converts a given {@code ReadOnlyRevenueTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRevenueManager}.
     */
    public JsonSerializableRevenueTracker(ReadOnlyRevenueTracker source) {
        revenues.addAll(source.getRevenueList().stream().map(JsonAdaptedRevenue::new).collect(Collectors.toList()));
    }

    /**
     * Converts this revenueTracker into the model's {@code RevenueTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RevenueTracker toModelType() throws IllegalValueException {
        RevenueTracker revenueTracker = new RevenueTracker();
        for (JsonAdaptedRevenue jsonAdaptedRevenue : revenues) {
            Revenue revenue = jsonAdaptedRevenue.toModelType();
            revenueTracker.addRevenue(revenue);
        }
        return revenueTracker;
    }

}
