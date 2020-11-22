package nustorage.ui.uilogic;

import nustorage.logic.commands.CommandResult;

public interface UiLogic {
    CommandResult execute(String commandResult) throws Exception;
}
