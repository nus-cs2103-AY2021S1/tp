package seedu.resireg.ui;

/**
 * Represents a tab containing a {@code piechartPanel}. The tab may contain other UI elements.
 */
interface PiechartsTab extends TabContainer {
    void setPiechartPanel(PiechartPanel piechartPanel);
}
