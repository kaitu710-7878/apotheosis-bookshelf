package dev.apotheosis_bookshelf.registry;

import dev.apotheosis_bookshelf.ApotheosisBookshelfMod;
import dev.apotheosis_bookshelf.block.ConfigurableBookshelfBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * BlockEntity registrations for Apotheosis Bookshelf.
 */
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ApotheosisBookshelfMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ConfigurableBookshelfBlockEntity>> CONFIGURABLE_BOOKSHELF =
            BLOCK_ENTITIES.register("configurable_bookshelf",
                    () -> BlockEntityType.Builder.of(
                            ConfigurableBookshelfBlockEntity::new,
                            ModBlocks.ETERNA_BOOKSHELF.get(),
                            ModBlocks.QUANTA_BOOKSHELF.get(),
                            ModBlocks.ARCANA_BOOKSHELF.get()
                    ).build(null));
}
