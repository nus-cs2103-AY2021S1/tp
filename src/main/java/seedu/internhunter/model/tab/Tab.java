package seedu.internhunter.model.tab;

import seedu.internhunter.ui.tabs.TabName;

/**
 * The API of the Tab component.
 */
public interface Tab {

    /**
     * Sets the current tab name with {@code tabName}.
     *
     * @param tabName The new TabName to change to.
     */
    void setTabName(TabName tabName);

    /**
     * Retrieves the current tab name.
     *
     * @return the current tab name.
     */
    TabName getTabName();

}
