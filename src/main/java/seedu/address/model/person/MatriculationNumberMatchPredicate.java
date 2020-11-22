package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code matriculation number} matches the current matriculation number.
 */
public class MatriculationNumberMatchPredicate implements Predicate<Person> {
    private final MatriculationNumber matriculationNumber;

    /**
     * Constructs a {@code MatriculationNumberMatchPredicate}.
     *
     * @param matriculationNumber matriculationNumber to test people with.
     */
    public MatriculationNumberMatchPredicate(MatriculationNumber matriculationNumber) {
        requireNonNull(matriculationNumber);
        this.matriculationNumber = matriculationNumber;
    }

    @Override
    public boolean test(Person person) {
        return person.getMatriculationNumber().equals(matriculationNumber);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumberMatchPredicate // instanceof handles nulls
                && matriculationNumber
                    .equals(((MatriculationNumberMatchPredicate) other).matriculationNumber)); // state check
    }

}
