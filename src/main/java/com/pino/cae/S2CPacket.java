package com.pino.cae;

import com.mojang.logging.LogUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class S2CPacket {
    static Logger LOGGER = LogUtils.getLogger();
    public S2CPacket(){}
    public S2CPacket(FriendlyByteBuf friendlyByteBuf) {
    }

    public void encoder(FriendlyByteBuf buffer) {
        // Write to buffer
    }

    public static S2CPacket decoder(FriendlyByteBuf buffer) {
        // Create packet from buffer data
        return new S2CPacket();
    }

    public void messageConsumer(Supplier<NetworkEvent.Context> ctx) {
        // Handle message
    }

// For some SimpleChannel channel
    public static void handle(S2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Work that needs to be threadsafe (most work)
            ServerPlayer sender = ctx.get().getSender(); // the client that sent this packet
            // do stuff
            LOGGER.debug("amogus");
        });
        ctx.get().setPacketHandled(true);
    }

    // In ClientPacketHandlerClass

}
