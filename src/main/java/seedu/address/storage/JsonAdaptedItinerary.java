package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryDate;

/**
 * Jackson-friendly version of {@link Itinerary}.
 */
class JsonAdaptedItinerary {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary's %s field is missing!";

    private final String name;
    private final String description;
    private final String startDate;
    private final String endDate;
    private final String budget;
    private final List<JsonAdaptedDay> days = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedItinerary} with the given itinerary details.
     */
    @JsonCreator
    public JsonAdaptedItinerary(@JsonProperty("name") String name,
                                @JsonProperty("description") String description,
                                @JsonProperty("startDate") String startDate,
                                @JsonProperty("endDate") String endDate,
                                @JsonProperty("budget") String budget,
                                @JsonProperty("days") List<JsonAdaptedDay> days) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        if (days != null) {
            this.days.addAll(days);
        }
    }

    /**
     * Converts a given {@code Itinerary} into this class for Jackson use.
     */
    public JsonAdaptedItinerary(Itinerary source) {
        name = source.getName().toString();
        description = source.getDescription().value;
        startDate = source.getStartDate().toString();
        endDate = source.getEndDate().toString();
        budget = source.getBudget().value;
        days.addAll(source.getDays().stream()
                .map(JsonAdaptedDay::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted itinerary object into the model's {@code Itinerary} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted itinerary.
     */
    public Itinerary toModelType() throws IllegalValueException {
        final Name modelName;
        final Description modelDescription;
        final ItineraryDate modelStartDate;
        final ItineraryDate modelEndDate;
        final Budget modelBudget;
        final List<Day> modelDays = new ArrayList<>();

        for (JsonAdaptedDay day : days) {
            modelDays.add(day.toModelType());
        }

        // Name is not optional
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        } else if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        } else {
            modelName = new Name(name);
        }

        // Description is optional
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        } else if (description.equals("")) {
            modelDescription = new Description();
        } else if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        // Start date is not optional
        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItineraryDate.class.getSimpleName()));
        } else if (!ItineraryDate.isValidDate(startDate)) {
            throw new IllegalValueException(ItineraryDate.MESSAGE_CONSTRAINTS);
        } else {
            modelStartDate = new ItineraryDate(startDate);
        }

        // End date is not optional
        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItineraryDate.class.getSimpleName()));
        } else if (!ItineraryDate.isValidDate(endDate)) {
            throw new IllegalValueException(ItineraryDate.MESSAGE_CONSTRAINTS);
        } else {
            modelEndDate = new ItineraryDate(endDate);
        }

        // Budget is optional
        if (budget == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Budget.class.getSimpleName()));
        } else if (budget.equals("")) {
            modelBudget = new Budget();
        } else if (!Budget.isValidBudget(budget)) {
            throw new IllegalValueException(Budget.MESSAGE_CONSTRAINTS);
        } else {
            modelBudget = new Budget(budget);
        }

        return new Itinerary(modelName, modelDescription, modelStartDate, modelEndDate, modelBudget, modelDays);
    }

}
