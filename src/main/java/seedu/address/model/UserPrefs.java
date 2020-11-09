package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path propertyBookFilePath = Paths.get("data", "propertybook.json");
    private Path bidBookFilePath = Paths.get("data" , "bidbook.json");
    private Path bidderAddressBookFilePath = Paths.get("data" , "bidderaddressbook.json");
    private Path sellerAddressBookFilePath = Paths.get("data" , "selleraddressbook.json");
    private Path meetingBookFilePath = Paths.get("data" , "meetingbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setPropertyBookFilePath(newUserPrefs.getPropertyBookFilePath());
        setBidBookFilePath(newUserPrefs.getBidBookFilePath());
        setBidderAddressBookFilePath(newUserPrefs.getBidderAddressBookFilePath());
        setSellerAddressBookFilePath(newUserPrefs.getSellerAddressBookFilePath());
        setMeetingBookFilePath(newUserPrefs.getMeetingBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public Path getBidBookFilePath() {
        return bidBookFilePath;
    }

    public Path getBidderAddressBookFilePath() {
        return bidderAddressBookFilePath;
    }

    public Path getSellerAddressBookFilePath() {
        return sellerAddressBookFilePath;
    }

    public Path getMeetingBookFilePath() {
        return meetingBookFilePath;
    }

    public Path getPropertyBookFilePath() {
        return propertyBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    // ========================= bid =============================
    public void setBidBookFilePath(Path bidBookFilePath) {
        requireNonNull(bidBookFilePath);
        this.bidBookFilePath = bidBookFilePath;
    }

    // ========================= bidder =============================
    public void setBidderAddressBookFilePath(Path bidderAddressBookFilePath) {
        requireNonNull(bidderAddressBookFilePath);
        this.bidderAddressBookFilePath = bidderAddressBookFilePath;
    }

    // ========================= seller =============================
    public void setSellerAddressBookFilePath(Path sellerAddressBookFilePath) {
        requireNonNull(sellerAddressBookFilePath);
        this.sellerAddressBookFilePath = sellerAddressBookFilePath;
    }

    // ========================= property =============================
    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        this.propertyBookFilePath = propertyBookFilePath;
    }

    // ========================= meeting ==============================
    public void setMeetingBookFilePath(Path meetingBookFilePath) {
        requireNonNull(meetingBookFilePath);
        this.meetingBookFilePath = meetingBookFilePath;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && bidderAddressBookFilePath.equals(o.bidderAddressBookFilePath)
                && sellerAddressBookFilePath.equals(o.sellerAddressBookFilePath)
                && propertyBookFilePath.equals(o.propertyBookFilePath)
                && bidBookFilePath.equals(o.bidBookFilePath)
                && meetingBookFilePath.equals(o.meetingBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal addressBook data file location : " + addressBookFilePath);
        sb.append("\nLocal property data file location: " + propertyBookFilePath);
        sb.append("\nLocal bidBook data file location : " + bidBookFilePath);
        sb.append("\nLocal Bidder data file location : " + bidderAddressBookFilePath);
        sb.append("\nLocal Seller data file location : " + sellerAddressBookFilePath);
        sb.append("\nLocal Meeting data file location : " + meetingBookFilePath);
        return sb.toString();
    }

}
