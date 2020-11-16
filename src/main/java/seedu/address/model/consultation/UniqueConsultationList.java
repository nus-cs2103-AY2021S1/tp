package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.consultation.exceptions.ConflictingGroupConsultationException;
import seedu.address.model.consultation.exceptions.ConflictingPersonalConsultationException;
import seedu.address.model.consultation.exceptions.ConsultationNotFoundException;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;


/**
 * A list of consultations that enforces uniqueness between its elements and does not allow nulls.
 * A consultation is considered unique by comparing using {@code Consultation#isSameConsultation(Consultation)}.
 * As such, adding and updating of consultations uses Consultation#isSameConsultation(Consultation) for equality so as
 * to ensure that the consultation being added or updated is unique in terms of identity in the UniqueConsultationList.
 * However, the removal of a consultation uses Consultation#equals(Object) so
 * as to ensure that the consultation with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Consultation#isSameConsultation(Consultation)
 */
public class UniqueConsultationList implements Iterable<Consultation> {
    private final ObservableList<Consultation> internalConsults = FXCollections.observableArrayList();
    private final ObservableList<Consultation> internalUnmodifiableConsults =
            FXCollections.unmodifiableObservableList(internalConsults);

    /**
     * Returns true if the list contains an equivalent consultation as the given argument.
     *
     * @param toCheck the consultation being checked.
     * @return Presence of consultation in current list.
     */
    public boolean contains(Consultation toCheck) {
        requireNonNull(toCheck);
        return internalConsults.stream().anyMatch(toCheck::isSameConsultation);
    }

    /**
     * Returns true if the list contains an personal consultation that conflicts with as the given argument
     * in terms of timing.
     *
     * @param toCheck Consultation being checked.
     * @return Presence of conflicting consultation.
     */
    public boolean hasConflictingPersonalConsult(Consultation toCheck) {
        return internalConsults.stream().anyMatch(toCheck::isPersonalConsultationOnSameTiming);
    }

    /**
     * Adds a consultation to the list.
     * The consultation must not already exist in the list.
     *
     * @param toAdd Consultation to add to the list.
     * @throws DuplicateConsultationException If consultation was already present.
     */
    public void add(Consultation toAdd) throws
            DuplicateConsultationException,
            ConflictingPersonalConsultationException,
            ConflictingGroupConsultationException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateConsultationException();
        }
        // conflicting personal consultation
        if (hasConflictingPersonalConsult(toAdd)) {
            throw new ConflictingPersonalConsultationException();
        }
        // conflicting group consultation
        if (hasConflictingGroupConsultation(toAdd)) {
            throw new ConflictingGroupConsultationException();
        }
        internalConsults.add(toAdd);
    }

    private boolean hasConflictingGroupConsultation(Consultation consultation) {
        return internalConsults.stream().anyMatch(consultation::isGroupConsultationOnSameTimingAndDifferentLocation);
    }

    /**
     * Replaces the consultation {@code target} in the list with {@code editedConsultation}.
     * {@code target} must exist in the list.
     * The consultation identity of {@code editedConsultation} must not be the same as
     * another existing consultation in the list.
     *
     * @param target Consultation set to be replaced.
     * @param editedConsultation Replacement for the targeted consultation.
     * @throws ConsultationNotFoundException If targeted consultation is missing.
     * @throws DuplicateConsultationException If replacement consultation was merely a duplicate in list.
     */
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);

        int index = internalConsults.indexOf(target);
        if (index == -1) {
            throw new ConsultationNotFoundException();
        }

        if (!target.isSameConsultation(editedConsultation) && contains(editedConsultation)) {
            throw new DuplicateConsultationException();
        }

        internalConsults.set(index, editedConsultation);
    }

    /**
     * Replaces all the consultations in the current list with the ones from the given {@code UniqueConsultationList}.
     *
     * @param replacement Replacement unique consultation list.
     */
    public void setConsultations(UniqueConsultationList replacement) {
        requireNonNull(replacement);
        internalConsults.setAll(replacement.internalConsults);
    }

    /**
     * Replaces the contents of this list with {@code consultations}.
     * {@code consultations} must not contain duplicate consultations.
     *
     * @param consultations Replacement list of consultations.
     * @throws DuplicateConsultationException If there are duplicates from the given list.
     */
    public void setConsultations(List<Consultation> consultations) {
        requireAllNonNull(consultations);
        if (!consultsAreUnique(consultations)) {
            throw new DuplicateConsultationException();
        }

        internalConsults.setAll(consultations);
    }

    /**
     * Removes the equivalent consultation from the list.
     * The consultation must exist in the list.
     *
     * @param toRemove Consultation to remove from the list.
     * @throws ConsultationNotFoundException If consultation is not found in the list.
     */
    public void remove(Consultation toRemove) {
        requireNonNull(toRemove);
        if (!internalConsults.remove(toRemove)) {
            throw new ConsultationNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return ObservableList of consultations.
     */
    public ObservableList<Consultation> asUnmodifiableObservableList() {
        return internalUnmodifiableConsults;
    }

    @Override
    public Iterator<Consultation> iterator() {
        return internalConsults.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueConsultationList // instanceof handles nulls
                && internalConsults.equals(((UniqueConsultationList) other).internalConsults));
    }

    @Override
    public int hashCode() {
        return internalConsults.hashCode();
    }

    /**
     * Returns true if {@code consultations} contains only unique consultations.
     *
     * @param consultations List of consultations.
     * @return Confirmation of all consults being unique.
     */
    private boolean consultsAreUnique(List<Consultation> consultations) {
        for (int i = 0; i < consultations.size() - 1; i++) {
            for (int j = i + 1; j < consultations.size(); j++) {
                if (consultations.get(i).isSameConsultation(consultations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
