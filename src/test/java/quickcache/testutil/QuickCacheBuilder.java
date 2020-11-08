package quickcache.testutil;

import quickcache.model.QuickCache;
import quickcache.model.flashcard.Flashcard;

/**
 * A utility class to help with building QuickCache objects.
 * Example usage: <br>
 *     {@code QuickCache ab = new QuickCacheBuilder().withFlashcard(RANDOM1).build();}
 */
public class QuickCacheBuilder {

    private QuickCache quickCache;

    public QuickCacheBuilder() {
        quickCache = new QuickCache();
    }

    public QuickCacheBuilder(QuickCache quickCache) {
        this.quickCache = quickCache;
    }

    /**
     * Adds a new {@code Flashcard} to the {@code quickCache} that we are building.
     */
    public QuickCacheBuilder withFlashcard(Flashcard flashcard) {
        quickCache.addFlashcard(flashcard);
        return this;
    }

    public QuickCache build() {
        return quickCache;
    }
}
