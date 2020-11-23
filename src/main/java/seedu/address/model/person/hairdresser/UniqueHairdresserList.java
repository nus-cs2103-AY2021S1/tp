package seedu.address.model.person.hairdresser;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.UniqueEntityList;
import seedu.address.model.person.RecordContainsHairdresserIdPredicate;
import seedu.address.model.person.hairdresser.exception.DuplicateHairdresserException;
import seedu.address.model.person.hairdresser.exception.HairdresserNotFoundException;

public class UniqueHairdresserList extends UniqueEntityList<Hairdresser> {


    public void setHairdresser(Hairdresser target, Hairdresser editedHairdresser) {
        setEntity(target, editedHairdresser);
    }



    public void setHairdressers(UniqueHairdresserList replacement) {
        setEntities(replacement);
    }

    /**
     * Replaces the contents of this list with {@code hairdressers}.
     * {@code hairdressers} must not contain duplicate hairdressers.
     */
    public void setHairdressers(List<Hairdresser> clients) {
        setEntities(clients);
    }

    /**
     * Returns Hairdresser with given PersonId.
     */
    public Hairdresser findHairdresserById(HairdresserId idToCheck) {
        requireNonNull(idToCheck);
        Predicate<Hairdresser> predicate = new RecordContainsHairdresserIdPredicate(idToCheck);
        FilteredList<Hairdresser> hairdresserWithId = internalList.filtered(predicate);
        if (hairdresserWithId.isEmpty()) {
            return null;
        }
        return hairdresserWithId.get(0);
    }

    @Override
    public DuplicateHairdresserException duplicateException() {
        return new DuplicateHairdresserException();
    }

    @Override
    public HairdresserNotFoundException notFoundException() {
        return new HairdresserNotFoundException();
    }

}

