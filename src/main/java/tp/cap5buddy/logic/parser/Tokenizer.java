package tp.cap5buddy.logic.parser;


/**
 * Represents the token of each user input.
 */
public class Tokenizer {
    private static final int SIZE = 4; // updates as the number of prefixes increases.
    private String[] words = new String[SIZE];
    private String input;

    /**
     * Constructs the tokenising object.
     * @param input
     */
    public Tokenizer(String input) {
        this.input = input;
        breakDown();
    }

    private void breakDown() {
        String[] temp = this.input.split(" ");
        for (int i = 0; i < temp.length; i++) {
            String word = temp[i];
            String prefix = word.substring(0, 2); // to extract the prefix
            if (Prefix.isPrefix(word)) {
                word = word.substring(2); // to remove the prefix from the actual word
            }
            if (prefix.equals(PrefixList.MODULE_NAME_PREFIX.toString())) {
                this.words[0] = word;
            } else if (prefix.equals(PrefixList.MODULE_LINK_PREFIX.toString())) {
                this.words[1] = word;
            } else if (prefix.equals(PrefixList.MODULE_NEWNAME_PREFIX.toString())) {
                this.words[2] = word;
            } else if (prefix.equals(PrefixList.MODULE_VIEW_PREFIX.toString())) {
                this.words[3] = word;
            } else {
                // throws error as invalid prefix is found
            }
        }
    }

    public String[] getWords() {
        return this.words;
    }
}
