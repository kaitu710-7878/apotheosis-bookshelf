package dev.apotheosis_bookshelf.block;

/**
 * Represents the Apotheosis enchanting stat controlled by a configurable bookshelf.
 */
public enum BookshelfStatType {
    ETERNA("eterna"),
    QUANTA("quanta"),
    ARCANA("arcana");

    private final String id;

    BookshelfStatType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTranslationKey() {
        return "stat.apotheosis_bookshelf." + id;
    }
}
