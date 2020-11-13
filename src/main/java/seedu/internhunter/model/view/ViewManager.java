package seedu.internhunter.model.view;

import static java.util.Objects.requireNonNull;

import seedu.internhunter.commons.core.index.Index;

/**
 * Represents View Index Control of InternHunter.
 */
public class ViewManager implements View {

    private Index companyIndex;
    private Index applicationIndex;
    private Index profileIndex;

    /**
     * Initializes ViewManger which controls the current {@code index}. By default, it is always of Index 1.
     */
    public ViewManager() {
        companyIndex = Index.fromOneBased(1); // default Index is 1
        applicationIndex = Index.fromOneBased(1); // default Index is 1
        profileIndex = Index.fromOneBased(1); // default Index is 1
    }

    /**
     * {@inheritDoc}
     */
    public void setCompanyViewIndex(Index index) {
        requireNonNull(index);
        companyIndex = index;
    }

    /**
     * {@inheritDoc}
     */
    public void setApplicationViewIndex(Index index) {
        requireNonNull(index);
        applicationIndex = index;
    }

    /**
     * {@inheritDoc}
     */
    public void setProfileViewIndex(Index index) {
        requireNonNull(index);
        profileIndex = index;
    }

    /**
     * {@inheritDoc}
     */
    public Index getCompanyViewIndex() {
        return companyIndex;
    }

    /**
     * {@inheritDoc}
     */
    public Index getApplicationViewIndex() {
        return applicationIndex;
    }

    /**
     * {@inheritDoc}
     */
    public Index getProfileViewIndex() {
        return profileIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ViewManager)) {
            return false;
        }

        ViewManager other = (ViewManager) obj;
        return companyIndex.equals(other.companyIndex)
                && applicationIndex.equals(other.applicationIndex)
                && profileIndex.equals(other.profileIndex);
    }
}
