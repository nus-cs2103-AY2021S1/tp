package seedu.resireg.ui;

import seedu.resireg.ui.room.RoomListPanel;

/**
 * Represents a tab containing a {@code RoomListPanel}. The tab may contain other UI elements.
 */
interface RoomsTab extends TabContainer {
    void setRoomListPanel(RoomListPanel roomListPanel);
}
