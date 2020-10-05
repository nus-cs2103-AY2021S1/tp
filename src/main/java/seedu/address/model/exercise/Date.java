package seedu.address.model.exercise;

public class Date {
    public final String value;

    public Date(String val) {
        this.value = val;
    }

    public static boolean isValidDate(String val) {
        return true;
    }
}
