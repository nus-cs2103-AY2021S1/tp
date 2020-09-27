package tp.cap5buddy.parser;

import java.util.Scanner;

public class ParserManager {
    private String current_Input;
    private String command;
    public ParserManager() {
        this.current_Input = null;
        this.command = null;
    }

    public String parse(String input) {
        this.current_Input = input;
        getCommand();
        return this.command;
    }

    public void getCommand() {
        String command = "";
        Scanner sc = new Scanner(this.current_Input);
        while (sc.hasNext()) {
            String now = sc.next();
            if (Prefix.isPrefix(now)) {
                break;
            } else {
                command += now;
            }
        }
        this.command = command;
    }
}
