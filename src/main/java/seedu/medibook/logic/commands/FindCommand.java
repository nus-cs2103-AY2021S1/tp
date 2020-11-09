package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import seedu.medibook.commons.core.Messages;
import seedu.medibook.model.Model;
import seedu.medibook.model.patient.Patient;

/**
 * Finds and lists all patients in medi book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all patients whose fields matches with the specified keywords (case-insensitive) provided "
            + "and displays them as a list with index numbers.\nAt least one search field must be specified.\n"
            + "Parameters: "
            + PREFIX_IC + "IC... "
            + PREFIX_NAME + "NAME... "
            + PREFIX_DATE + "DATE OF BIRTH... "
            + PREFIX_PHONE + "PHONE... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_IC + "S9123456B T1234567G "
            + PREFIX_NAME + "Alice Bernice Charlotte "
            + PREFIX_DATE + "28-02-2012 "
            + PREFIX_PHONE + "98765432 69204068 93210283";

    private final List<Predicate<Patient>> predicates;

    public FindCommand(Predicate<Patient> predicate) {
        this.predicates = Collections.singletonList(predicate);
    }

    public FindCommand(List<Predicate<Patient>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Patient> combinedPredicates = predicates.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredPatientList(combinedPredicates);
        model.resetAccessedPatient();

        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENT_LISTED_OVERVIEW, model.getFilteredPatientList().size()),
                false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && arePredicatesEqual(predicates, ((FindCommand) other).predicates)); // state check
    }

    /**
     * Checks if the two lists contains the same predicates regardless of order.
     */
    private boolean arePredicatesEqual(List<Predicate<Patient>> predicates1, List<Predicate<Patient>> predicates2) {
        if (predicates1.size() != predicates2.size()) {
            return false;
        }
        HashSet<Predicate<Patient>> predicatesSet = new HashSet<>(predicates1);
        predicatesSet.removeAll(predicates2);
        return predicatesSet.isEmpty();
    }
}
