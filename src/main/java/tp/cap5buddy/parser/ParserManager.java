package tp.cap5buddy.parser;

/**
 * Represents the manager that handles all parser related actions and requests.
 */
public class ParserManager {
    private String currentInput;

    /**
     * Represents the constructor that creates the manager object.
     */
    public ParserManager() {
        this.currentInput = null;
    }

    /**
     * Returns the String of the result after parsing the input.
     * @param input user input.
     * @return String result message.
     */
    public String parse(String input) {
        this.currentInput = input;
        return this.currentInput;
    }
}
