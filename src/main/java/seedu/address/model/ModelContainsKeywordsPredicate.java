package seedu.address.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Model}'s given attribute matches any of the keywords given.
 */
public abstract class ModelContainsKeywordsPredicate<T> implements Predicate<T> {
    protected final Map<Prefix, List<String>> keywords;

    public ModelContainsKeywordsPredicate() {
        this.keywords = new HashMap<>();
    }

    public void setKeyword(Prefix attribute, String keyword) {
        List<String> extendedKeywords = new ArrayList<>();
        if (keywords.containsKey(attribute)) {
            extendedKeywords = keywords.get(attribute);
        }
        extendedKeywords.add(keyword);
        keywords.put(attribute, extendedKeywords);

        // assert if attribute is added to keywords
        assert keywords.containsKey(attribute) : "attribute not added to keywords";
    }

    @Override
    public abstract boolean test(T t);

    @Override
    public abstract boolean equals(Object other);
}

