package tp.cap5buddy.logic.parser;

public class Prefix {
    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String toString() {
        return this.prefix;
    }

    /**
     * Checks if the given word contains a prefix.
     *
     * @param word word to check.
     * @return boolean is prefix or not.
     */
    public static boolean isPrefix(String word) {
        String possiblePrefix = "" + word.charAt(0) + word.charAt(1);
        boolean res = false;
        switch (possiblePrefix) {
        case "n/":
        case "l/":
        case "i/":
        case "e/":
        case "v/":
        case "c/":
        case "a/":
        case "r/":
            res = true;
            break;
        case "d/":
            res = true;
            break;
        case "t/":
        case "p/":
            res = true;
            break;
        default:
            res = false;
        }
        return res;
    }
}
