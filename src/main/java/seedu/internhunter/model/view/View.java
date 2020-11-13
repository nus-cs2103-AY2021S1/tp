package seedu.internhunter.model.view;

import seedu.internhunter.commons.core.index.Index;

/**
 * The API of the Tab component.
 */
public interface View {

    /**
     * Sets the current company view index with {@code index}.
     *
     * @param index The new Index for company view index.
     */
    void setCompanyViewIndex(Index index);

    /**
     * Sets the current application view index with {@code index}.
     *
     * @param index The new Index for application view index.
     */
    void setApplicationViewIndex(Index index);

    /**
     * Sets the current profile view index with {@code index}.
     *
     * @param index The new Index for profile view index.
     */
    void setProfileViewIndex(Index index);

    /**
     * Retrieves the current company view Index.
     *
     * @return the current company view Index.
     */
    Index getCompanyViewIndex();

    /**
     * Retrieves the current application view Index.
     *
     * @return the current application view Index.
     */
    Index getApplicationViewIndex();

    /**
     * Retrieves the current profile view Index.
     *
     * @return the current profile view Index.
     */
    Index getProfileViewIndex();

}
