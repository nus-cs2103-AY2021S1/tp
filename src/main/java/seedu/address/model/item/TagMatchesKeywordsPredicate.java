package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Tags} contains any of the keyword tags given, case-insensitive.
 */
public class TagMatchesKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    /**
     * Constructs a TagMatchesKeywordsPredicate that must not be empty
     * @param keywords keyword patterns to match.
     */
    public TagMatchesKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
                .anyMatch(keyword -> item.getTags()
                        .stream()
                        .anyMatch(tag -> !keyword.strip().isBlank() && (StringUtil
                                .matchesPatternIgnoreCase(tag.getTagName(),
                                        Pattern.compile(keyword.strip(), Pattern.CASE_INSENSITIVE))))
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagMatchesKeywordsPredicate) other).keywords)); // state check
    }

}
