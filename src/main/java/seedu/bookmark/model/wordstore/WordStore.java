package seedu.bookmark.model.wordstore;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import seedu.bookmark.model.wordstore.exceptions.WordNotFoundException;

public class WordStore {

    private HashMap<String, Word> wordStore;

    /**
     * Creates a WordStore object
     */
    public WordStore() {
        this.wordStore = new HashMap<>();
    }

    /**
     * Returns true if the list contains an String which matches a word in the wordStore
     */
    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        return wordStore.containsKey(toCheck);
    }

    /**
     * Adds words from a given list into the Store
     * @param words list of words to be added
     */
    public void addWords(List<String> words) {
        for (String word : words) {
            assert word != null;
            wordAdder(word);
        }
    }

    /**
     * Deletes words from a given list out of the Store
     * @param words list of words to be deleted
     */
    public void deleteWords(List<String> words) {
        for (String word : words) {
            wordDeleter(word);
        }
    }

    /**
     * Processes the provided word into wordStore by either creating a new Word or incrementing the count based on
     * whether the word already exists
     * @param targetWord word to be added
     */
    public void wordAdder(String targetWord) {
        requireNonNull(targetWord);
        boolean added = contains(targetWord);
        if (added) {
            Word targetWordObj = wordStore.get(targetWord);
            assert targetWordObj != null;
            targetWordObj.addCount();
        } else {
            Word newWord = new Word(targetWord);
            this.addWord(newWord);
        }
    }

    /**
     * Processes the provided word out of wordStore by either removing the word or minusing the count based on
     * whether the word count > 1
     * If there are no words found, the deletion is ignored
     * @param targetWord word to be deleted
     */
    public void wordDeleter(String targetWord) {
        requireNonNull(targetWord);
        Word targetWordObj = wordStore.get(targetWord);
        if (targetWordObj == null) {
            throw new WordNotFoundException();
        }
        if (targetWordObj.getCount() == 1) { //only got 1 instance which is the deleted book
            this.deleteWord(targetWordObj);
        } else {
            targetWordObj.minusCount();
        }
    }

    /**
     * Returns the wordStore
     * @return wordStore
     */
    public HashMap<String, Word> getWordStore() {
        return this.wordStore;
    }

    /**
     * Returns the Word object
     * @return Word
     */
    public Word getWord(String word) {
        return this.wordStore.get(word);
    }


    private void addWord(Word word) {
        this.wordStore.put(word.getWord(), word);

    }

    private void deleteWord(Word word) {
        this.wordStore.remove(word.getWord(), word);
    }

}
