package tp.cap5buddy.commands;

public class ResultCommand extends Command {
    private String result;

    public ResultCommand(String result) {
        this.result = result;
    }

    public String getMessage() {
        return this.result;
    }
}
