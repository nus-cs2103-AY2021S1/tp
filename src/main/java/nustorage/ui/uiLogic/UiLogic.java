package nustorage.ui.uiLogic;

import nustorage.logic.commands.CommandResult;

public interface UiLogic {
    CommandResult execute(String commandResult) throws Exception;
}
