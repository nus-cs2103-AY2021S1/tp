package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.calorie.CalorieManager;
import seedu.address.testutil.TypicalCalorieManager;

public class JsonAdaptedCalorieManagerTest {
    private static final CalorieManager VALID_CALORIE_MANAGER = TypicalCalorieManager.CALORIE_MANAGER1;
    private static final List<JsonAdaptedInput> VALID_INPUT_LIST = VALID_CALORIE_MANAGER.getCalorieInputList()
            .stream().map(JsonAdaptedInput::new).collect(Collectors.toList());

    private static final List<JsonAdaptedOutput> VALID_OUTPUT_LIST = VALID_CALORIE_MANAGER.getCalorieOutputList()
            .stream().map(JsonAdaptedOutput::new).collect(Collectors.toList());

    @Test
    public void toModelType_validCalorieManagerDetails_returnsDay() throws Exception {
        JsonAdaptedCalorieManager calorieManager = new JsonAdaptedCalorieManager(VALID_CALORIE_MANAGER);
        assertEquals(VALID_CALORIE_MANAGER, calorieManager.toModelType());
    }

    @Test
    public void toModelType_nullCalorieManager_throwsIllegalValueException() throws Exception {
        JsonAdaptedCalorieManager calorieManager =
                new JsonAdaptedCalorieManager(VALID_INPUT_LIST, VALID_OUTPUT_LIST);
        assertEquals(VALID_CALORIE_MANAGER, calorieManager.toModelType());
    }

}
