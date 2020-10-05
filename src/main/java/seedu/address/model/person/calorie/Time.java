package seedu.address.model.person.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Calorie Count should only contain numeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[0-9][0-9]:[0-9][0-9]";

    public final String TIME;

    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTimeCount(time), MESSAGE_CONSTRAINTS);
        TIME = time;
    }

    public static boolean isValidTimeCount(String test) {

        if(test.matches(VALIDATION_REGEX)){
            int hour = Integer.parseInt(test.substring(0,1));
            int min = Integer.parseInt(test.substring(3,4));
            return hour <= 23 && min < 59;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return TIME;
    }


}
