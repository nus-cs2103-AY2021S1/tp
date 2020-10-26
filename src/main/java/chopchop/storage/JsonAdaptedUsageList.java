package chopchop.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import chopchop.commons.exceptions.IllegalValueException;

public class JsonAdaptedUsageList {
    private final List<String> usages;

    /**
     * Constructs a {@code JsonAdaptedUsageSet} with given list of localdatetime strings.
     */
    @JsonCreator
    public JsonAdaptedUsageList(@JsonProperty("usages") List<String> usages) {
        this.usages = usages;
    }

    /**
     * Converts JsonAdaptedUsages to Usages itself.
     */
    public List<LocalDateTime> toModelType() throws IllegalValueException {
        List<LocalDateTime> newUsages = new ArrayList<>();
        try {
            for (String usage: this.usages) {
                newUsages.add(LocalDateTime.parse(usage));
            }
        } catch (Exception e) {
            throw new IllegalValueException("LocalDateTime cannot be parsed.");
        }

        return newUsages;
    }

}
