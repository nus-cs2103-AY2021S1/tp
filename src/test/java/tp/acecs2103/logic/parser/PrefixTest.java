package tp.acecs2103.logic.parser;

import org.junit.jupiter.api.Test;

public class PrefixTest {
    @Test
    public void prefix_getPrefix_success() {
        Prefix prefix = new Prefix("1234567890abcdefg");
        assert prefix.getPrefix().equals("1234567890abcdefg");
    }

    @Test
    public void prefix_hashCode_success() {
        Prefix prefix = new Prefix("1234567890abcdefg");
        Prefix nullPrefix = new Prefix(null);
        assert prefix.hashCode() == "1234567890abcdefg".hashCode() && nullPrefix.hashCode() == 0;
    }

    @Test
    public void prefix_equals_success() {
        Prefix prefixA = new Prefix("123");
        Prefix prefixB = new Prefix(null);
        Prefix prefixC = new Prefix("abc");
        Prefix prefixD = new Prefix("123");
        assert !prefixA.equals(prefixB) && prefixA.equals(prefixD) && !prefixA.equals(prefixC);
    }
}
