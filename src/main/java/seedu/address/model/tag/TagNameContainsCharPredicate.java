package seedu.address.model.tag;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Tag}'s {@code Name} matches any of the sequence of characters given.
 */
public class TagNameContainsCharPredicate extends TagNameContainsKeywordsPredicate {
    public TagNameContainsCharPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Tag tag) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsCharIgnoreCase(tag.getTagName().tagName, keyword)
                        || tag.getLabels().stream().anyMatch(
                            label -> StringUtil.containsCharIgnoreCase(label.getLabel(), keyword)));
    }
}
