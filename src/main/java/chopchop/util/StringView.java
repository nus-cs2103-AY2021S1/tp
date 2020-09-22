// StringView.java

package chopchop.util;

import java.util.Arrays;
import java.util.Collections;
import java.nio.charset.Charset;
import java.util.function.Predicate;

public class StringView {

    private char[] chars;
    private int begin;
    private int end;

    public StringView(String string) {
        this.chars = string.toCharArray();
        this.begin = 0;
        this.end   = this.chars.length;
    }

    private StringView(char[] chars, int begin, int end) {
        this.chars = chars;
        this.begin = Math.max(begin, 0);
        this.end   = Math.min(end, this.chars.length);

        assert this.begin <= this.end;
    }

    private void set(char[] chars, int begin, int end) {
        this.chars = chars;
        this.begin = begin;
        this.end   = end;
    }

    private void replaceWith(StringView other) {
        this.chars = other.chars;
        this.begin = other.begin;
        this.end   = other.end;
    }


    public int size() {
        return this.end - this.begin;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int find(String thing) {
        return this.find(new StringView(thing));
    }

    public int find(StringView thing) {
        return Collections.indexOfSubList(Arrays.asList(this.chars), Arrays.asList(thing.chars));
    }

    public int find(char b) {
        for (int i = this.begin; i < this.end; i++) {
            if (this.chars[i] == b) {
                return i - this.begin;
            }
        }

        return -1;
    }

    public boolean startsWith(String thing) {
        return this.find(thing) == 0;
    }

    public boolean startsWith(StringView thing) {
        return this.find(thing) == 0;
    }

    public Pair<StringView, StringView> bisect(char b) {
        var fst = this.take(this.find(b));
        var snd = this.drop(fst.size() + 1).dropWhile(x -> x == b);

        return new Pair<>(fst, snd);
    }

    public void bisect(StringView x, char b, StringView xs) {
        var fst = this.take(this.find(b));
        var snd = this.drop(fst.size() + 1).dropWhile(c -> c == b);

        x.replaceWith(fst);
        xs.replaceWith(snd);
    }


    public char at(int idx) {
        return this.chars[this.begin + idx];
    }

    public StringView drop(int n) {
        assert n >= 0;
        return new StringView(this.chars, Math.min(this.begin + n, this.end), this.end);
    }

    public StringView take(int n) {
        if (n < 0) {
            n = this.size();
        }

        return new StringView(this.chars, this.begin, Math.min(this.begin + n, this.end));
    }

    public StringView takeLast(int n) {
        assert n >= 0;
        return new StringView(this.chars, Math.min(this.begin + n, this.end), this.end);
    }

    public StringView dropWhile(Predicate<Character> pred) {
        int i = this.begin;
        while (i < this.end && pred.test(this.chars[i])) {
            i += 1;
        }

        return new StringView(this.chars, i, this.end);
    }

    public StringView takeWhile(Predicate<Character> pred) {
        int n = this.begin;
        while (n < this.end && pred.test(this.chars[n])) {
            n += 1;
        }

        return new StringView(this.chars, this.begin, n);
    }


    @Override
    public String toString() {
        return new String(this.chars, this.begin, this.size());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StringView)) {
            return false;
        }

        var other = (StringView) obj;
        return Arrays.equals(this.chars, this.begin, this.end, other.chars, other.begin, other.end);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
