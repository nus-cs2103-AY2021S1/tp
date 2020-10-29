package seedu.address.model.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ExerciseBook;
import seedu.address.model.GoalBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.goal.Goal;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    private static Exercise[] getSampleExercises() {
        return new Exercise[]{
            new Exercise(new seedu.address.model.exercise.Name("Push Up"), new Description("Did 52 within 60 seconds"),
                    new Date("01-10-2020"), new Calories("100")),
            new Exercise(new seedu.address.model.exercise.Name("Sit Up"), new Description("Did 50"),
                    new Date("01-10-2020"), new Calories("120")),
            new Exercise(new seedu.address.model.exercise.Name("2 4km"), new Description("11:30"),
                    new Date("04-10-2020"), new Calories("100")),
            new Exercise(new seedu.address.model.exercise.Name("Pull Up"),
                    new Description("20 with Added Weight: 5 kg "), new Date("05-10-2020"), new Calories("100"))
        };
    }

    public static ReadOnlyExerciseBook getSampleExerciseBook() {
        ExerciseBook eb = new ExerciseBook();
        for (Exercise e : getSampleExercises()) {
            eb.addExercise(e);
        }
        return eb;
    }

    public static ReadOnlyGoalBook getSampleGoalBook() {
        GoalBook gb = new GoalBook();
        for (Map.Entry<Date, Goal> entry: gb.getGoalMap().entrySet()) {
            gb.addGoal(entry.getValue());
        }
        return gb;
    }
}
