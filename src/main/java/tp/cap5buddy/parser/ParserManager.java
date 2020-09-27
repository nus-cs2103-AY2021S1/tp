package tp.cap5buddy.parser;

public class ParserManager {
    private String current_Input;
    public ParserManager() {
        this.current_Input = null;
    }

    public String parse(String input) {
        this.current_Input = input;
        return this.current_Input;
    }
}
