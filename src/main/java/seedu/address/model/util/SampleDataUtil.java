package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.log.Comment;
import seedu.address.model.log.Log;
import seedu.address.model.log.Rep;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Log[] getSamplePersons() {
        return new Log[] {
            new Log(new Exercise(new Name("Sit up"), 30),
                    new Rep("30"), new Comment("My abs hurt :(")),
            new Log(new Exercise(new Name("Pull ups"), 20),
                    new Rep("10"), new Comment("-"))
        };
    }

    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            new Exercise(new Name("Flying kicks"), 15),
            new Exercise(new Name("Sit up"), 30),
            new Exercise(new Name("Pull ups"), 20),
            new Exercise(new Name("Jumping jacks"), 15),
            new Exercise(new Name("Run 100m"), 30),
            new Exercise(new Name("Stretch"), 20),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Log sampleLog : getSamplePersons()) {
            sampleAb.addPerson(sampleLog);
        }
        return sampleAb;
    }
}
