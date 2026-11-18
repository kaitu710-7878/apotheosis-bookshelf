package dev.apotheosis_bookshelf.registry;

import dev.apotheosis_bookshelf.ApotheosisBookshelfMod;
import dev.apotheosis_bookshelf.menu.BookshelfConfigMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Menu registrations for Apotheosis Bookshelf.
 */
public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ApotheosisBookshelfMod.MOD_ID);

    public static final RegistryObject<MenuType<BookshelfConfigMenu>> BOOKSHELF_CONFIG_MENU =
            MENUS.register("bookshelf_config", () -> IForgeMenuType.create(BookshelfConfigMenu::new));
}
