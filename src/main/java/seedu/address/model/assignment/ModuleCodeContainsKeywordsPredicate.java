package seedu.address.model.assignment;

import seedu.address.commons.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ModuleCodeContainsKeywordsPredicate implements Predicate<Assignment> {
    private final ModuleCode moduleCode;

    public ModuleCodeContainsKeywordsPredicate(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public boolean test(Assignment assignment) {
        List<ModuleCode> listForm = new ArrayList<>();
        listForm.add(moduleCode);
        return listForm.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(assignment.getModuleCode().moduleCode, moduleCode.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeContainsKeywordsPredicate // instanceof handles nulls
                && moduleCode.equals(((ModuleCodeContainsKeywordsPredicate) other).moduleCode)); // state check
    }

}