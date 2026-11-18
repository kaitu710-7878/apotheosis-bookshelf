package dev.apotheosis_bookshelf.block;

import dev.apotheosis_bookshelf.ModConfig;
import dev.shadowsoffire.apotheosis.ench.api.IEnchantingBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

/**
 * A bookshelf that enables all anvil-applicable enchantments on the Enchanting Table and optionally blocks curses.
 */
public class AnvilBookshelfBlock extends Block implements IEnchantingBlock {
    public AnvilBookshelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean allowsTreasure(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    public Set<Enchantment> getBlacklistedEnchantments(BlockState state, LevelReader level, BlockPos pos) {
        return BookshelfUtils.getCurseBlacklist();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.apotheosis_bookshelf.anvil_shelf").withStyle(ChatFormatting.LIGHT_PURPLE));
        if (ModConfig.COMMON.disableCurseOnShelves.get()) {
            tooltip.add(Component.translatable("tooltip.apotheosis_bookshelf.no_curses").withStyle(ChatFormatting.GREEN));
        }
    }
}
