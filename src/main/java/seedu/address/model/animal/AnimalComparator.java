package seedu.address.model.animal;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.feedtime.FeedTime;


/**
 * Container for all animal comparators used for sorting.
 */
public class AnimalComparator {

    public static final Comparator<Animal> ANIMAL_NAME_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            return o1.getName().fullName.compareTo(o2.getName().fullName);
        }
    };

    public static final Comparator<Animal> ANIMAL_ID_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            return Integer.compare(
                    Integer.parseInt(o1.getId().value),
                    Integer.parseInt(o2.getId().value));
        }
    };

    public static final Comparator<Animal> ANIMAL_FEEDTIME_COMPARATOR = new Comparator<Animal>() {
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
}
