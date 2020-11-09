package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Tag} and {@code Name} matches the tag keyword given.
 */
public class ContactMatchesFindKeywordPredicate implements Predicate<Person> {
    private final List<String> keywordName;
    private final String keywordTag;

    /**
     * Construct a predicate to match keyword tag to contact's tag
     * @param keywordTag the keyword for the tag
     */
    public ContactMatchesFindKeywordPredicate(String keywordTag) {
        // make sure keyword is only one word
        assert !keywordTag.contains("\\s+");
        this.keywordTag = keywordTag;
        this.keywordName = new ArrayList<>();
    }

    /**
     * Construct a predicate to match keyword name to contact's name
     * @param keywordName the keyword for the name
     */
    public ContactMatchesFindKeywordPredicate(List<String> keywordName) {
        this.keywordName = keywordName;
        this.keywordTag = "";
    }

    /**
     * Construct a predicate to match keyword name and tag to contact's name and tag
     * @param keywordName the keyword for the name
     * @param keywordTag the keyword for the tag
     */
    public ContactMatchesFindKeywordPredicate(List<String> keywordName, String keywordTag) {
        // make sure keyword is only one word
        assert !keywordTag.contains("\\s+");
        this.keywordTag = keywordTag;
        this.keywordName = keywordName;
    }

    @Override
    public boolean test(Person person) {
        boolean matchName = keywordName.size() == 0 || keywordName.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        boolean matchTag = keywordTag == "" || person.getTags().stream()
            .anyMatch(keyword -> this.keywordTag.trim().toLowerCase().equals(keyword.tagName.trim().toLowerCase()));
        return matchName && matchTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ContactMatchesFindKeywordPredicate // instanceof handles nulls
            && keywordName.equals(((ContactMatchesFindKeywordPredicate) other).keywordName) // state check
            && keywordTag.equals(((ContactMatchesFindKeywordPredicate) other).keywordTag));
    }
}
