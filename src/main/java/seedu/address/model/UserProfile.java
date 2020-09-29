package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.UniqueProfileItemList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UserProfile implements ReadOnlyUserProfile {

    private final UniqueProfileItemList profileItems;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        profileItems = new UniqueProfileItemList();
    }

    public UserProfile() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public UserProfile(ReadOnlyUserProfile toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setProfileItems(List<ProfileItem> profileItems) {
        this.profileItems.setProfileItems(profileItems);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyUserProfile newData) {
        requireNonNull(newData);

        setProfileItems(newData.getUserProfileItemList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasProfileItem(ProfileItem profileItem) {
        requireNonNull(profileItem);
        return profileItems.contains(profileItem);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addProfileItem(ProfileItem p) {
        profileItems.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void addProfileItem(ProfileItem target, ProfileItem editedProfileItem) {
        requireNonNull(editedProfileItem);

        profileItems.setProfileItem(target, editedProfileItem);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeProfileItem(ProfileItem key) {
        profileItems.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return profileItems.asUnmodifiableObservableList().size() + " profile items";
        // TODO: refine later
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && profileItems.equals(((UserProfile) other).profileItems));
    }

    @Override
    public int hashCode() {
        return profileItems.hashCode();
    }

    /**
     * Returns an unmodifiable view of the Profile list.
     * This list will not contain any duplicate profile items.
     */
    @Override
    public ObservableList<ProfileItem> getUserProfileItemList() {
        return profileItems.asUnmodifiableObservableList();
    }
}
