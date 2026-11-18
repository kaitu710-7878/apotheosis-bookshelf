package dev.apotheosis_bookshelf.registry;

import dev.apotheosis_bookshelf.ApotheosisBookshelfMod;
import dev.apotheosis_bookshelf.block.AnvilBookshelfBlock;
import dev.apotheosis_bookshelf.block.BookshelfStatType;
import dev.apotheosis_bookshelf.block.ConfigurableBookshelfBlock;
import dev.apotheosis_bookshelf.block.TreasureBookshelfBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Block registrations for Apotheosis Bookshelf.
 */
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ApotheosisBookshelfMod.MOD_ID);

    private static BlockBehaviour.Properties bookshelfProps() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(1.5F, 6.0F)
                .sound(SoundType.WOOD);
    }

    public static final RegistryObject<Block> ETERNA_BOOKSHELF = BLOCKS.register("eterna_bookshelf",
            () -> new ConfigurableBookshelfBlock(BookshelfStatType.ETERNA, bookshelfProps()));

    public static final RegistryObject<Block> QUANTA_BOOKSHELF = BLOCKS.register("quanta_bookshelf",
            () -> new ConfigurableBookshelfBlock(BookshelfStatType.QUANTA, bookshelfProps()));

    public static final RegistryObject<Block> ARCANA_BOOKSHELF = BLOCKS.register("arcana_bookshelf",
            () -> new ConfigurableBookshelfBlock(BookshelfStatType.ARCANA, bookshelfProps()));

    public static final RegistryObject<Block> TREASURE_BOOKSHELF = BLOCKS.register("treasure_bookshelf",
            () -> new TreasureBookshelfBlock(bookshelfProps()));

    public static final RegistryObject<Block> ANVIL_BOOKSHELF = BLOCKS.register("anvil_bookshelf",
            () -> new AnvilBookshelfBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3.0F, 12.0F)
                    .sound(SoundType.ANVIL)));
}
