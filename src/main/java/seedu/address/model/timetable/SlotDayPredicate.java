package seedu.address.model.timetable;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Slot}'s {@code Day} matches the day given.
 */
public class SlotDayPredicate implements Predicate<Slot> {
    private final List<String> keywords;

    public SlotDayPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Slot slot) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsCharIgnoreCase(slot.getDay().getDay(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SlotDayPredicate // instanceof handles nulls
                && keywords.equals(((SlotDayPredicate) other).keywords)); // state check
    }

}
