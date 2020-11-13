package seedu.address.testutil;

import static seedu.address.testutil.TypicalCalories.INPUT_A;
import static seedu.address.testutil.TypicalCalories.INPUT_B;
import static seedu.address.testutil.TypicalCalories.OUTPUT_A;
import static seedu.address.testutil.TypicalCalories.OUTPUT_B;

import seedu.address.model.calorie.CalorieManager;

public class TypicalCalorieManager {

    public static final CalorieManager CALORIE_MANAGER1 = new CalorieManagerBuilder().addInput(INPUT_A)
            .addInput(INPUT_B).addOutput(OUTPUT_A).addOutput(OUTPUT_B).build();
}
