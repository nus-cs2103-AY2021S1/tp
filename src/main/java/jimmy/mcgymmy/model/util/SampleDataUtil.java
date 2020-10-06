package jimmy.mcgymmy.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;
import jimmy.mcgymmy.model.food.Carbohydrate;
import jimmy.mcgymmy.model.food.Fat;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.food.Name;
import jimmy.mcgymmy.model.food.Protein;
import jimmy.mcgymmy.model.tag.Tag;

/**
 * Contains utility methods for populating {@code McGymmy} with sample data.
 */
public class SampleDataUtil {
    public static Food[] getSamplePersons() {
        return new Food[] {
            new Food(new Name("Chicken Rice"), new Protein(200), new Fat(300),
                    new Carbohydrate(100),
                    getTagSet("friends")),
            new Food(new Name("Rice Bowl"), new Protein(300), new Fat(200),
                    new Carbohydrate(100),
                    getTagSet("colleagues", "friends")),
            new Food(new Name("Fried Chicken"), new Protein(200), new Fat(100),
                    new Carbohydrate(10),
                    getTagSet("neighbours")),
            new Food(new Name("David Li"), new Protein(132), new Fat(321),
                    new Carbohydrate(125),
                    getTagSet("family")),
            new Food(new Name("Irfan Ibrahim"), new Protein(145), new Fat(432),
                    new Carbohydrate(125),
                    getTagSet("classmates")),
            new Food(new Name("Roy Balakrishnan"), new Protein(452), new Fat(254),
                    new Carbohydrate(564),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyMcGymmy getSampleMcGymmy() {
        McGymmy sampleAb = new McGymmy();
        for (Food sampleFood : getSamplePersons()) {
            sampleAb.addFood(sampleFood);
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

}
