package seedu.jarvis.model;

import seedu.jarvis.model.login.Username;

/**
 * Unmodifiable view of user login details.
 */
public interface ReadOnlyUserLogin {
    Username getUsername();
    String getUserPassword();
}
