package dev.apotheosis_bookshelf.block;

import dev.shadowsoffire.apotheosis.ench.api.IEnchantingBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A bookshelf block whose Eterna, Quanta, or Arcana contribution can be configured from 1 to 50.
 */
public class ConfigurableBookshelfBlock extends Block implements EntityBlock, IEnchantingBlock {
    private final BookshelfStatType statType;

    public ConfigurableBookshelfBlock(BookshelfStatType statType, Properties properties) {
        super(properties);
        this.statType = statType;
    }

    public BookshelfStatType getStatType() {
        return statType;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ConfigurableBookshelfBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            if (level.getBlockEntity(pos) instanceof ConfigurableBookshelfBlockEntity be) {
                NetworkHooks.openScreen(serverPlayer, be, buf -> {
                    buf.writeBlockPos(pos);
                    buf.writeInt(be.getValue());
                    buf.writeEnum(statType);
                });
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private int getStatValue(LevelReader level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ConfigurableBookshelfBlockEntity be) {
            return be.getValue();
        }
        return ConfigurableBookshelfBlockEntity.MIN_VALUE;
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
        return statType == BookshelfStatType.ETERNA ? getStatValue(level, pos) : 0.0F;
    }

    @Override
    public float getMaxEnchantingPower(BlockState state, LevelReader level, BlockPos pos) {
        return statType == BookshelfStatType.ETERNA ? 50.0F : 0.0F;
    }

    @Override
    public float getQuantaBonus(BlockState state, LevelReader level, BlockPos pos) {
        return statType == BookshelfStatType.QUANTA ? getStatValue(level, pos) : 0.0F;
    }

    @Override
    public float getArcanaBonus(BlockState state, LevelReader level, BlockPos pos) {
        return statType == BookshelfStatType.ARCANA ? getStatValue(level, pos) : 0.0F;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        int value = 1;
        CompoundTag beTag = BlockItem.getBlockEntityData(stack);
        if (beTag != null && beTag.contains(ConfigurableBookshelfBlockEntity.TAG_VALUE)) {
            value = beTag.getInt(ConfigurableBookshelfBlockEntity.TAG_VALUE);
        }
        tooltip.add(Component.translatable("tooltip.apotheosis_bookshelf.configurable_stat",
                Component.translatable(statType.getTranslationKey()), value, statType.getMaxValue()).withStyle(ChatFormatting.AQUA));
        if (statType == BookshelfStatType.ETERNA) {
            tooltip.add(Component.translatable("tooltip.apotheosis_bookshelf.eterna_cap").withStyle(ChatFormatting.GRAY));
        }
        tooltip.add(Component.translatable("tooltip.apotheosis_bookshelf.right_click_config").withStyle(ChatFormatting.DARK_GRAY));
    }
}
