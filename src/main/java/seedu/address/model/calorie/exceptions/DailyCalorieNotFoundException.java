package seedu.address.model.calorie.exceptions;

public class DailyCalorieNotFoundException extends RuntimeException {
    public DailyCalorieNotFoundException() {
        super("Today's calorie log has not been created!");
    }
}
