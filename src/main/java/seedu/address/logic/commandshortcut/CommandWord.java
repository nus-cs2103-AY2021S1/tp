package seedu.address.logic.commandshortcut;

import java.util.Optional;
import java.util.stream.Stream;

public enum CommandWord {
    ADD_APPOINTMENT("add_appt"),
    ADD_CLIENT("add_client"),
    ADD_HAIRDRESSER("add_hairdresser"),
    EDIT_APPOINTMENT("edit_appt"),
    EDIT_CLIENT("edit_client"),
    EDIT_HAIRDRESSER("edit_hairdresser"),
    DELETE_APPOINTMENT("delete_appt"),
    DELETE_CLIENT("delete_client"),
    DELETE_HAIRDRESSER("delete_hairdresser"),
    LIST_APPOINTMENT("list_appt"),
    LIST_CLIENT("list_client"),
    LIST_HAIRDRESSER("list_hairdresser"),
    FILTER_HAIRDRESSER("filter_hairdresser"),
    FILTER_APPT("filter_appt"),
    FILTER_CLIENT("filter_client"),
    CLEAR("clear"),
    EXIT("exit"),
    HELP("help"),
    ADD_SHORTCUT("add_shortcut"),
    DELETE_SHORTCUT("delete_shortcut"),
    LIST_SHORTCUT("list_shortcut"),
    PRINT("print");

    private String defaultWord;

    CommandWord(String defaultCommand) {
        this.defaultWord = defaultCommand;
    }

    public String getDefaultWord() {
        return this.defaultWord;
    }


    @Override
    public String toString() {
        return defaultWord;
    }

    public static CommandWord getCommandWord(String word) {
        Optional<CommandWord> opt = Stream.of(CommandWord.values())
                .filter(commandWord -> commandWord.getDefaultWord().equals(word))
                .findFirst();
        if (opt.isEmpty()) {
            return null;
        } else {
            return opt.get();
        }

    }
}
