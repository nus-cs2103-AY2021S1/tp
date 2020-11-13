package seedu.address.testutil;

import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;


public class CalorieBuilder {

    public static final String DEFAULT_TIME = "1130";
    public static final String DEFAULT_CALORIE_COUNT = "123";
    public static final String DEFAULT_EXERCISE = "running";
    public static final String DEFAULT_FOOD = "laksa";

    public static final boolean INPUT = true;
    public static final boolean OUTPUT = false;

    private Time time;
    private CalorieCount calorieCount;
    private Exercise exercise;
    private Food food;

    /**
     * Creates a {@code CalorieBuilder} with the default details.
     */
    public CalorieBuilder() {

        this.time = new Time(DEFAULT_TIME);
        this.calorieCount = new CalorieCount(DEFAULT_CALORIE_COUNT);
        this.food = new Food(DEFAULT_FOOD);
        this.exercise = new Exercise(DEFAULT_EXERCISE);

    }

    /**
     * Initializes the CalorieBuilder with the data of {@code calorieToCopy}.
     */
    public CalorieBuilder(Calorie calorieToCopy, Boolean type) {
        this.time = new Time(DEFAULT_TIME);
        this.calorieCount = new CalorieCount(DEFAULT_CALORIE_COUNT);
        //INPUT
        if (type) {
            this.food = ((Input) calorieToCopy).getFood();
            this.exercise = null;
        } else {
            //OUTPUT
            this.exercise = ((Output) calorieToCopy).getExercise();
            this.food = null;
        }
    }

    /**
     * Sets the {@code Time} of the {@code Calorie} that we are building.
     */
    public CalorieBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code CalorieCount} of the {@code Calorie} that we are building.
     */
    public CalorieBuilder withCalorieCount(String calorieCount) {
        this.calorieCount = new CalorieCount(calorieCount);
        return this;
    }


    /**
     * Sets the {@code Exericse} of the {@code Calorie} that we are building.
     */
    public CalorieBuilder withExercise(String exercise) {
        this.exercise = new Exercise(exercise);
        return this;
    }

    /**
     * Sets the {@code Exericse} of the {@code Calorie} that we are building.
     */
    public CalorieBuilder withFood(String food) {
        this.food = new Food(food);
        return this;
    }

    public Input buildInput() {
        return new Input(time, food, calorieCount);
    }

    public Output buildOutput() {
        return new Output(time, exercise, calorieCount);
    }
}
