package seedu.address.model.animal;

import java.util.Comparator;

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
}
