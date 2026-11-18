package dev.apotheosis_bookshelf.block;

/**
 * Represents the Apotheosis enchanting stat controlled by a configurable bookshelf.
 */
public enum BookshelfStatType {
    ETERNA("eterna", 50),
    QUANTA("quanta", 100),
    ARCANA("arcana", 100);

    private final String id;
    private final int maxValue;

    BookshelfStatType(String id, int maxValue) {
        this.id = id;
        this.maxValue = maxValue;
    }

    public String getId() {
        return id;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getTranslationKey() {
        return "stat.apotheosis_bookshelf." + id;
    }
}
