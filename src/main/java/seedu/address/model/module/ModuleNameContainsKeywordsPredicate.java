package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code ModuleName} matches any of the keywords given.
 */
public class ModuleNameContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public ModuleNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getModuleName().fullModName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
