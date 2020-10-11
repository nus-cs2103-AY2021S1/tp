package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

public class CodeOrNameMatchesKeywordPredicate implements Predicate<Module> {
    private List<String> keywords;
    public CodeOrNameMatchesKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Module module) {
        return module.codeOrNameContainsKeywords(keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CodeOrNameMatchesKeywordPredicate // instanceof handles nulls
                && keywords.equals(((CodeOrNameMatchesKeywordPredicate) other).keywords)); // state check
    }
}
