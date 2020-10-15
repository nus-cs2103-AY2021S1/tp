package seedu.address.model.task;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Task}'s given attribute matches any of the keywords given.
 */
public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    private final Map<Prefix, String> keywords;

    public TaskContainsKeywordsPredicate() {
        this.keywords = new HashMap<>();
    }

    public void setKeyword(Prefix attribute, String keyword) {
        keywords.put(attribute, keyword);
    }

    @Override
    public boolean test(Task task) {
        for (Map.Entry<Prefix, String> entry : keywords.entrySet()) {
            Prefix prefix = entry.getKey();
            String word = entry.getValue();
            if (isMatched(prefix.getPrefix(), word, task)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskContainsKeywordsPredicate)) {
            return false;
        }

        TaskContainsKeywordsPredicate casted = (TaskContainsKeywordsPredicate) other;
        for (Prefix key : keywords.keySet()) {
            if (!casted.keywords.containsKey(key) || !keywords.get(key).equals(casted.keywords.get(key))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatched(String prefix, String word, Task task) {
        if (prefix.equals("title:")) {
            return StringUtil.matchesWordIgnoreCase(task.getTitle().title, word);
        }

        if (prefix.equals("desc:")) {
            return StringUtil.matchesWordIgnoreCase(task.getDescription().value, word);
        }

        if (prefix.equals("date:")) {
            return StringUtil.matchesWordIgnoreCase(task.getDateTime().value, word);
        }

        if (prefix.equals("type:")) {
            return StringUtil.matchesWordIgnoreCase(task.getType().value, word);
        }
        if (prefix.equals("status:")) {
            // must be an exact match here
            return task.getStatus().value.toString().toLowerCase().equals(word.toLowerCase());
        }

        return false;
    }


}
