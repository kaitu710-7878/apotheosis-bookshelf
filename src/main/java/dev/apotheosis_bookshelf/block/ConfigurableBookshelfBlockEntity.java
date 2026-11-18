package dev.apotheosis_bookshelf.block;

import dev.apotheosis_bookshelf.menu.BookshelfConfigMenu;
import dev.apotheosis_bookshelf.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/**
 * BlockEntity storing the configured stat value (1-50) for a configurable bookshelf.
 */
public class ConfigurableBookshelfBlockEntity extends BlockEntity implements MenuProvider {
    public static final int MIN_VALUE = 1;
    public static final int DEFAULT_MAX_VALUE = 50;
    public static final String TAG_VALUE = "Value";

    private int value = 1;

    public ConfigurableBookshelfBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONFIGURABLE_BOOKSHELF.get(), pos, state);
    }

    public int getMaxValue() {
        if (getBlockState().getBlock() instanceof ConfigurableBookshelfBlock bookshelf) {
            return bookshelf.getStatType().getMaxValue();
        }
        return DEFAULT_MAX_VALUE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        int clamped = Mth.clamp(newValue, MIN_VALUE, getMaxValue());
        if (this.value != clamped) {
            this.value = clamped;
            setChanged();
            if (level != null && !level.isClientSide) {
                BlockState state = getBlockState();
                level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt(TAG_VALUE, value);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(TAG_VALUE)) {
            value = Mth.clamp(tag.getInt(TAG_VALUE), MIN_VALUE, getMaxValue());
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt(TAG_VALUE, value);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public Component getDisplayName() {
        return getBlockState().getBlock().getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new BookshelfConfigMenu(containerId, playerInventory, this);
    }
}
