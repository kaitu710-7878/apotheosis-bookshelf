package dev.apotheosis_bookshelf.mixin;

import dev.apotheosis_bookshelf.block.AnvilBookshelfBlock;
import dev.shadowsoffire.apotheosis.ench.table.ApothEnchantmentMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * Detects Anvil Bookshelves around the Enchanting Table when generating enchantment lists.
 */
@Mixin(value = ApothEnchantmentMenu.class, remap = false)
public abstract class ApothEnchantmentMenuMixin {

    @Inject(method = "getEnchantmentList", at = @At("HEAD"))
    private void apotheosis_bookshelf$beforeGetEnchantmentList(
            ItemStack stack, int enchantSlot, int level, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {
        boolean hasAnvilShelf = ((EnchantmentMenuAccessor) this).getAccess().evaluate((lvl, tablePos) -> {
            for (BlockPos offset : EnchantmentTableBlock.BOOKSHELF_OFFSETS) {
                if (ApothEnchantmentMenu.canReadStatsFrom(lvl, tablePos, offset)) {
                    BlockPos shelfPos = tablePos.offset(offset);
                    if (lvl.getBlockState(shelfPos).getBlock() instanceof AnvilBookshelfBlock) {
                        return true;
                    }
                }
            }
            return false;
        }, false);
        AnvilEnchantingContext.ANVIL_MODE.set(hasAnvilShelf);
    }

    @Inject(method = "getEnchantmentList", at = @At("RETURN"))
    private void apotheosis_bookshelf$afterGetEnchantmentList(
            ItemStack stack, int enchantSlot, int level, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {
        AnvilEnchantingContext.ANVIL_MODE.remove();
    }
}
