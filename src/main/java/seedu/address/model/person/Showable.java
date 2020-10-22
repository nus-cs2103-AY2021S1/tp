package seedu.address.model.person;

public interface Showable<T> {
    boolean isSameShowable(T otherShowable);
}
