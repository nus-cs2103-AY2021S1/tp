package seedu.address.testutil;

import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.day.Day;

/**
 * A utility class to help with building MyFitnessBuddy objects.
 * Example usage: <br>
 *     {@code MyFitnessBuddy ab = new MyFitnessBuddyBuilder().withPerson("John", "Doe").build();}
 */
public class MyFitnessBuddyBuilder {

    private MyFitnessBuddy myFitnessBuddy;

    public MyFitnessBuddyBuilder() {
        myFitnessBuddy = new MyFitnessBuddy();
    }

    public MyFitnessBuddyBuilder(MyFitnessBuddy myFitnessBuddy) {
        this.myFitnessBuddy = myFitnessBuddy;
    }

    /**
     * Adds a new {@code Day} to the {@code myFitnessBuddy} that we are building.
     */
    public MyFitnessBuddyBuilder withDay(Day day) {
        myFitnessBuddy.addDay(day);
        return this;
    }

    public MyFitnessBuddy build() {
        return myFitnessBuddy;
    }
}
