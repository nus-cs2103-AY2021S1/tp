package tp.cap5buddy.logic;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.ResultCommand;
import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.logic.parser.ParserManager;
import tp.cap5buddy.logic.parser.exception.ParseException;

import java.util.ArrayList;

/**
 * The brain of the program, handles parsing and commands.
 */
public class LogicManager implements Logic {

    private ModuleList modlist;
    private ParserManager pm;

    public LogicManager() {
        this.modlist = new ModuleList(new ArrayList<Module>());
        this.pm = new ParserManager();
    }

    @Override
    public ResultCommand execute(String userInput) throws ParseException {
        Command command = pm.parse(userInput);
        ResultCommand result = command.execute(modlist);
        return result;
    }
}
