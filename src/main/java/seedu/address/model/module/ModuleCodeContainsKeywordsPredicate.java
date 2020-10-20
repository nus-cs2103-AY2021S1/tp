package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class ModuleCodeContainsKeywordsPredicate implements Predicate<Module> {
    private final String keyword;

    public ModuleCodeContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Module module) {
        return StringUtil.containsSubWordOrWordIgnoreCase(module.getModuleCode().toString(), keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((ModuleCodeContainsKeywordsPredicate) other).keyword)); // state check
    }

}
