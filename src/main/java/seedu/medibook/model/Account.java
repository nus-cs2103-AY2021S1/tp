package seedu.medibook.model;

import seedu.medibook.model.doctor.Doctor;

public class Account {

    public static final String MESSAGE_USERNAME_CONSTRAINTS = "Username has to be at least 5 characters";
    public static final String MESSAGE_PASSWORD_CONSTRAINTS = "Password has to be at least 5 characters";

    private String username;
    private String password;
    private Doctor doctor;

    /**
     * Constructs an account with a username and a password.
     */
    public Account(String username, String password, Doctor doctor) {
        this.username = username;
        this.password = password;
        this.doctor = doctor;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    /**
     * Checks if two accounts are equal.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Account)) {
            return false;
        }

        Account otherAccount = (Account) other;
        return otherAccount.username.equals(this.username)
                && otherAccount.password.equals(this.password)
                && otherAccount.doctor.equals(this.doctor);
    }

    public boolean check(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
