package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelPredicate;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.storage.Storage;

/**
 * Lists all students in ResiReg to the user.
 */
public class ListStudentsCommand extends Command {
    public static final String COMMAND_WORD = "students";

    public static final String MESSAGE_SUCCESS = "Listed all students";
    public static final String MESSAGE_FILTERED_SUCCESS = "Listed students filtered by given criteria";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Lists all students within the system, optionally filtered by some criteria.",
            "Parameters: "
                    + "[" + PREFIX_NAME + "NAME] "
                    + "[" + PREFIX_PHONE + "PHONE] "
                    + "[" + PREFIX_EMAIL + "EMAIL +] "
                    + "[" + PREFIX_FACULTY + "FACULTY +] "
                    + "[" + PREFIX_STUDENT_ID + "STUDENT_ID]\n"
                    + "Example: " + COMMAND_WORD
                    + " " + PREFIX_NAME + "dameeth");

    private final StudentFilter filter;

    public ListStudentsCommand(StudentFilter filter) {
        this.filter = filter;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(model);

        model.updateFilteredStudentList(filter.getStudentPredicate());
        String message = filter.equals(new StudentFilter()) ? MESSAGE_SUCCESS : MESSAGE_FILTERED_SUCCESS;
        return new ToggleCommandResult(message, TabView.STUDENTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListStudentsCommand // instanceof handles nulls
                && filter.equals(((ListStudentsCommand) other).filter));
    }

    /**
     * Stores the criteria by which to filter the list of Students displayed.
     */
    public static class StudentFilter {
        private Set<Name> validNames;
        private Set<Phone> validPhones;
        private Set<Email> validEmails;
        private Set<Faculty> validFaculties;
        private Set<StudentId> validStudentIds;

        /**
         * Creates a new StudentFilter which will show all Students.
         */
        public StudentFilter() {
            validNames = new HashSet<>();
            validPhones = new HashSet<>();
            validEmails = new HashSet<>();
            validFaculties = new HashSet<>();
            validStudentIds = new HashSet<>();
        }

        /**
         * Adds the given names to the collection of valid names to allow.
         *
         * @param names Student names.
         */
        public void addValidNames(Collection<Name> names) {
            validNames.addAll(names);
        }

        /**
         * Adds the given phones to the collection of valid phones to allow.
         *
         * @param phones Student phones.
         */
        public void addValidPhones(Collection<Phone> phones) {
            validPhones.addAll(phones);
        }

        /**
         * Adds the given emails to the collection of valid emails to allow.
         *
         * @param emails Student emails.
         */
        public void addValidEmails(Collection<Email> emails) {
            validEmails.addAll(emails);
        }

        /**
         * Adds the given faculties to the collection of valid faculties to allow.
         *
         * @param faculties Student faculties.
         */
        public void addValidFaculties(Collection<Faculty> faculties) {
            validFaculties.addAll(faculties);
        }

        /**
         * Adds the given Student Ids to the collection of valid Student Ids to allow.
         *
         * @param studentIds Student Ids.
         */
        public void addValidStudentIds(Collection<StudentId> studentIds) {
            validStudentIds.addAll(studentIds);
        }

        /**
         * Returns predicate to apply based on the given collection. If the collection is empty, the predicate
         * will always return true, otherwise, only allow attributes in the collection are allowed.
         *
         * @param collection Collection of valid room attributes.
         */
        private <T> Predicate<T> getPredicateFromCollection(Collection<T> collection) {
            return t -> collection.isEmpty() || collection.contains(t);
        }

        /**
         * Returns predicate to use to filter students.
         */
        ModelPredicate<Student> getStudentPredicate() {
            // We only assume partial match on names, as it is the most common use case.
            Predicate<Name> namePredicate = t -> validNames.isEmpty() || validNames.stream()
                    .anyMatch(n -> t.toString().toLowerCase().contains(n.toString().toLowerCase()));

            // For the rest of the predicates, we favor an exact match.
            Predicate<Phone> phonePredicate = getPredicateFromCollection(validPhones);
            Predicate<Email> emailPredicate = getPredicateFromCollection(validEmails);
            Predicate<Faculty> facultyPredicate = getPredicateFromCollection(validFaculties);
            Predicate<StudentId> studentIdPredicate = getPredicateFromCollection(validStudentIds);

            return (student, model) -> namePredicate.test(student.getName())
                    && phonePredicate.test(student.getPhone())
                    && emailPredicate.test(student.getEmail())
                    && facultyPredicate.test(student.getFaculty())
                    && studentIdPredicate.test(student.getStudentId());
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof ListStudentsCommand.StudentFilter)) {
                return false;
            }
            ListStudentsCommand.StudentFilter otherFilter = (ListStudentsCommand.StudentFilter) other;
            return validNames.equals(otherFilter.validNames)
                    && validStudentIds.equals(otherFilter.validStudentIds)
                    && validFaculties.equals(otherFilter.validFaculties)
                    && validEmails.equals(otherFilter.validEmails)
                    && validPhones.equals(otherFilter.validPhones);
        }
    }
}
