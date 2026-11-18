package dev.apotheosis_bookshelf.menu;

import dev.apotheosis_bookshelf.block.BookshelfStatType;
import dev.apotheosis_bookshelf.block.ConfigurableBookshelfBlock;
import dev.apotheosis_bookshelf.block.ConfigurableBookshelfBlockEntity;
import dev.apotheosis_bookshelf.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

/**
 * Menu for configuring the stat value (1-50) of a ConfigurableBookshelfBlock.
 */
public class BookshelfConfigMenu extends AbstractContainerMenu {
    private final BlockPos pos;
    private final int initialValue;
    private final BookshelfStatType statType;
    private final ContainerLevelAccess access;

    // Client constructor
    public BookshelfConfigMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, buf.readBlockPos(), buf.readInt(), buf.readEnum(BookshelfStatType.class), ContainerLevelAccess.NULL);
    }

    // Server constructor
    public BookshelfConfigMenu(int containerId, Inventory playerInventory, ConfigurableBookshelfBlockEntity be) {
        this(containerId, be.getBlockPos(), be.getValue(),
                be.getBlockState().getBlock() instanceof ConfigurableBookshelfBlock b ? b.getStatType() : BookshelfStatType.ETERNA,
                ContainerLevelAccess.create(be.getLevel(), be.getBlockPos()));
    }

    private BookshelfConfigMenu(int containerId, BlockPos pos, int initialValue, BookshelfStatType statType, ContainerLevelAccess access) {
        super(ModMenus.BOOKSHELF_CONFIG_MENU.get(), containerId);
        this.pos = pos;
        this.initialValue = initialValue;
        this.statType = statType;
        this.access = access;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public BookshelfStatType getStatType() {
        return statType;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return access.evaluate((level, blockPos) ->
                level.getBlockState(blockPos).getBlock() instanceof ConfigurableBookshelfBlock
                        && player.distanceToSqr(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) <= 64.0,
                true);
    }
}
