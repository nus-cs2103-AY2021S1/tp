package seedu.address.logic.commandshortcut;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commandshortcut.exceptions.CommandWordException;

public class CommandShortcutSet {

    private static CommandShortcutSet instance;

    private static List<CommandShortcut> commandShortcuts;

    private CommandShortcutSet() {
        this.commandShortcuts = Stream.of(CommandWord.values())
                .map(word -> new CommandShortcut(word)).collect(Collectors.toList());
    }

    public static CommandShortcutSet getInstance() {
        if (instance == null) {
            instance = new CommandShortcutSet();
        }
        return instance;
    }

    public static void reset() {
        instance = new CommandShortcutSet();
    }

    /**
     * Insert a new shortcut
     * @param word The word associated with the shortcut
     * @param shortcut The shortcut to be added
     * @throws CommandWordException if shortcut already exists
     */
    public void insertNewShortcut(CommandWord word, String shortcut) throws CommandWordException {

        if (commandShortcuts.stream().anyMatch(commandShortcut -> commandShortcut.shortcutExist(shortcut))) {
            throw new CommandWordException("Alias already exists!");
        }
        commandShortcuts.stream().filter(commandShortcut -> commandShortcut.checkCommandWord(word))
                .findFirst().get().addShortcut(shortcut);

    }

    /**
     * Insert a new shortcut
     * @param word The associated word
     * @param shortcut The new shortcut
     * @throws CommandWordException if the original command word does not exist
     */
    public void insertNewShortcut(String word, String shortcut) throws CommandWordException {
        CommandWord commandWord = CommandWord.getCommandWord(word);
        if (commandWord == null) {
            throw new CommandWordException("Original Command Word does not exist!");
        }
        insertNewShortcut(commandWord, shortcut);

    }

    public CommandWord getCommandWord(String shortcut) throws CommandWordException {
        Optional<CommandShortcut> command = commandShortcuts.stream()
                .filter(commandShortcut -> commandShortcut.shortcutExist(shortcut))
                .findFirst();
        if (command.isEmpty()) {
            throw new CommandWordException("Command does not exists!");
        }

        return command.get().getCommandWord();
    }

    /**
     * Delete an shortcut
     * @param shortcut Alias to be deleted
     * @throws CommandWordException if the shortcut does not exist
     */
    public void deleteShortcut(String shortcut) throws CommandWordException {
        Optional<CommandShortcut> command = commandShortcuts.stream()
                .filter(commandShortcut -> commandShortcut.shortcutExist(shortcut))
                .findFirst();
        if (command.isEmpty()) {
            throw new CommandWordException("Alias does not exists!");
        }
        command.get().removeShortcut(shortcut);
    }

    public List<CommandShortcut> getListOfShortcuts() {
        return this.commandShortcuts;
    }

    public void setUpAliasSet(List<CommandShortcut> shortcutSet) {
        this.commandShortcuts = Stream.of(CommandWord.values())
                .map(word -> new CommandShortcut(word)).collect(Collectors.toList());
        shortcutSet.stream().forEach(set -> {
            set.getAllShortcuts().stream().forEach(alias -> {
                try {
                    insertNewShortcut(set.getCommandWord(), alias);
                } catch (CommandWordException ex) {
                    System.out.println("skip");
                }
            });
        });
    }

    public void setUpAliasSet(CommandShortcutSet aliasSet) {
        commandShortcuts = aliasSet.getListOfShortcuts();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Showing all shortcuts:\n");
        for (CommandShortcut cmd : commandShortcuts) {
            str.append(cmd.toString());
            str.append("\n");
        }
        return str.toString();
    }
}

