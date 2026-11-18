package dev.apotheosis_bookshelf.client;

import dev.apotheosis_bookshelf.ApotheosisBookshelfMod;
import dev.apotheosis_bookshelf.registry.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Client-side screen registration.
 */
@Mod.EventBusSubscriber(modid = ApotheosisBookshelfMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() ->
                MenuScreens.register(ModMenus.BOOKSHELF_CONFIG_MENU.get(), BookshelfConfigScreen::new));
    }
}
