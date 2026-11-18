package dev.apotheosis_bookshelf;

import dev.apotheosis_bookshelf.network.ModNetwork;
import dev.apotheosis_bookshelf.registry.ModBlockEntities;
import dev.apotheosis_bookshelf.registry.ModBlocks;
import dev.apotheosis_bookshelf.registry.ModItems;
import dev.apotheosis_bookshelf.registry.ModMenus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Main entrypoint for the Apotheosis Bookshelf mod.
 */
@Mod(ApotheosisBookshelfMod.MOD_ID)
public class ApotheosisBookshelfMod {
    public static final String MOD_ID = "apotheosis_bookshelf";

    public ApotheosisBookshelfMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModItems.CREATIVE_TABS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);

        ModLoadingContext.get().registerConfig(Type.COMMON, ModConfig.COMMON_SPEC);

        modEventBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModNetwork::register);
    }
}
