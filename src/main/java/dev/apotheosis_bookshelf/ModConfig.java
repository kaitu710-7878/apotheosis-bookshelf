package dev.apotheosis_bookshelf;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Common configuration for Apotheosis Bookshelf.
 */
public class ModConfig {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        public final ForgeConfigSpec.BooleanValue disableCurseOnShelves;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("bookshelves");
            disableCurseOnShelves = builder
                    .comment("If true, Treasure Bookshelf and Anvil Bookshelf prevent curse enchantments from being applied on the Enchanting Table.")
                    .define("disableCurseOnShelves", true);
            builder.pop();
        }
    }
}
