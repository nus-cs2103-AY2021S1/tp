package seedu.address.model.module.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.module.Module;

public class ModuleNameContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public ModuleNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsSubWordOrWordIgnoreCase(
                        module.getModuleName().moduleName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
