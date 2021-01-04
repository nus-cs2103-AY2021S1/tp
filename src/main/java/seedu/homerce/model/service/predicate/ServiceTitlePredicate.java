package seedu.homerce.model.service.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.homerce.commons.util.StringUtil;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Title;

/**
 * Tests that a {@code Service}'s {@code Title} matches the given Title.
 */
public class ServiceTitlePredicate implements Predicate<Service> {
    private final Title title;

    public ServiceTitlePredicate(Title title) {
        this.title = title;
    }

    @Override
    public boolean test(Service service) {
        List<String> keywords = Arrays.asList(title.value.toLowerCase().split(" "));
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(service.getTitle().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ServiceTitlePredicate // instanceof handles nulls
            && title.equals(((ServiceTitlePredicate) other).title)); // state check
    }
}
