package seedu.address.model.profile;

import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;

public class Profile {
    private Address address;
    private Phone phone;

    /**
     * Profile constructor
     * @param address address of profile as String
     * @param phone phone number of profile as String
     */
    public Profile(Phone phone, Address address) {
        this.phone = phone;
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }

    public Phone getPhone() {
        return this.phone;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Profile)) {
            return false;
        }

        Profile otherPreset = (Profile) other;
        return otherPreset.getAddress().equals(getAddress())
                && otherPreset.getPhone().equals(getPhone());
    }
}
