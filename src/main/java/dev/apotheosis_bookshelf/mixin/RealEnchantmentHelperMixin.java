package dev.apotheosis_bookshelf.mixin;

import dev.shadowsoffire.apotheosis.ench.EnchModule;
import dev.shadowsoffire.apotheosis.ench.EnchantmentInfo;
import dev.shadowsoffire.apotheosis.ench.table.RealEnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Expands available enchantments to all anvil-applicable enchantments when an Anvil Bookshelf is active.
 */
@Mixin(value = RealEnchantmentHelper.class, remap = false)
public class RealEnchantmentHelperMixin {

    @Inject(method = "getAvailableEnchantmentResults", at = @At("HEAD"), cancellable = true)
    private static void apotheosis_bookshelf$getAnvilEnchantmentResults(
            int power,
            ItemStack stack,
            boolean allowTreasure,
            Set<Enchantment> blacklist,
            CallbackInfoReturnable<List<EnchantmentInstance>> cir) {
        if (!Boolean.TRUE.equals(AnvilEnchantingContext.ANVIL_MODE.get())) {
            return;
        }

        List<EnchantmentInstance> list = new ArrayList<>();
        boolean isBook = stack.is(Items.BOOK);

        for (Enchantment ench : ForgeRegistries.ENCHANTMENTS.getValues()) {
            if (blacklist.contains(ench)) {
                continue;
            }
            if (!isBook && !ench.canEnchant(stack)) {
                continue;
            }
            EnchantmentInfo info = EnchModule.getEnchInfo(ench);
            for (int lvl = info.getMaxLevel(); lvl >= ench.getMinLevel(); lvl--) {
                if (power >= info.getMinPower(lvl) && power <= info.getMaxPower(lvl)) {
                    list.add(new EnchantmentInstance(ench, lvl));
                    break;
                }
            }
        }

        cir.setReturnValue(list);
    }
}
