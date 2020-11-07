package seedu.simplykitchen.model.util;

import static seedu.simplykitchen.logic.commands.SortDescCommand.SORT_DESC_COMPARATORS;
import static seedu.simplykitchen.logic.commands.SortExpiryCommand.SORT_EXPIRY_COMPARATORS;
import static seedu.simplykitchen.logic.commands.SortPriorityCommand.SORT_PRIORITY_COMPARATORS;

import java.util.Comparator;

import seedu.simplykitchen.model.food.Food;


/**
 * Contains utility comparators for sorting food lists.
 */
public class ComparatorUtil {
    public static final Comparator<Food> DEFAULT = (food1, food2) -> 0;
    public static final Comparator<Food>[] DEFAULT_COMPARATOR_ARRAY = new Comparator[]{(food1, food2) -> 0};
    public static final Comparator<Food> SORT_BY_ASCENDING_EXPIRY_DATE = (food1, food2) ->
                food1.getExpiryDate().isAfter(food2.getExpiryDate())
                    ? 1
                    : food1.getExpiryDate().equals(food2.getExpiryDate())
                        ? 0
                        : -1;
    public static final Comparator<Food> SORT_BY_DESCENDING_PRIORITY = (food1, food2) ->
                food1.getPriority().isHigherPriority(food2.getPriority())
                    ? -1
                    : food1.getPriority().equals(food2.getPriority())
                        ? 0
                        : 1;
    public static final Comparator<Food> SORT_BY_LEXICOGRAPHICAL_ORDER = (food1, food2) ->
                food1.getDescription().fullDescription.compareTo(food2.getDescription().fullDescription);
    public static final Comparator<Food> SORT_BY_FIRST_CHARACTER = (food1, food2) ->
                food1.getDescription().compareFirstCharacterTo(food2.getDescription());
    public static final Comparator<Food> SORT_BY_DESC_THEN_ASC_EXPIRY = SORT_BY_ASCENDING_EXPIRY_DATE
            .thenComparing(SORT_BY_FIRST_CHARACTER).thenComparing(SORT_BY_LEXICOGRAPHICAL_ORDER);

    /**
     * Returns the foodInventorySortingComparators information according to {@code comparatorDescription}.
     */
    public static Comparator<Food>[] getComparator(String comparatorDescription) {
        switch (comparatorDescription) {
        case "default without ordering":
            return DEFAULT_COMPARATOR_ARRAY;
        case "ascending expiry date":
            return SORT_EXPIRY_COMPARATORS;
        case "descending priority":
            return SORT_PRIORITY_COMPARATORS;
        case "description":
            return SORT_DESC_COMPARATORS;
        default:
            throw new IllegalArgumentException("Comparator is not defined.");
        }
    }

    /**
     * Returns a String describing the sorting used according to {@code foodInventorySortingComparators}.
     */
    public static String generateSortingComparatorsDescription(Comparator<Food>[] sortingComparators) {
        if (sortingComparators.equals(DEFAULT_COMPARATOR_ARRAY)) {
            return "default without ordering";
        } else if (sortingComparators.equals(SORT_EXPIRY_COMPARATORS)) {
            return "ascending expiry date";
        } else if (sortingComparators.equals(SORT_PRIORITY_COMPARATORS)) {
            return "descending priority";
        } else if (sortingComparators.equals(SORT_DESC_COMPARATORS)) {
            return "description";
        } else {
            throw new IllegalArgumentException("Comparator is not defined.");
        }
    }
}
