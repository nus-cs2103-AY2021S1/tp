package seedu.address.model.task;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.ModelContainsKeywordsPredicate;
import seedu.address.model.task.deadline.Deadline;

/**
 * Tests that a {@code Task}'s given attribute matches any of the keywords given.
 */
public class TaskContainsKeywordsPredicate extends ModelContainsKeywordsPredicate<Task> implements Predicate<Task> {
    private final Map<Prefix, List<String>> keywords;

    public TaskContainsKeywordsPredicate() {
        keywords = super.keywords;
    }

    @Override
    public boolean test(Task task) {
        boolean matched = false;
        for (Map.Entry<Prefix, List<String>> entry : keywords.entrySet()) {
            Prefix prefix = entry.getKey();
            List<String> words = entry.getValue();
            if (!isMatched(prefix.getPrefix(), words, task)) {
                return false;
            }
            matched = true;
        }
        return matched;
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

    private boolean isMatched(String prefix, List<String> words, Task task) {
        // assert prefix is of the desired format
        assert prefix.charAt(prefix.length() - 1) == ':' : "prefix string is in the wrong format";
        if (prefix.equals("title:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(task.getTitle().title, keyword));
        }

        if (prefix.equals("desc:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(task.getDescription().value, keyword));
        }

        if (prefix.equals("date:")) {
            String dateTime = task.getDate().format(DateUtil.DATE_FORMATTER);
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(dateTime, keyword));
        }

        if (prefix.equals("status:")) {
            return task instanceof Deadline && words.stream()
                    .anyMatch(keyword -> ((Deadline) task).getStatus().toString().toLowerCase()
                            .equals(keyword.toLowerCase()));
        }

        if (prefix.equals("tag:")) {
            return words.stream()
                    .anyMatch(keyword -> StringUtil.matchesWordIgnoreCase(task.getTag().tagName, keyword));
        }

        return false;
    }


}
