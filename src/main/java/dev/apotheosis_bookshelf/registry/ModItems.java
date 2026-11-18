package dev.apotheosis_bookshelf.registry;

import dev.apotheosis_bookshelf.ApotheosisBookshelfMod;
import dev.apotheosis_bookshelf.item.GlintItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Item and creative tab registrations for Apotheosis Bookshelf.
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ApotheosisBookshelfMod.MOD_ID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ApotheosisBookshelfMod.MOD_ID);

    public static final RegistryObject<Item> ETERNA_BOOKSHELF = ITEMS.register("eterna_bookshelf",
            () -> new BlockItem(ModBlocks.ETERNA_BOOKSHELF.get(), new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> QUANTA_BOOKSHELF = ITEMS.register("quanta_bookshelf",
            () -> new BlockItem(ModBlocks.QUANTA_BOOKSHELF.get(), new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ARCANA_BOOKSHELF = ITEMS.register("arcana_bookshelf",
            () -> new BlockItem(ModBlocks.ARCANA_BOOKSHELF.get(), new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> TREASURE_BOOKSHELF = ITEMS.register("treasure_bookshelf",
            () -> new BlockItem(ModBlocks.TREASURE_BOOKSHELF.get(), new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ANVIL_BOOKSHELF = ITEMS.register("anvil_bookshelf",
            () -> new BlockItem(ModBlocks.ANVIL_BOOKSHELF.get(), new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> PRIMAL_END_CRYSTAL = ITEMS.register("primal_end_crystal",
            () -> new GlintItem(new Item.Properties().rarity(Rarity.EPIC), "tooltip.apotheosis_bookshelf.primal_end_crystal"));

    public static final RegistryObject<Item> PRIMAL_NETHER_STAR = ITEMS.register("primal_nether_star",
            () -> new GlintItem(new Item.Properties().rarity(Rarity.EPIC), "tooltip.apotheosis_bookshelf.primal_nether_star"));

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.apotheosis_bookshelf.main"))
                    .icon(() -> ETERNA_BOOKSHELF.get().getDefaultInstance())
                    .displayItems((params, output) -> {
                        output.accept(ETERNA_BOOKSHELF.get());
                        output.accept(QUANTA_BOOKSHELF.get());
                        output.accept(ARCANA_BOOKSHELF.get());
                        output.accept(TREASURE_BOOKSHELF.get());
                        output.accept(ANVIL_BOOKSHELF.get());
                        output.accept(PRIMAL_END_CRYSTAL.get());
                        output.accept(PRIMAL_NETHER_STAR.get());
                    })
                    .build());
}
