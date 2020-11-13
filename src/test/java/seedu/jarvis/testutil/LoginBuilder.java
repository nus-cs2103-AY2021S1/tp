package seedu.jarvis.testutil;

import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.login.Username;

public class LoginBuilder {
    public static final String DEFAULT_USERNAME = "nusstu\\e1234568";
    public static final String DEFAULT_PASSWORD = "example132";

    private Username username;
    private String password;

    /**
     * Creates a {@code LoginBuilder} with the default details.
     */
    public LoginBuilder() {
        username = new Username(DEFAULT_USERNAME);
        password = DEFAULT_PASSWORD;
    }

    /**
     * Initializes the LoginBuilder with the data of {@code loginToCopy}.
     */
    public LoginBuilder(UserLogin userLogin) {
        username = userLogin.getUsername();
        password = userLogin.getUserPassword();
    }

    /**
     * Sets the {@code Username} of the {@code UserLogin} that we are building.
     */
    public LoginBuilder withUsername(String username) {
        this.username = new Username(username);
        return this;
    }

    /**
     * Sets the {@code Password} of the {@code UserLogin} that we are building.
     */
    public LoginBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserLogin build() {
        return new UserLogin(username, password);
    }
}
