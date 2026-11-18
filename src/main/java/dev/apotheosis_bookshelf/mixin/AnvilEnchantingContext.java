package dev.apotheosis_bookshelf.mixin;

/**
 * ThreadLocal context tracking whether the Enchanting Table is currently operating
 * with an Anvil Bookshelf in range.
 */
public final class AnvilEnchantingContext {
    public static final ThreadLocal<Boolean> ANVIL_MODE = ThreadLocal.withInitial(() -> Boolean.FALSE);

    private AnvilEnchantingContext() {
    }
}
