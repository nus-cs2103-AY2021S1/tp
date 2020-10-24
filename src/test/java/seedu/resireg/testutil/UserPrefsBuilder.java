package seedu.resireg.testutil;

import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * A utility class to help with building UserPrefs objects.
 * Example usage: <br>
 *     {@code UserPrefs up = new UserPrefsBuilder().withCommandWordAlias([object]).build();}
 */
public class UserPrefsBuilder {

    private UserPrefs userPrefs;

    public UserPrefsBuilder() {
        userPrefs = new UserPrefs();
    }

    public UserPrefsBuilder(UserPrefs userPrefs) {
        this.userPrefs = userPrefs;
    }

    /**
     * Adds a new {@code CommandWordAlias} to the {@code UserPrefs} that we are building.
     */
    public UserPrefsBuilder withCommandWordAlias(CommandWordAlias commandWordAlias) {
        userPrefs.addAlias(commandWordAlias);
        return this;
    }

    public UserPrefs build() {
        return userPrefs;
    }
}
