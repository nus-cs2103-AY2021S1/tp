package seedu.zookeep.model.animal;

import java.util.Comparator;
import java.util.Optional;

import seedu.zookeep.model.feedtime.FeedTime;


/**
 * Stores a comparator used for sorting animals according to the category provided.
 */
public class AnimalComparator {

    private static final Comparator<Animal> ANIMAL_FEEDTIME_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            Optional<FeedTime> feedTime1 = o1.getEarliestFeedTime();
            Optional<FeedTime> feedTime2 = o2.getEarliestFeedTime();
            if (feedTime1.isEmpty() && feedTime2.isEmpty()) {
                return 0;
            } else if (feedTime1.isEmpty()) {
                return 1; // feedtime1 has no timeslot, push to the back
            } else if (feedTime2.isEmpty()) {
                return -1;
            } else {
                return Integer.compare(
                        Integer.parseInt(feedTime1.get().feedTime),
                        Integer.parseInt(feedTime2.get().feedTime));
            }
        }
    };

    private static final Comparator<Animal> ANIMAL_ID_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            return Integer.compare(
                    Integer.parseInt(o1.getId().value),
                    Integer.parseInt(o2.getId().value));
        }
    };

    private static final Comparator<Animal> ANIMAL_MEDICAL_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            return o1.getMedicalConditions().size() - o2.getMedicalConditions().size();
        }
    };

    private static final Comparator<Animal> ANIMAL_NAME_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            return o1.getName().fullName.compareTo(o2.getName().fullName);
        }
    };

    private static final String FEEDTIME_CATEGORY = "feedtime";
    private static final String ID_CATEGORY = "id";
    private static final String MEDICAL_CATEGORY = "medical";
    private static final String NAME_CATEGORY = "name";

    private String category;
    private Comparator<Animal> animalComparator;

    private AnimalComparator(Comparator<Animal> animalComparator, String category) {
        this.animalComparator = animalComparator;
        this.category = category;
    }

    /**
     * Creates an animal comparator to sort all animals by earliest feed time in chronological order.
     * @return An animal feed time comparator.
     */
    public static AnimalComparator createAnimalFeedTimeComparator() {
        return new AnimalComparator(ANIMAL_FEEDTIME_COMPARATOR, FEEDTIME_CATEGORY);
    }

    /**
     * Creates an animal comparator to sort all animals by id in ascending order.
     * @return An animal id comparator.
     */
    public static AnimalComparator createAnimalIdComparator() {
        return new AnimalComparator(ANIMAL_ID_COMPARATOR, ID_CATEGORY);
    }

    /**
     * Creates an animal comparator to sort all animals by their number of medical conditions in increasing order.
     * @return An animal medical comparator.
     */
    public static AnimalComparator createAnimalMedicalComparator() {
        return new AnimalComparator(ANIMAL_MEDICAL_COMPARATOR, MEDICAL_CATEGORY);
    }

    /**
     * Creates an animal comparator to sort all animals by name in alphabetical order.
     * @return An animal name comparator.
     */
    public static AnimalComparator createAnimalNameComparator() {
        return new AnimalComparator(ANIMAL_NAME_COMPARATOR, NAME_CATEGORY);
    }

    public String getCategory() {
        return this.category;
    }

    public Comparator<Animal> getAnimalComparator() {
        return this.animalComparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnimalComparator // instanceof handles nulls
                && category.equals(((AnimalComparator) other).category)); // state check
    }
}
