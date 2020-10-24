package chopchop.model.attributes;

import chopchop.commons.util.StringUtil;
import chopchop.model.Entry;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Tests that an item's {@code Name} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Entry> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        Stream<String> tagNameList = entry.getTags()
                .stream().map(tag -> tag.getTagName());

        return tagNameList.anyMatch(tagName -> this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tagName, keyword)));

        //      String tagStr = entry.getTags().stream().map(t -> t.toString()).collect(Collectors.joining());
        //      return this.keywords.stream()
        //              .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tagStr, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
