package seedu.address.model.visit;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a patient's visit history in the CliniCal application.
 */
public class VisitHistory {

    public static final String MESSAGE_CONSTRAINTS = "Visit history should take any valid Visit";

    private final ArrayList<Visit> visits;

    /**
     * Constructs a {@code VisitHistory}.
     *
     * @param visits A list of valid visits.
     */
    public VisitHistory(ArrayList<Visit> visits) {
        this.visits = visits;
    }

//    /**
//     * Adds a new visit to the visit history.
//     */
//    public VisitHistory addVisit(Visit toAdd) {
//        this.visits.add(toAdd);
//        Collections.sort(this.visits);
//        return this;
//    }
//
//    /**
//     * Edits a visit(specified by index) from the visit history.
//     */
//    public VisitHistory editVisit(int idx, Visit toEdit) {
//        this.visits.set(idx - 1, toEdit);
//        return this;
//    }
//
//    /**
//     * Deletes a visit(specified by index) from the visit history.
//     */
//    public VisitHistory deleteVisit(int idx) throws IndexOutOfBoundsException {
//        this.visits.remove(idx - 1);
//        return this;
//    }
//
//    public Visit getVisitByIndex(int idx) throws IndexOutOfBoundsException {
//        return visits.get(idx - 1);
//    }

    public ArrayList<Visit> getVisits() {
        return this.visits;
    }

    /**
     * Returns true if both visit history have the same list of visits.
     * This defines a stronger notion of equality between two visit history.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VisitHistory)) {
            return false;
        }

        VisitHistory otherVisitHistory = (VisitHistory) other;
        return otherVisitHistory.getVisits().equals(getVisits());
    }

    @Override
    public int hashCode() {
        return Objects.hash(visits);
    }

    @Override
    public String toString() {
        if (visits.isEmpty()) {
            return "No past visit history";
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("Visit History:\n");
        this.visits.forEach(builder::append);
        return builder.toString();
    }
}
