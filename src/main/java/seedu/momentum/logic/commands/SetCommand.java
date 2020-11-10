//@@author khoodehui

package seedu.momentum.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.momentum.logic.parser.CliSyntax.SET_STATISTIC_TIMEFRAME;
import static seedu.momentum.logic.parser.CliSyntax.SET_THEME;

import java.util.Optional;

import seedu.momentum.commons.core.GuiThemeSettings;
import seedu.momentum.commons.core.StatisticTimeframe;
import seedu.momentum.commons.core.StatisticTimeframeSettings;
import seedu.momentum.commons.core.Theme;
import seedu.momentum.commons.util.CollectionUtil;
import seedu.momentum.logic.SettingsUpdateManager;
import seedu.momentum.model.Model;

/**
 * Adjusts various settings in the application.
 */
public class SetCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
        + "[" + SET_THEME + "THEME]"
        + "[" + SET_STATISTIC_TIMEFRAME + "TIMEFRAME]";

    public static final String MESSAGE_UPDATE_SETTINGS_SUCCESS = "Settings updated.";
    public static final String MESSAGE_NOT_CHANGED = "At least one setting must be changed.";

    private final SettingsToChange settingsToChange;

    /**
     * Creates a SetCommand that changes application settings.
     *
     * @param settingsToChange New setting to change to.
     */
    public SetCommand(SettingsToChange settingsToChange) {
        requireNonNull(settingsToChange);
        this.settingsToChange = new SettingsToChange(settingsToChange);
    }

    /**
     * Changes the settings in the application.
     *
     * @param model {@code Model} to perform the change on.
     * @return Feedback message of the change result, for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (settingsToChange.getTheme().isPresent()) {
            Theme newTheme = settingsToChange.getTheme().get();
            model.setGuiThemeSettings(new GuiThemeSettings(newTheme));
            SettingsUpdateManager.updateTheme(newTheme);
        }

        if (settingsToChange.getStatTimeframe().isPresent()) {
            StatisticTimeframe newTimeframe = settingsToChange.getStatTimeframe().get();
            model.setStatisticTimeframeSettings(new StatisticTimeframeSettings(newTimeframe));
            SettingsUpdateManager.updateStatisticTimeframe(newTimeframe);
        }

        model.commitToHistory();
        return new CommandResult(MESSAGE_UPDATE_SETTINGS_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetCommand)) {
            return false;
        }

        // state check
        SetCommand o = (SetCommand) other;
        return settingsToChange.equals(o.settingsToChange);
    }

    /**
     * Stores the details of the settings to be changed.
     */
    public static class SettingsToChange {

        private Theme theme;
        private StatisticTimeframe statTimeframe;

        public SettingsToChange() {
        }

        /**
         * Copy constructor.
         *
         * @param toCopy Setting to copy.
         */
        public SettingsToChange(SettingsToChange toCopy) {
            setTheme(toCopy.theme);
            setStatTimeframe(toCopy.statTimeframe);
        }

        public boolean isAnySettingChanged() {
            return CollectionUtil.isAnyNonNull(theme, statTimeframe);
        }

        public void setTheme(Theme theme) {
            this.theme = theme;
        }

        public Optional<Theme> getTheme() {
            return Optional.ofNullable(theme);
        }

        public void setStatTimeframe(StatisticTimeframe statTimeframe) {
            this.statTimeframe = statTimeframe;
        }

        public Optional<StatisticTimeframe> getStatTimeframe() {
            return Optional.ofNullable(statTimeframe);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SettingsToChange)) {
                return false;
            }

            // state check
            SettingsToChange o = (SettingsToChange) other;
            return getTheme().equals(o.getTheme())
                && getStatTimeframe().equals(o.getStatTimeframe());
        }
    }
}
