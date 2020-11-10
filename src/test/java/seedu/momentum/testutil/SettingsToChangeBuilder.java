//@@author khoodehui
package seedu.momentum.testutil;

import seedu.momentum.commons.core.StatisticTimeframe;
import seedu.momentum.commons.core.Theme;
import seedu.momentum.logic.commands.SetCommand;

/**
 * A utility class to help with building SettingsToChange objects.
 */
public class SettingsToChangeBuilder {

    private SetCommand.SettingsToChange settingsToChange;

    public SettingsToChangeBuilder() {
        settingsToChange = new SetCommand.SettingsToChange();
    }

    public SettingsToChangeBuilder(SetCommand.SettingsToChange settingsToChange) {
        this.settingsToChange = new SetCommand.SettingsToChange(settingsToChange);
    }

    /**
     * Sets the {@code Theme} of the {@code SettingsToChangeBuilder} that we are building.
     *
     * @param theme Theme to set to the list of settings.
     */
    public SettingsToChangeBuilder withTheme(String theme) {
        settingsToChange.setTheme(new Theme(theme));
        return this;
    }

    /**
     * Sets the {@code StatisticTimeframe} of the {@code SettingsToChangeBuilder} that we are building.
     *
     * @param timeframe Timeframe to set to the list of settings.
     */
    public SettingsToChangeBuilder withStatisticTimeframe(String timeframe) {
        settingsToChange.setStatTimeframe(new StatisticTimeframe(timeframe));
        return this;
    }

    public SetCommand.SettingsToChange build() {
        return settingsToChange;
    }
}
