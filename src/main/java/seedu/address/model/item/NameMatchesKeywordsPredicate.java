package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Item}'s {@code Name} contains any of the keywords given, case-insensitive.
 */
public class NameMatchesKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;
    private final List<Pattern> patterns;

    /**
     * Constructs a predicate using the given keywords.
     * @param keywords keywords to use to search.
     */
    public NameMatchesKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        assert keywords.size() > 0;

        this.keywords = keywords;
        this.patterns = keywords.stream()
                .map(x -> Pattern.compile(x, Pattern.CASE_INSENSITIVE))
                .collect(Collectors.toList());
    }

    @Override
    public boolean test(Item item) {
        return patterns.stream()
                .anyMatch(keywordPattern -> StringUtil.matchesPatternIgnoreCase(item.getName(), keywordPattern));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameMatchesKeywordsPredicate) other).keywords)); // state check
    }

}
