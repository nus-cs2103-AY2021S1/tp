package tp.cap5buddy.logic;

import java.util.ArrayList;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.ResultCommand;
import tp.cap5buddy.logic.parser.ParserManager;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;




/**
 * The brain of the program, handles parsing and commands.
 */
public class LogicManager implements Logic {

    private ModuleList modlist;
    private ParserManager pm;

    /**
     * Represents the constructor of the Manager.
     */
    public LogicManager() {
        this.modlist = new ModuleList(new ArrayList<Module>());
        this.pm = new ParserManager();
    }

    /**
     * Returns the result container with all the relevant information.
     * @param userInput user input of user.
     * @return ResultCommand result container.
     * @throws ParseException invalid command.
     */
    @Override
    public ResultCommand execute(String userInput) throws ParseException {
        Command command = pm.parse(userInput);
        ResultCommand result = command.execute(modlist);
        return result;
    }
}
