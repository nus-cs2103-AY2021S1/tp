package seedu.resireg.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/*
 * Code for InvalidationListenerList is adapted from addressbook-level4,
 * which can be found at https://github.com/se-edu/addressbook-level4.
 */
/**
 * Contains a list of {@link InvalidationListener} objects. Added so that
 * storage functions are invoked only when necessary, which improves
 * performance while also allowing history to be updated.
 */
public class InvalidationListenerList {
    private final LinkedList<InvalidationListener> listeners = new LinkedList<>();

    /**
     * Calls {@link InvalidationListener#invalidated(Observable)} on all added listeners.
     * Any modifications to the list during the method's invocation
     * will only take effect on the next invocation.
     *
     * @param obs The {@code Observable} that turned invalid.
     */
    public void callListeners(Observable obs) {
        LinkedList<InvalidationListener> copy = new LinkedList<>(listeners);
        copy.forEach((listener) -> listener.invalidated(obs));
    }

    /**
     * Adds {@code listener} to the list. Guaranteed to be O(1).
     * If the same listener is added multiple times, it will be notified multiple times as well.
     */
    public void addListener(InvalidationListener listener) {
        requireNonNull(listener);
        listeners.add(listener);
    }

    /**
     * Removes {@code listener} from the list.
     * If the listener was not added previously, then this method does nothing.
     * However, if the  listener was added multiple times, only the first occurrence will be removed.
     */
    public void removeListener(InvalidationListener listener) {
        requireNonNull(listener);
        listeners.remove(listener);
    }

}
