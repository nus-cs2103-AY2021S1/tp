package seedu.bookmark.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.history.State;

public class ViewTypeManagerTest {

    private ViewTypeManager viewTypeManager = new ViewTypeManager();
    private StateStub testState = new StateStub();

    @Test
    public void setView_mostRecentlyUsed_noChange() {
        ViewType initialView = viewTypeManager.getCurrentViewType();
        viewTypeManager.setCurrentView(ViewType.MOST_RECENTLY_USED);
        assertEquals(initialView, viewTypeManager.getCurrentViewType());
    }

    @Test
    public void setView_notMostRecentlyUsed_success() {
        viewTypeManager.setCurrentView(ViewType.DEFAULT);
        assertEquals(viewTypeManager.getCurrentViewType(), ViewType.DEFAULT);

        viewTypeManager.setCurrentView(ViewType.DETAILED);
        assertEquals(ViewType.DETAILED, viewTypeManager.getCurrentViewType());
    }

    @Test
    public void setViewTypePairing_mostRecentlyUsed_usedCurrentView() {
        ViewType currentView = viewTypeManager.getCurrentViewType();
        viewTypeManager.addViewTypePairing(testState, ViewType.MOST_RECENTLY_USED);
        assertEquals(currentView, viewTypeManager.getViewType(testState));
    }

    @Test
    public void setViewTypePairing_notMostRecentlyUsed_usedProvidedView() {
        viewTypeManager.addViewTypePairing(testState, ViewType.DEFAULT);
        assertEquals(ViewType.DEFAULT, viewTypeManager.getViewType(testState));

        viewTypeManager.addViewTypePairing(testState, ViewType.DETAILED);
        assertEquals(ViewType.DETAILED, viewTypeManager.getViewType(testState));
    }

    @Test
    public void getViewType_notPresent_defaultView() {
        StateStub newState = new StateStub();
        assertEquals(ViewType.DEFAULT, viewTypeManager.getViewType(newState));
    }

    static class StateStub extends State {

        public StateStub() {
            super(null, null, null);
        }

        @Override
        public boolean equals(Object other) {
            return this == other;
        }
    }
}


