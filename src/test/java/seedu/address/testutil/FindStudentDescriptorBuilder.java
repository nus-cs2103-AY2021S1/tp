package seedu.address.testutil;

import seedu.address.logic.commands.FindCommand.FindStudentDescriptor;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.SchoolContainsKeywordsPredicate;
import seedu.address.model.student.YearMatchPredicate;


/**
 * A utility class to help with building FindStudentDescriptor objects.
 */
public class FindStudentDescriptorBuilder {

    private FindStudentDescriptor descriptor;

    public FindStudentDescriptorBuilder() {
        descriptor = new FindStudentDescriptor();
    }

    public FindStudentDescriptorBuilder(FindStudentDescriptor descriptor) {
        this.descriptor = new FindStudentDescriptor(descriptor);
    }

    /**
     * Sets the {@code namePredicate} of the {@code FindStudentDescriptor} that we are building.
     */
    public FindStudentDescriptorBuilder withNamePredicate(NameContainsKeywordsPredicate namePredicate) {
        descriptor.setNamePredicate(namePredicate);
        return this;
    }

    /**
     * Sets the {@code schoolPredicate} of the {@code FindStudentDescriptor} that we are building.
     */
    public FindStudentDescriptorBuilder withSchoolPredicate(SchoolContainsKeywordsPredicate schoolPredicate) {
        descriptor.setSchoolPredicate(schoolPredicate);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code FindStudentDescriptor} that we are building.
     */
    public FindStudentDescriptorBuilder withYearPredicate(YearMatchPredicate yearPredicate) {
        descriptor.setYearPredicate(yearPredicate);
        return this;
    }

    public FindStudentDescriptor build() {
        return descriptor;
    }
}
