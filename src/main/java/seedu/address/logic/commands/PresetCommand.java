package seedu.address.logic.commands;

public abstract class PresetCommand extends Command {

    public static final String COMMAND_WORD = "preset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves or Loads a preset of the user's supper order.\n"
            + "Format: preset MODE [NAME]\n"
            + "- MODE dictates what the system will perform for the user's supper orders,"
                + " represented by the formats:\n"
            + "     save: Used to save a preset."
                    + " (If used without a NAME, will save with a default preset name of 'MyPreset')\n"
            + "     load: Used to load a preset."
                    + " (If used without a NAME, will list all saved presets)\n"
            + "- NAME is the preset name which the system will save the preset as,"
                + " or tries to load the given preset by the given name.\n"
            + "     - if NAME already exists, the new preset will overwrite the existing preset.\n"
            + "     - NAME is Case-Sensitive.\n"
            + "     - NAME is Vendor Specific, and is unique to each vendor.\n"
            + "Examples:\n"
            + "preset save: saves the user's supper order with the default preset name.\n"
            + "preset load MyPreset: loads the current default preset if it exists.\n"
            + "preset save vegan: save the user's supper order with a preset name of 'vegan'.\n"
            + "preset load vegan: loads the preset supper order with the preset name 'vegan'.";

    public static final String MESSAGE_DISPLAY_ALL_PRESETS = "Here are your list of saved presets!\n";
}
