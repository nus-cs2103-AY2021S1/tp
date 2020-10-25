package chopchop.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import chopchop.commons.exceptions.IllegalValueException;
import chopchop.model.UsageList;
import chopchop.model.usage.RecipeUsage;

public class JsonSerializableRecipeUsageList {
    private final List<JsonAdaptedRecipeUsage> usages;

    /**
     * Constructs a {@code JsonSerializableRecipeBook} with the given recipes.
     */
    @JsonCreator
    public JsonSerializableRecipeUsageList(@JsonProperty("recipeUsages") List<JsonAdaptedRecipeUsage> records) {
        this.usages = new ArrayList<>(records);
    }

    /**
     * Converts a given {@code ReadOnlyRecipeBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRecipeBook}.
     */
    public JsonSerializableRecipeUsageList(UsageList<RecipeUsage> source) {
        this.usages = source.getUsages().stream().map(JsonAdaptedRecipeUsage::new).collect(Collectors.toList());
    }

    /**
     * Converts this recipe book into the model's {@code RecipeBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public List<RecipeUsage> toType() throws IllegalValueException {
        List<RecipeUsage> recordList = new ArrayList<>();
        for (JsonAdaptedRecipeUsage jsonAdaptedRecord : this.usages) {
            RecipeUsage record = jsonAdaptedRecord.toType();
            recordList.add(record);
        }
        return recordList;
    }
}
