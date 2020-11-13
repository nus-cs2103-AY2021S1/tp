package seedu.address.model.tag;

import java.util.function.Predicate;

import seedu.address.model.label.Label;

public class TagLabelEqualsKeywordPredicate implements Predicate<Tag> {
    private final Label labelToFind;

    public TagLabelEqualsKeywordPredicate(Label labelToFind) {
        this.labelToFind = labelToFind;
    }

    @Override
    public boolean test(Tag tag) {
        return tag.getLabels().contains(labelToFind);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagLabelEqualsKeywordPredicate // instanceof handles nulls
                && labelToFind.equals(((TagLabelEqualsKeywordPredicate) other).labelToFind)); // state check
    }
}
