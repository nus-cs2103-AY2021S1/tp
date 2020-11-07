package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements PersonPredicate {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    //@@author jerrylchong
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> (
                        person.getName().fullName.toLowerCase().contains(keyword.toLowerCase())
                        || hasInitials(person, keyword)
                ));
    }
    //@@author

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    //@@author jerrylchong
    private boolean hasInitials(Person person, String keyword) {
        String fullName = person.getName().fullName;
        String[] splitName = fullName.split("\\s");
        String initials = Arrays.stream(splitName).reduce("", (x, y) -> x + y.substring(0, 1));
        return initials.toLowerCase().equals(keyword.toLowerCase());
    }
    //@@author

}
