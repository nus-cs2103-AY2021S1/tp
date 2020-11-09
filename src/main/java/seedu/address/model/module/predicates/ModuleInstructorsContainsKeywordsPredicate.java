package seedu.address.model.module.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

public class ModuleInstructorsContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public ModuleInstructorsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        Set<Person> instructors = module.getInstructors();
        return keywords.stream()
                .allMatch(keyword -> anyInstructorsNameContainsKeyword(instructors, keyword));
    }

    private static boolean anyInstructorsNameContainsKeyword(Set<Person> instructors, String keyword) {
        for (Person instructor : instructors) {
            if (StringUtil.containsSubWordOrWordIgnoreCase(instructor.getName().toString(), keyword)) {
                return true;
            }
            continue;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleInstructorsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleInstructorsContainsKeywordsPredicate) other).keywords)); // state check
    }
}
