package seedu.resireg.logic.commands;

public enum CommandWordEnum {
    ADD_COMMAND("add"),
    CLEAR_COMMAND("clear"),
    DELETE_COMMAND("delete"),
    EDIT_COMMAND("edit"),
    EXIT_COMMAND("exit"),
    FIND_COMMAND("find"),
    HELP_COMMAND("help"),
    LIST_COMMAND("students"),
    LIST_ROOM_COMMAND("rooms"),
    ALLOCATE_COMMAND("allocate"),
    DEALLOCATE_COMMAND("deallocate"),
    REALLOCATE_COMMAND("reallocate"),
    REDO_COMMAND("redo"),
    UNDO_COMMAND("undo"),
    ADD_ALIAS_COMMAND("alias"),
    DELETE_ALIAS_COMMAND("dealias"),
    LIST_ALIAS_COMMAND("aliases"),
    TOGGLE_TAB_SPLIT_COMMAND("togglesplit");

    private String commandWord;


    CommandWordEnum(String commandWord) {
        this.commandWord = commandWord;
    }

    public String toString() {
        return commandWord;
    }
}
