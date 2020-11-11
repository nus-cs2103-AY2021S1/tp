package seedu.bookmark.model.wordstore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

public class WordComparatorTest {
    private static final Word APPLE = new Word("Apple");
    private static final Word BANANA = new Word("Banana");
    private static final Word CHERRY = new Word("Cherry");
    private static final Word DURIAN = new Word("Durian");
    private static final Word ELDERBERRY = new Word("Elderberry");
    private static final Word FOO = new Word("Foo", 1);
    private static final Word BOO = new Word("Boo", 2);
    private static final Word POO = new Word("Poo", 3);
    private static final PriorityQueue<Word> differentDistancePQ = new PriorityQueue<>(new WordComparator());
    private static final PriorityQueue<Word> differentWordsPQ = new PriorityQueue<>(new WordComparator());

    @Test
    public void compareWord_byDistance() {
        differentDistancePQ.add(FOO);
        differentDistancePQ.add(BOO);
        differentDistancePQ.add(POO);

        Word word = differentDistancePQ.poll();
        assertEquals(word, FOO);
        word = differentDistancePQ.poll();
        assertEquals(word, BOO);
        word = differentDistancePQ.poll();
        assertEquals(word, POO);
    }

    @Test
    public void compareWord_byWord() {
        differentWordsPQ.add(CHERRY);
        differentWordsPQ.add(DURIAN);
        differentWordsPQ.add(APPLE);
        differentWordsPQ.add(BANANA);
        differentWordsPQ.add(ELDERBERRY);

        Word word = differentWordsPQ.poll();
        assertEquals(word, APPLE);
        word = differentWordsPQ.poll();
        assertEquals(word, BANANA);
        word = differentWordsPQ.poll();
        assertEquals(word, CHERRY);
        word = differentWordsPQ.poll();
        assertEquals(word, DURIAN);
        word = differentWordsPQ.poll();
        assertEquals(word, ELDERBERRY);
    }

}
