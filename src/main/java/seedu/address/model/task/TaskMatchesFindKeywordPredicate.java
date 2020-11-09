package seedu.address.model.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Tag} and {@code Description} matches the tag keyword given.
 */
public class TaskMatchesFindKeywordPredicate implements Predicate<Task> {
    private final List<String> keywordDescription;
    private final String keywordTag;

    /**
     * Construct a predicate to match keyword tag to task's tag
     * @param keywordTag the keyword for the tag
     */
    public TaskMatchesFindKeywordPredicate(String keywordTag) {
        // make sure keyword is only one word
        assert !keywordTag.contains("\\s+");
        this.keywordTag = keywordTag;
        this.keywordDescription = new ArrayList<>();
    }

    /**
     * Construct a predicate to match keyword description to task's description
     * @param keywordDescription the keyword for the description
     */
    public TaskMatchesFindKeywordPredicate(List<String> keywordDescription) {
        this.keywordDescription = keywordDescription;
        this.keywordTag = "";
    }

    /**
     * Construct a predicate to match keyword description and tag to task's description and tag
     * @param keywordDescription the keyword for the description
     * @param keywordTag the keyword for the tag
     */
    public TaskMatchesFindKeywordPredicate(List<String> keywordDescription, String keywordTag) {
        // make sure keyword is only one word
        assert !keywordTag.contains("\\s+");
        this.keywordTag = keywordTag;
        this.keywordDescription = keywordDescription;
    }

    @Override
    public boolean test(Task task) {
        boolean matchName = keywordDescription.size() == 0 || keywordDescription.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription(), keyword));
        boolean matchTag = keywordTag == "" || task.getTags().stream()
            .anyMatch(keyword -> this.keywordTag.trim().toLowerCase().equals(keyword.tagName.trim().toLowerCase()));
        return matchName && matchTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TaskMatchesFindKeywordPredicate // instanceof handles nulls
            && keywordDescription.equals(((TaskMatchesFindKeywordPredicate) other).keywordDescription) // state check
            && keywordTag.equals(((TaskMatchesFindKeywordPredicate) other).keywordTag));
    }
}
