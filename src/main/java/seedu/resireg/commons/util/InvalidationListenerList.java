package seedu.resireg.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * Contains a list of {@link InvalidationListener} objects. Added so that
 * storage functions are invoked only when necessary, which improves
 * performance while also allowing history to be updated.
 */

/*
 * Code for InvalidationListenerList is adapted from addressbook-level4,
 * which can be found at https://github.com/se-edu/addressbook-level4.
 */
public class InvalidationListenerList {
    private final ArrayList<InvalidationListener> listeners = new ArrayList<>();

    /**
     * Calls {@link InvalidationListener#invalidated(Observable)} on all added listeners.
     * Any modifications to the list during the method's invocation
     * will only take effect on the next invocation.
     *
     * @param obs The {@code Observable} that turned invalid.
     */
    public void callListeners(Observable obs) {
        ArrayList<InvalidationListener> copy = new ArrayList<>(listeners);

        for (InvalidationListener listener : copy) {
            listener.invalidated(obs);
        }
    }

    /**
     * Adds {@code listener} to the list.
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
