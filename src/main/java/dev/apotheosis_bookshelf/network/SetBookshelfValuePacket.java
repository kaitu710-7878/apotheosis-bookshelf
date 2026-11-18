package dev.apotheosis_bookshelf.network;

import dev.apotheosis_bookshelf.block.ConfigurableBookshelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Packet sent from client to server to update a configurable bookshelf's stat value (1-50).
 */
public class SetBookshelfValuePacket {
    private final BlockPos pos;
    private final int value;

    public SetBookshelfValuePacket(BlockPos pos, int value) {
        this.pos = pos;
        this.value = value;
    }

    public static void encode(SetBookshelfValuePacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeInt(msg.value);
    }

    public static SetBookshelfValuePacket decode(FriendlyByteBuf buf) {
        return new SetBookshelfValuePacket(buf.readBlockPos(), buf.readInt());
    }

    public static void handle(SetBookshelfValuePacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) {
                return;
            }
            if (player.distanceToSqr(msg.pos.getX() + 0.5, msg.pos.getY() + 0.5, msg.pos.getZ() + 0.5) > 64.0) {
                return;
            }
            if (player.level().isLoaded(msg.pos)
                    && player.level().getBlockEntity(msg.pos) instanceof ConfigurableBookshelfBlockEntity be) {
                be.setValue(Mth.clamp(msg.value,
                        ConfigurableBookshelfBlockEntity.MIN_VALUE,
                        ConfigurableBookshelfBlockEntity.MAX_VALUE));
            }
        });
        ctx.setPacketHandled(true);
    }
}
