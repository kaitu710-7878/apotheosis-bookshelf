package dev.apotheosis_bookshelf.block;

import dev.apotheosis_bookshelf.ModConfig;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Shared utility methods for bookshelf blocks.
 */
public final class BookshelfUtils {
    private BookshelfUtils() {
    }

    /**
     * Returns all registered curse enchantments if curse prevention is enabled in config,
     * otherwise returns an empty set.
     */
    public static Set<Enchantment> getCurseBlacklist() {
        if (!ModConfig.COMMON.disableCurseOnShelves.get()) {
            return Collections.emptySet();
        }
        Set<Enchantment> curses = new HashSet<>();
        for (Enchantment ench : ForgeRegistries.ENCHANTMENTS.getValues()) {
            if (ench.isCurse()) {
                curses.add(ench);
            }
        }
        return curses;
    }
}
