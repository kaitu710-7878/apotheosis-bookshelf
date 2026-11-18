package dev.apotheosis_bookshelf.network;

import dev.apotheosis_bookshelf.ApotheosisBookshelfMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * Network channel registration for Apotheosis Bookshelf.
 */
public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ApotheosisBookshelfMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        CHANNEL.registerMessage(id++, SetBookshelfValuePacket.class,
                SetBookshelfValuePacket::encode,
                SetBookshelfValuePacket::decode,
                SetBookshelfValuePacket::handle);
    }
}
