package seedu.address.model.exercise;

public class Calories {
    public final String value;

    public Calories(String val) {
        this.value = val;
    }

    public static boolean isValidCalories(String val) {
        return true;
    }
}