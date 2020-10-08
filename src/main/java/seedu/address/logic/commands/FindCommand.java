package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.SchoolContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.YearMatchPredicate;


/**
 * Finds and lists all persons in Reeve by a certain criteria.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Given a non-empty list of filter criteria, "
            + "finds all persons whose fields match or contain keywords (if applicable, case-insensitive) "
            + "of each of the filter criteria and displays them as a list with index numbers.\n"
            + "Parameters: [n/NAME] [s/SCHOOL] [y/YEAR]\n"
            + "Example: " + COMMAND_WORD + " n/Alex david s/yishun";

    public static final String FIELD_NOT_GIVEN = "At least one field to search by must be provided.";


    private final List<Predicate<Student>> predicates;

    /**
     * @param predicates List of predicates that we use to filter Reeve with
     */
    public FindCommand(List<Predicate<Student>> predicates) {
        assert predicates.size() > 0;
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        predicates.forEach(model::updateFilteredPersonList); // update model for each predicate
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }

    /**
     * Stores the details to find the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class FindStudentDescriptor {
        private NameContainsKeywordsPredicate namePredicate;
        private SchoolContainsKeywordsPredicate schoolPredicate;
        private YearMatchPredicate yearPredicate;


        public FindStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public FindStudentDescriptor(FindCommand.FindStudentDescriptor toCopy) {
            setNamePredicate(toCopy.namePredicate);
            setSchoolPredicate(toCopy.schoolPredicate);
            setYearPredicate(toCopy.yearPredicate);
        }

        /**
         * Returns true if at least one predicate is present.
         */
        public boolean isAnyPredicatePresent() {
            return CollectionUtil.isAnyNonNull(namePredicate, schoolPredicate, yearPredicate);
        }

        /**
         * Returns list of available predicates
         */
        public List<Predicate<Student>> getPredicates() {
            assert isAnyPredicatePresent();
            List<Predicate<Student>> predicates = new ArrayList<>();
            getNamePredicate().ifPresent(predicates::add);
            getSchoolPredicate().ifPresent(predicates::add);
            getYearPredicate().ifPresent(predicates::add);
            return predicates;
        }

        public Optional<NameContainsKeywordsPredicate> getNamePredicate() {
            return Optional.ofNullable(namePredicate);
        }

        public void setNamePredicate(NameContainsKeywordsPredicate namePredicate) {
            this.namePredicate = namePredicate;
        }

        public Optional<SchoolContainsKeywordsPredicate> getSchoolPredicate() {
            return Optional.ofNullable(schoolPredicate);
        }

        public void setSchoolPredicate(SchoolContainsKeywordsPredicate schoolPredicate) {
            this.schoolPredicate = schoolPredicate;
        }

        public Optional<YearMatchPredicate> getYearPredicate() {
            return Optional.ofNullable(yearPredicate);
        }

        public void setYearPredicate(YearMatchPredicate yearPredicate) {
            this.yearPredicate = yearPredicate;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindCommand.FindStudentDescriptor)) {
                return false;
            }

            // state check
            FindCommand.FindStudentDescriptor e = (FindCommand.FindStudentDescriptor) other;

            return getNamePredicate().equals(e.getNamePredicate())
                    && getSchoolPredicate().equals(e.getSchoolPredicate())
                    && getYearPredicate().equals(e.getYearPredicate());
        }
    }
}
