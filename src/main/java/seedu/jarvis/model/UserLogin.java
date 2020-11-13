package seedu.jarvis.model;

import static java.util.Objects.requireNonNull;

import seedu.jarvis.model.login.Username;

public class UserLogin implements ReadOnlyUserLogin {
    private Username username = new Username();
    private String password = "";

    /**
     * Creates a {@code UserLogin} with default values.
     */
    public UserLogin() {
    }

    /**
     * Creates a {@code UserLogin} with the prefs in {@code userLogin}.
     */
    public UserLogin(ReadOnlyUserLogin userLogin) {
        this();
        resetData(userLogin);
    }

    /**
     * Creates a {@code UserLogin} with username and password.
     * @param username
     * @param password
     */
    public UserLogin(Username username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserLogin newUserLogin) {
        requireNonNull(newUserLogin);
        setUsername(newUserLogin.getUsername());
        setPassword(newUserLogin.getUserPassword());
    }
    public Username getUsername() {
        return username;
    }

    public String getUserPassword() {
        return password;
    }

    public boolean hasUsername() {
        return !username.isEmpty();
    }

    public boolean hasPassword() {
        return !password.isEmpty();
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmpty() {
        return this.username.isEmpty() || this.password.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserLogin // instanceof handles nulls
                && username.equals(((UserLogin) other).getUsername())
                && password.equals(((UserLogin) other).getUserPassword())); // state check
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Username: ")
                .append(getUsername())
                .append(" Password: ")
                .append(getUserPassword());
        return builder.toString();
    }
}
