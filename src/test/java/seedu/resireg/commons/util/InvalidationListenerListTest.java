package seedu.resireg.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;

public class InvalidationListenerListTest {
    private final SimpleObjectProperty<Object> testObservable = new SimpleObjectProperty<>();
    private final InvalidationListenerList listenerList = new InvalidationListenerList();
    private String state = "";

    @Test
    public void addListener_sameListenerOnce_listenerAdded() {
        listenerList.addListener(observable -> {
            state += "a";
            assertEquals(testObservable, observable);
        });
        listenerList.callListeners(testObservable);
        assertEquals("a", state);
    }

    @Test
    public void addListener_sameListenerTwice_listenerAddedTwice() {
        InvalidationListener listener = observable -> state += "a";
        listenerList.addListener(listener);
        listenerList.addListener(listener);
        listenerList.callListeners(testObservable);
        assertEquals("aa", state);
    }

    @Test
    public void addListener_listenersBeingCalled_listenerNotCalled() {
        InvalidationListener listener = observable -> {
            throw new AssertionError("This method should not be called");
        };
        InvalidationListener otherListener = observable -> listenerList.addListener(listener);
        listenerList.addListener(otherListener);
        listenerList.callListeners(testObservable);
    }

    @Test
    public void removeListener_singleListenerAdded_listenerRemoved() {
        InvalidationListener listener = observable -> state += "a";
        listenerList.addListener(listener);
        listenerList.removeListener(listener);
        listenerList.callListeners(testObservable);
        assertEquals("", state);
    }

    @Test
    public void removeListener_sameListenerAddedTwice_firstListenerRemoved() {
        InvalidationListener listener = observable -> state += "a";
        listenerList.addListener(listener);
        listenerList.addListener(listener);
        listenerList.removeListener(listener);
        listenerList.callListeners(testObservable);
        assertEquals("a", state);
    }

    @Test
    public void removeListener_sameListenerAddedTwice_bothListenersRemoved() {
        InvalidationListener listener = observable -> state += "a";
        listenerList.addListener(listener);
        listenerList.addListener(listener);
        listenerList.removeListener(listener);
        listenerList.removeListener(listener);
        listenerList.callListeners(testObservable);
        assertEquals("", state);
    }

    @Test
    public void removeListener_listenersBeingCalled_listenerStillCalled() {
        InvalidationListener listener = observable -> state += "a";
        InvalidationListener otherListener = observable -> listenerList.removeListener(listener);
        listenerList.addListener(otherListener);
        listenerList.addListener(listener);
        listenerList.callListeners(testObservable);
        assertEquals("a", state);
    }
}
