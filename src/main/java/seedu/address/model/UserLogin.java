package seedu.address.model;

import static java.util.Objects.requireNonNull;

public class UserLogin implements ReadOnlyUserLogin {
    private String username = null;
    private String password = null;
    /**
     * Creates a {@code UserLogin} with default values.
     */
    public UserLogin() {}
    /**
     * Creates a {@code UserLogin} with the prefs in {@code userLogin}.
     */
    public UserLogin(ReadOnlyUserLogin userLogin) {
        this();
        resetData(userLogin);
    }
    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserLogin newUserLogin) {
        requireNonNull(newUserLogin);
        setUsername(newUserLogin.getUsername());
        setPassword(newUserLogin.getUserPassword());
    }
    public String getUsername() {
        return username;
    }
    public String getUserPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
