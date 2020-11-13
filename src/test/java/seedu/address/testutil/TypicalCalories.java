package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;

public class TypicalCalories {

    public static final Input INPUT_A = new CalorieBuilder().withTime("1130").withFood("laksa").withCalorieCount("100")
            .buildInput();
    public static final Input INPUT_B = new CalorieBuilder().withTime("1230").withFood("rice").withCalorieCount("201")
            .buildInput();
    public static final Input INPUT_C = new CalorieBuilder().withTime("1330").withFood("noodle").withCalorieCount("302")
            .buildInput();
    public static final Input INPUT_D = new CalorieBuilder().withTime("0030").withFood("burger").withCalorieCount("403")
            .buildInput();
    public static final Input INPUT_E = new CalorieBuilder().withTime("0930").withFood("beer").withCalorieCount("555")
            .buildInput();

    public static final Output OUTPUT_A = new CalorieBuilder().withTime("1130")
                                              .withExercise("running").withCalorieCount("111").buildOutput();
    public static final Output OUTPUT_B = new CalorieBuilder().withTime("1230")
                                              .withExercise("swimming").withCalorieCount("222").buildOutput();
    public static final Output OUTPUT_C = new CalorieBuilder().withTime("1330")
                                              .withExercise("dancing").withCalorieCount("333").buildOutput();
    public static final Output OUTPUT_D = new CalorieBuilder().withTime("0030")
                                              .withExercise("jumping").withCalorieCount("444").buildOutput();
    public static final Output OUTPUT_E = new CalorieBuilder().withTime("0930")
                                              .withExercise("beer").withCalorieCount("555").buildOutput();

    public static List<Input> getTypicalInputList() {
        return new ArrayList<>(Arrays.asList(INPUT_A, INPUT_B, INPUT_C));
    }

    public static List<Output> getTypicalOutputList() {
        return new ArrayList<>(Arrays.asList(OUTPUT_A, OUTPUT_B, OUTPUT_C));
    }
}
