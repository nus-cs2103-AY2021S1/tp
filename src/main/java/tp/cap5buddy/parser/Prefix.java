package tp.cap5buddy.parser;

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

    public static boolean isPrefix(String word) {
        String possiblePrefix = "" + word.charAt(0) + word.charAt(1);
        boolean res = false;
        switch (possiblePrefix) {
            case "n/":
                res = true;
                break;
            case "l/":
                res = true;
                break;

            default:
                res = false;
        }
        return res;
    }
}
