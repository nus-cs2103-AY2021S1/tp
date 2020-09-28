package tp.cap5buddy.parser;

import java.util.Scanner;

/**
 * Represents the token of each user input.
 */
public class Tokenizer {

    private Prefix[] userCommand = new Prefix[2];
    private String input;

    /**
     * Constructs the tokenising object.
     * @param input
     */
    public Tokenizer(String input) {
        this.input = input;
    }

    private void breakDown() {
        Scanner sc = new Scanner(this.input);

    }
}
